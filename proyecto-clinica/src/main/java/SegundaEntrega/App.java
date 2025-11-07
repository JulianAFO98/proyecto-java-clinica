package SegundaEntrega;



import javax.swing.SwingUtilities;

import SegundaEntrega.Patrones.PatronState.GestionLlamados;
import SegundaEntrega.controlador.ControladorAsociados;
import SegundaEntrega.controlador.ControladorSimulacion;
import SegundaEntrega.vista.IVista;
import SegundaEntrega.vista.VentanaPestanas;


public class App {
    public static void main(String[] args) {
        // Ejecutar en el Event Dispatch Thread para seguridad de Swing
        SwingUtilities.invokeLater(() -> {
            IVista v = new VentanaPestanas();
            GestionLlamados gestion = new GestionLlamados();
            ControladorAsociados ca = new ControladorAsociados(v, gestion);
            ControladorSimulacion cs = new ControladorSimulacion(v,gestion);
        });
    }
}