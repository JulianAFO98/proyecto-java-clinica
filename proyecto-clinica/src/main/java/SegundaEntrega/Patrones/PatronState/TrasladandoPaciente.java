package SegundaEntrega.Patrones.PatronState;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class TrasladandoPaciente implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public TrasladandoPaciente(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
    }

    @Override
    public void solicitarTrasladoClinica() {
        // Ya está trasladando paciente, no hacer nada
    }

    @Override
    public void retornoAutomatico() {
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
    }
}
