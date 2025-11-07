package SegundaEntrega.Patrones.PatronState;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class EnTaller implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia ambulancia) {
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
    }

    @Override
    public void solicitarMantenimiento() {
        ambulancia.setEstado(new RegresandoDelTaller(ambulancia));
    }
}
