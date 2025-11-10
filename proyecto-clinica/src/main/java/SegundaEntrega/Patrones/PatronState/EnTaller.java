package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class EnTaller extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public EnTaller(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        ambulancia.notificarCambio("En taller, no se puede atender a domicilio");
    }

    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("En taller, no se puede trasladar a clinica");
    }

    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Continua en taller");
    }

    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("Mantenimiento completado, regresando del taller");
        ambulancia.setEstado(new RegresandoDelTaller(ambulancia));
    }
}
