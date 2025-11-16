package SegundaEntrega;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import SegundaEntrega.Patrones.PatronObserver.ObservadorAmbulancia;
import SegundaEntrega.controlador.ControladorAsociados;
import SegundaEntrega.controlador.ControladorSimulacion;
import SegundaEntrega.modelo.Datos.Ambulancia;
import SegundaEntrega.modelo.Datos.GestionLlamados;
import SegundaEntrega.persistencia.Conexion;
import SegundaEntrega.vista.IVista;
import SegundaEntrega.vista.VentanaPestanas;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Conexion.crearBaseDatos();
                Conexion.crearUsuario();
                Conexion.limpiarYCrearTabla();
                Conexion.insertarAsociados();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            IVista v = new VentanaPestanas();
            Ambulancia a = new Ambulancia();
            GestionLlamados gestion = new GestionLlamados(a);
            ControladorSimulacion cs = new ControladorSimulacion(v, gestion);
            ObservadorAmbulancia observer = new ObservadorAmbulancia(a, v);
            ControladorAsociados ca = new ControladorAsociados(v, gestion);
        });
    }
}