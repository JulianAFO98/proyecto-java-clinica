package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class RegresandoDelTaller extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public RegresandoDelTaller(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
    }

    @Override
    public void solicitarTrasladoClinica() {

        ambulancia.notificarCambio("No puede atender solicitudes mientras regresa del taller");

    }

    @Override
    public void retornoAutomatico() {

        ambulancia.notificarCambio("Ambulancia disponible");

        ambulancia.setEstado(new Disponible(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
   
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras regresa del taller");

    }
}
