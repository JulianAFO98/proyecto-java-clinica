package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SegundaEntrega.Patrones.PatronState.GestionLlamados;
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
        if(comando.equals(this.vista.EMPEZAR_SIMULACION)){
            this.vista.agregarALogSimulacion("Iniciada la simulacion\n");
        }else if(comando.equals(this.vista.FINALIZAR_SIMULACION)){
            this.vista.agregarALogSimulacion("Finalizada la simulacion\n");
        }
    }

}
