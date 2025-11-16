package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SegundaEntrega.modelo.Datos.GestionLlamados;
import SegundaEntrega.vista.IVista;

public class ControladorSimulacion implements ActionListener{
    private IVista vista;
    private GestionLlamados gestion;

    

    public ControladorSimulacion(IVista vista, GestionLlamados gestion) {
        this.vista = vista;
        this.gestion = gestion;
        this.vista.addActionListenerSimulacion(this);
    }



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
    //borrar despues
    public void agregarALogSimulacion(String s){
        this.vista.agregarALogSimulacion(s);
    }

}
