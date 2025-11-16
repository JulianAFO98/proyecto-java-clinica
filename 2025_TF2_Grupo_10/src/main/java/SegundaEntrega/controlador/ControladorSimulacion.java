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
     * @param gestion componente que ejecuta la logica de simulacion
     */
    public ControladorSimulacion(IVista vista, GestionLlamados gestion) {
        this.vista = vista;
        this.gestion = gestion;
        this.vista.addActionListenerSimulacion(this);
    }

    /**
     * Atiende los eventos de la vista y delega en la gestion correspondiente.
     *
     * @param e evento emitido por los controles de simulacion
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando  = e.getActionCommand();
        if(comando.equals(IVista.EMPEZAR_SIMULACION)){
            try {
                int cantidad = Integer.parseInt(this.vista.getCantidad());
                int iteraciones = Integer.parseInt(this.vista.getIteracion());
                this.vista.cambiarEstadoInput(false);
                this.gestion.empezarSimulacion(cantidad, iteraciones);
            } catch (NumberFormatException exc) {
                this.vista.mostrarMensaje("Ingrese una cantidad valida");
            }
        }else if(comando.equals(IVista.FINALIZAR_SIMULACION)){
            this.gestion.finalizarSimulacion();
            this.vista.cambiarEstadoInput(true);
        }else if(comando.equals(IVista.LLAMAR_OPERARIO)){
            this.gestion.llamarOperario();
        }
    }

}
