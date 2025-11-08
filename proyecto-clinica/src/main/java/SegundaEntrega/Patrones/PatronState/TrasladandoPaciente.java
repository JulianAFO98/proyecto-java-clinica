package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class TrasladandoPaciente extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public TrasladandoPaciente(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
    }

    @Override
    public void solicitarTrasladoClinica() {
        setChanged();
        notifyObservers("No puede solicitar traslado mientras ya esta trasladando un paciente");
    }

    @Override
    public void retornoAutomatico() {
        setChanged();
        notifyObservers("Cambio a disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
        setChanged();
        notifyObservers("No puede solicitar mantenimiento mientras esta trasladando un paciente");
    }
}
