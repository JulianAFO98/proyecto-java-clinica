package SegundaEntrega.Patrones.PatronObserver;

import java.util.Observable;
import java.util.Observer;

import SegundaEntrega.controlador.ControladorAsociados;
import SegundaEntrega.modelo.Datos.Ambulancia;

public class ObservadorAmbulancia implements Observer {
    private Ambulancia ambulancia;
    private ControladorAsociados controladorAsociados;

    public ObservadorAmbulancia(Ambulancia ambulancia, ControladorAsociados controladorAsociados) {
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
        this.controladorAsociados = controladorAsociados;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Ambulancia) {
            Ambulancia ambulancia = (Ambulancia) o;
            System.out.println("Estado de la ambulancia actualizado: " + ambulancia.getEstado());
        }
    }
}
