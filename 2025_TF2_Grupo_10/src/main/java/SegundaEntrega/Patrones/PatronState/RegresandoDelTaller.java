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
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public RegresandoDelTaller(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;
        
        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Ignora la atencion domiciliaria hasta finalizar el regreso.
     * <br>Pre: ambulancia != null
     * <br>Post: No se modifica el estado de la ambulancia.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
    }

    /**
     * Rechaza el traslado a clinica durante el regreso.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarTrasladoClinica() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede atender solicitudes mientras regresa del taller");

    }

    /**
     * Una vez completado el regreso vuelve al estado disponible.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a Disponible.
     */
    @Override
    public void retornoAutomatico() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("Ambulancia disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    /**
     * Evita solicitar nuevo mantenimiento hasta finalizar el regreso.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarMantenimiento() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras regresa del taller");

    }
}