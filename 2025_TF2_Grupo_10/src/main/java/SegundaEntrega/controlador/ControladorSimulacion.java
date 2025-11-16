package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SegundaEntrega.modelo.Datos.GestionLlamados;
import SegundaEntrega.vista.IVista;

/**
 * Gestiona los comandos relacionados con la simulacion de llamados.
 */
public class ControladorSimulacion implements ActionListener{
    private IVista vista;
    private GestionLlamados gestion;

    /**
     * Vincula la vista con la gestion de llamados para la simulacion.
     *
     * @param vista referencia a la interfaz que dispara eventos
     * <br>Pre: vista != null
     * <br>Post: this.vista = vista
     * @param gestion componente que ejecuta la logica de simulacion
     * <br>Pre: gestion != null
     * <br>Post: this.gestion = gestion y la vista registra el controlador.
     */
    public ControladorSimulacion(IVista vista, GestionLlamados gestion) {
        // Precondiciones
        assert vista != null : "La vista no puede ser nula.";
        assert gestion != null : "La gestion de llamados no puede ser nula.";

        this.vista = vista;
        this.gestion = gestion;
        this.vista.addActionListenerSimulacion(this);
        
        // Postcondiciones implicitas
        assert this.vista == vista : "La vista no se asigno correctamente.";
        assert this.gestion == gestion : "La gestion no se asigno correctamente.";
    }

    /**
     * Atiende los eventos de la vista y delega en la gestion correspondiente.
     *
     * @param e evento emitido por los controles de simulacion
     * <br>Pre: e != null
     * <br>Post: Se ejecuta la accion correspondiente: empezar, finalizar o llamar operario.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Precondicion
        assert e != null : "El evento de accion no puede ser nulo.";
        String comando = e.getActionCommand();
        assert comando != null && !comando.trim().isEmpty() : "El comando de accion no debe ser nulo o vacio.";
        
        // Invariantes
        assert this.vista != null : "La vista no debe ser nula.";
        assert this.gestion != null : "La gestion no debe ser nula.";

        if(comando.equals(IVista.EMPEZAR_SIMULACION)){
            // Invariante interno
            String cantidadStr = this.vista.getCantidad();
            String iteracionesStr = this.vista.getIteracion();

            try {
                // Precondiciones de negocio
                int cantidad = Integer.parseInt(cantidadStr);
                int iteraciones = Integer.parseInt(iteracionesStr);
                
                assert cantidad > 0 : "La cantidad de llamados debe ser positiva.";
                assert iteraciones > 0 : "La cantidad de iteraciones debe ser positiva.";
                
                this.vista.cambiarEstadoInput(false);
                this.gestion.empezarSimulacion(cantidad, iteraciones);
                
                // Postcondicion de exito
                
            } catch (NumberFormatException exc) {
                this.vista.mostrarMensaje("Ingrese una cantidad valida");
                // Postcondicion de error
            }
        }else if(comando.equals(IVista.FINALIZAR_SIMULACION)){
            this.gestion.finalizarSimulacion();
            this.vista.cambiarEstadoInput(true);
            // Postcondicion
            
        }else if(comando.equals(IVista.LLAMAR_OPERARIO)){
            this.gestion.llamarOperario();
            // Postcondicion
        }
    }

}