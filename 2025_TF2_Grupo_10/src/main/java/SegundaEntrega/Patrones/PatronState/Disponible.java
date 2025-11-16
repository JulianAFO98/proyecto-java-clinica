package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que representa a la ambulancia lista para atender solicitudes.
 */
public class Disponible extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Construye el estado Disponible asociado a una ambulancia.
     *
     * @param ambulancia instancia que cambia de estado
     */
    public Disponible(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Pasa al estado de atencion domiciliaria y notifica a los observadores.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        ambulancia.notificarCambio("Atendiendo a domicilio");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    /**
     * Cambia al estado de traslado a clinica y notifica.
     */
    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("Trasladando a clinica");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    /**
     * Mantiene el estado disponible notificando al observer.
     */
    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Ambulancia disponible");
    }

    /**
     * Deriva al estado de mantenimiento en taller.
     */
    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("Solicitando mantenimiento");
        ambulancia.setEstado(new EnTaller(ambulancia));
    }
}
