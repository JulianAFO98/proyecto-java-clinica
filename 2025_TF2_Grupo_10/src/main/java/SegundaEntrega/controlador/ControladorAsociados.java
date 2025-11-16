package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import SegundaEntrega.persistencia.AsociadoExistenteException;
import SegundaEntrega.vista.IVista;
import SegundaEntrega.modelo.Datos.Asociado;
import SegundaEntrega.modelo.Datos.GestionLlamados;

/**
 * Coordina las acciones sobre asociados y actualiza la interfaz grafica.
 */
public class ControladorAsociados implements ActionListener {
    private IVista vista;
    private GestionLlamados gestion;

    /**
     * Registra la vista y prepara la gestion de asociados.
     *
     * @param vista referencia a la interfaz donde se muestran los datos
     * <br>Pre: vista != null
     * <br>Post: this.vista = vista
     * @param gestion fachada que administra asociados y sus operaciones
     * <br>Pre: gestion != null
     * <br>Post: this.gestion = gestion y la vista se actualiza con los asociados
     */
    public ControladorAsociados(IVista vista, GestionLlamados gestion) {
        // Precondiciones
        assert vista != null : "La vista no puede ser nula.";
        assert gestion != null : "La gestion de llamados no puede ser nula.";

        this.vista = vista;
        this.gestion = gestion;
        this.vista.addActionListenerAsociado(this);
        this.vista.actualizarListas(this.gestion.getAsociados());
        
        // Postcondicion implicita: los atributos se asignaron
        assert this.vista == vista : "La vista no se asigno correctamente.";
        assert this.gestion == gestion : "La gestion no se asigno correctamente.";
    }

    /**
     * Procesa los eventos de la vista de asociados.
     *
     * @param e evento generado por la interfaz grafica
     * <br>Pre: e != null
     * <br>Post: Se ejecuta la accion correspondiente (crear o eliminar) y se actualiza la interfaz.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Precondicion
        assert e != null : "El evento de accion no puede ser nulo.";
        String comando = e.getActionCommand();
        assert comando != null && !comando.trim().isEmpty() : "El comando de accion no debe ser nulo o vacio.";
        
        // Invariante
        assert this.vista != null : "La vista no debe ser nula.";
        assert this.gestion != null : "La gestion no debe ser nula.";

        if (comando.equals(IVista.CREAR_ASOCIADO)) {
            String nombre = this.vista.getNombre();
            String dni = this.vista.getDni();
            
            // Precondicion interna para la accion de creacion
            assert nombre != null && !nombre.trim().isEmpty() : "El nombre del asociado no debe ser nulo o vacio.";
            assert dni != null && !dni.trim().isEmpty() : "El DNI del asociado no debe ser nulo o vacio.";

            int asociadosAntes = this.gestion.getAsociados().size();
            
            try {
                this.gestion.agregarAsociado(nombre, dni);
                this.vista.agregarALogAsociados("Se creo un asociado\n");
                
                // Postcondicion de exito
                assert this.gestion.getAsociados().size() == asociadosAntes + 1 : "Si no hubo excepcion, la cantidad de asociados debe aumentar en 1.";

            } catch (AsociadoExistenteException exception) {
                this.vista.mostrarMensaje("El dni ya existe: " + exception.getDni());
                
                // Postcondicion de error: el estado de la gestion no cambio
                assert this.gestion.getAsociados().size() == asociadosAntes : "Si hubo excepcion, la cantidad de asociados no debe cambiar.";
            }

        } else if (comando.equals(IVista.DAR_BAJA_ASOCIADO)) {
            Asociado asociadoSeleccionado = this.vista.getAsociadoSeleccionado();
            
            if (asociadoSeleccionado != null) {
                // Precondicion implicita: El asociado seleccionado debe existir en la gestion.
                
                int asociadosAntes = this.gestion.getAsociados().size();
                this.gestion.eliminarAsociado(asociadoSeleccionado);
                this.vista.agregarALogAsociados("Se dio de baja un asociado\n");
                
                // Postcondicion
                assert this.gestion.getAsociados().size() == asociadosAntes - 1 : "La cantidad de asociados debe disminuir en 1.";
                assert !this.gestion.getAsociados().contains(asociadoSeleccionado) : "El asociado debe haber sido eliminado de la gestion.";
            }
        }
        
        // Postcondicion final (incluso si la accion no tuvo efecto o fue exitosa)
        this.vista.limpiarCamposAsociado();
        this.vista.actualizarListas(this.gestion.getAsociados());
        
        // Invariante de coleccion
        assert this.gestion.getAsociados() != null : "La lista de asociados no puede ser nula despues de la operacion.";
    }

    
}