package SegundaEntrega;



import javax.swing.SwingUtilities;

import SegundaEntrega.Patrones.PatronObserver.ObservadorAmbulancia;
import SegundaEntrega.controlador.ControladorAsociados;
import SegundaEntrega.controlador.ControladorSimulacion;
import SegundaEntrega.modelo.Datos.Ambulancia;
import SegundaEntrega.modelo.Datos.GestionLlamados;
import SegundaEntrega.vista.IVista;
import SegundaEntrega.vista.VentanaPestanas;


public class App {
    public static void main(String[] args) {
        // Ejecutar en el Event Dispatch Thread para seguridad de Swing
        SwingUtilities.invokeLater(() -> {
            IVista v = new VentanaPestanas();
            Ambulancia a = new Ambulancia();
            GestionLlamados gestion = new GestionLlamados(a);
            ControladorSimulacion cs = new ControladorSimulacion(v,gestion);
            ObservadorAmbulancia observer = new ObservadorAmbulancia(a, cs);
            ControladorAsociados ca = new ControladorAsociados(v, gestion);
        });
    }
}