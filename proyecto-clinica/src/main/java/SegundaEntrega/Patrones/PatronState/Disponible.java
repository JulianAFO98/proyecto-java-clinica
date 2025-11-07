package SegundaEntrega.Patrones.PatronState;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class Disponible implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public Disponible(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    @Override
    public void retornoAutomatico() {
        // La ambulancia ya esta disponible, no hacer nada
    }

    @Override
    public void solicitarMantenimiento() {
        ambulancia.setEstado(new EnTaller(ambulancia));
    }
}
