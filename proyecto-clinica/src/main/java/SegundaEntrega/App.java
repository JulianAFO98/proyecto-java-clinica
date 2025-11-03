package SegundaEntrega;



import javax.swing.SwingUtilities;

import SegundaEntrega.controlador.ControladorAsociados;
import SegundaEntrega.vista.IVista;
import SegundaEntrega.vista.VentanaPestanas;


public class App {
    public static void main(String[] args) {
        // Ejecutar en el Event Dispatch Thread para seguridad de Swing
        SwingUtilities.invokeLater(() -> {
            IVista v = new VentanaPestanas();
            ControladorAsociados ca = new ControladorAsociados(v, null);
        });
    }
}