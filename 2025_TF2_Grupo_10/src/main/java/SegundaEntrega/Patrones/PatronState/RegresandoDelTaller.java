package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que refleja el regreso de la ambulancia luego del mantenimiento.
 */
public class RegresandoDelTaller extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Construye el estado de regreso desde el taller.
     *
     * @param ambulancia instancia administrada por el patron State
     */
    public RegresandoDelTaller(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Ignora la atencion domiciliaria hasta finalizar el regreso.
     */
    @Override
    public void solicitarAtencionDomicilio() {
    }

    /**
     * Rechaza el traslado a clinica durante el regreso.
     */
    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("No puede atender solicitudes mientras regresa del taller");

    }

    /**
     * Una vez completado el regreso vuelve al estado disponible.
     */
    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Ambulancia disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    /**
     * Evita solicitar nuevo mantenimiento hasta finalizar el regreso.
     */
    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras regresa del taller");

    }
}
