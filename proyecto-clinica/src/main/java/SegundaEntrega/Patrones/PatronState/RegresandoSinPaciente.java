package SegundaEntrega.Patrones.PatronState;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class RegresandoSinPaciente implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public RegresandoSinPaciente(Ambulancia ambulancia) {
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
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
    }

}
