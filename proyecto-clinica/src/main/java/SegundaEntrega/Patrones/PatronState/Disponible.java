package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class Disponible extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public Disponible(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
        ambulancia.notificarCambio("Atendiendo a domicilio");
        System.out.println("Hola");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("Trasladando a clinica");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Ambulancia disponible");
    }

    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("Solicitando mantenimiento");
        ambulancia.setEstado(new EnTaller(ambulancia));
    }
}
