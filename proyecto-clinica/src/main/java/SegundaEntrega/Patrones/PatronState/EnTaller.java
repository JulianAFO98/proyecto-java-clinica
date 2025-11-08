package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class EnTaller extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        setChanged();
        notifyObservers("En taller, no se puede atender a domicilio");
    }

    @Override
    public void solicitarTrasladoClinica() {
        setChanged();
        notifyObservers("En taller, no se puede trasladar a clinica");
    }

    @Override
    public void retornoAutomatico() {
        setChanged();
        notifyObservers("Continua en taller");
    }

    @Override
    public void solicitarMantenimiento() {
        setChanged();
        notifyObservers("Mantenimiento completado, regresando del taller");
        ambulancia.setEstado(new RegresandoDelTaller(ambulancia));
    }
}
