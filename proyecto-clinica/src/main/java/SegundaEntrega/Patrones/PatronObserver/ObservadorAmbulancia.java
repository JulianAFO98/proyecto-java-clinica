package SegundaEntrega.Patrones.PatronObserver;

import java.util.Observable;
import java.util.Observer;

import SegundaEntrega.controlador.ControladorSimulacion;
import SegundaEntrega.modelo.Datos.Ambulancia;

public class ObservadorAmbulancia implements Observer {
    private Observable ambulancia;
    private ControladorSimulacion controladorSimulacion;

    public ObservadorAmbulancia(Ambulancia ambulancia, ControladorSimulacion controladorSimulacion) {
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
        this.controladorSimulacion = controladorSimulacion;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != this.ambulancia)
            throw new IllegalArgumentException();
        Ambulancia ambulancia = (Ambulancia) o;
        String mensaje = (String) arg;
        this.controladorSimulacion.agregarALogSimulacion(mensaje);
    }
}
