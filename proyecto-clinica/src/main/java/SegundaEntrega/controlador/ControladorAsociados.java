package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SegundaEntrega.modelo.Datos.GestionLlamados;
import SegundaEntrega.vista.IVista;

public class ControladorAsociados implements ActionListener{
    private IVista vista;
    private GestionLlamados gestion;

    

    public ControladorAsociados(IVista vista, GestionLlamados gestion) {
        this.vista = vista;
        this.gestion = gestion;
        this.vista.addActionListenerAsociado(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals(this.vista.CREAR_ASOCIADO)){
            String nombre = this.vista.getNombre();
            String dni = this.vista.getDni();
            try {
                this.gestion.agregarAsociado(nombre,dni);
            } catch (Exception exc) {
            }
            this.vista.agregarALogAsociados("Se creo un asociado\n");

        }
        actualizarListaAsociados();
    }

    public void actualizarListaAsociados(){
        this.vista.actualizarListas(this.gestion.getAsociados());
    }

}
