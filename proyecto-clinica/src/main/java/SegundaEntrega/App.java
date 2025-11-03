package SegundaEntrega;



import javax.swing.SwingUtilities;

import SegundaEntrega.vista.VentanaPestanas;


public class App 
{
   public static void main(String[] args) {
        // Ejecutar en el Event Dispatch Thread para seguridad de Swing
        SwingUtilities.invokeLater(() -> new VentanaPestanas());
    }
}
