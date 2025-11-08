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
        ambulancia.notificarCambio("No puede solicitar traslado mientras ya esta trasladando un paciente");
    }

    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Cambio a disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras esta trasladando un paciente");

    }
}
