package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class RegresandoSinPaciente extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public RegresandoSinPaciente(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        setChanged();
        notifyObservers("Cambio a atendiendo a domicilio");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    @Override
    public void solicitarTrasladoClinica() {
        setChanged();
        notifyObservers("Cambio a trasladando paciente");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
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
        notifyObservers("No puede solicitar mantenimiento mientras regresa sin paciente");
    }

}
