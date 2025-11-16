package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
     * @param gestion fachada que administra asociados y sus operaciones
     */
    public ControladorAsociados(IVista vista, GestionLlamados gestion) {
        this.vista = vista;
        this.gestion = gestion;
        this.vista.addActionListenerAsociado(this);
        this.vista.actualizarListas(this.gestion.getAsociados());
    }

    /**
     * Procesa los eventos de la vista de asociados.
     *
     * @param e evento generado por la interfaz grafica
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals(IVista.CREAR_ASOCIADO)) {
            String nombre = this.vista.getNombre();
            String dni = this.vista.getDni();
            try {
                this.gestion.agregarAsociado(nombre,dni);
                this.vista.agregarALogAsociados("Se creo un asociado\n");
            } catch (AsociadoExistenteException exception) {
                this.vista.mostrarMensaje("El dni ya existe: "+exception.getDni());
            }

        } else if (comando.equals(IVista.DAR_BAJA_ASOCIADO)) {
            Asociado asociadoSeleccionado = this.vista.getAsociadoSeleccionado();
            if (asociadoSeleccionado != null) {
                this.gestion.eliminarAsociado(asociadoSeleccionado);
                this.vista.agregarALogAsociados("Se dio de baja un asociado\n");
            }
        }
        this.vista.limpiarCamposAsociado();
        this.vista.actualizarListas(this.gestion.getAsociados());
    }

    

    
}
