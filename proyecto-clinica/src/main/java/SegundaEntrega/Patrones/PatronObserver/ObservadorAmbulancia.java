package SegundaEntrega.Patrones.PatronObserver;

import java.util.Observable;
import java.util.Observer;

import SegundaEntrega.modelo.Datos.Ambulancia;
import SegundaEntrega.vista.IVista;

public class ObservadorAmbulancia implements Observer {
    private Observable ambulancia;
    private IVista vista;

    public ObservadorAmbulancia(Ambulancia ambulancia, IVista vista) {
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
        this.vista = vista;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != this.ambulancia)
            throw new IllegalArgumentException();
        Ambulancia ambulancia = (Ambulancia) o;
        String mensaje = (String) arg;
        this.vista.agregarALogSimulacion(mensaje);
    }
}
