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
            this.gestion.empezarSimulacion();
        }else if(comando.equals(IVista.FINALIZAR_SIMULACION)){
            this.gestion.finalizarSimulacion();
        }else if(comando.equals(IVista.LLAMAR_OPERARIO)){
            this.gestion.llamarOperario();
        }
    }

    public void agregarALogSimulacion(String s){
        this.vista.agregarALogSimulacion(s);
    }

}
