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
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public Disponible(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;

        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Pasa al estado de atencion domiciliaria y notifica a los observadores.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a AtendiendoADomicilio.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("Atendiendo a domicilio");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    /**
     * Cambia al estado de traslado a clinica y notifica.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a TrasladandoPaciente.
     */
    @Override
    public void solicitarTrasladoClinica() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("Trasladando a clinica");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    /**
     * Mantiene el estado disponible notificando al observer.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia se mantiene en Disponible.
     */
    @Override
    public void retornoAutomatico() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("Ambulancia disponible");
    }

    /**
     * Deriva al estado de mantenimiento en taller.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a EnTaller.
     */
    @Override
    public void solicitarMantenimiento() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("Solicitando mantenimiento");
        ambulancia.setEstado(new EnTaller(ambulancia));
    }
}