package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class Disponible extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public Disponible(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        setChanged();
        notifyObservers("Atendiendo a domicilio");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    @Override
    public void solicitarTrasladoClinica() {
        setChanged();
        notifyObservers("Trasladando a clinica");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    @Override
    public void retornoAutomatico() {
        setChanged();
        notifyObservers("Ambulancia disponible");
        // La ambulancia ya esta disponible, no hacer nada
    }

    @Override
    public void solicitarMantenimiento() {
        setChanged();
        notifyObservers("Solicitando mantenimiento");
        ambulancia.setEstado(new EnTaller(ambulancia));
    }
}
