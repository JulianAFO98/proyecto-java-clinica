package SegundaEntrega.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import SegundaEntrega.modelo.Datos.GestionLlamados;
import SegundaEntrega.vista.IVista;
import SegundaEntrega.modelo.Datos.Asociado;

public class ControladorAsociados implements ActionListener {
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
        if (comando.equals(this.vista.CREAR_ASOCIADO)) {
            String nombre = this.vista.getNombre();
            String dni = this.vista.getDni();
            try {
                this.gestion.agregarAsociado(nombre, dni);
            } catch (Exception exc) {
            }
            this.vista.agregarALogAsociados("Se creo un asociado\n");
            this.vista.limpiarCamposAsociado();

        } else if (comando.equals(this.vista.DAR_BAJA_ASOCIADO)) {
            Asociado asociadoSeleccionado = this.getAsociadoSeleccionado();
            if (asociadoSeleccionado != null) {
                this.gestion.eliminarAsociado(asociadoSeleccionado);
                this.vista.agregarALogAsociados("Se dio de baja un asociado\n");
            }
        }
        actualizarListaAsociados();
    }

    public void actualizarListaAsociados() {
        ArrayList<Asociado> asociadoDeAlta = new ArrayList<>();
        for (Asociado asociado : this.gestion.getAsociados()) {
            if (asociado.isAlta()) {
                asociadoDeAlta.add(asociado);
            }
        }

        this.vista.actualizarListas(asociadoDeAlta);
    }

    public Asociado getAsociadoSeleccionado() {
        return this.vista.getAsociadoSeleccionado();
    }

}
