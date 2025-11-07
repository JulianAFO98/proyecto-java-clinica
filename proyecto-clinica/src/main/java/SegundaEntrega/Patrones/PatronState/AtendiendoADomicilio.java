package SegundaEntrega.Patrones.PatronState;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class AtendiendoADomicilio implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public AtendiendoADomicilio(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
    }

    @Override
    public void solicitarTrasladoClinica() {
    }

    @Override
    public void retornoAutomatico() {
        ambulancia.setEstado(new RegresandoSinPaciente(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
    }
}
