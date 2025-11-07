package SegundaEntrega.Patrones.PatronState;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class RegresandoDelTaller implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public RegresandoDelTaller(Ambulancia ambulancia) {
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
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
    }
}
