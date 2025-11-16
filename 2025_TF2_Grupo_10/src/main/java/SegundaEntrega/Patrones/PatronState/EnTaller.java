package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que representa la permanencia de la ambulancia en el taller.
 */
public class EnTaller extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Crea el estado de mantenimiento en taller.
     *
     * @param ambulancia recurso que espera mantenimiento
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public EnTaller(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;
        
        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Rechaza solicitudes domiciliarias mientras permanece en taller.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("En taller, no se puede atender a domicilio");
    }

    /**
     * Rechaza los traslados a clinica durante el mantenimiento.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarTrasladoClinica() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("En taller, no se puede trasladar a clinica");
    }

    /**
     * Indica que la ambulancia continua en taller.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el mensaje de continuidad. El estado se mantiene en EnTaller.
     */
    @Override
    public void retornoAutomatico() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("Continua en taller");
    }

    /**
     * Marca la finalizacion del mantenimiento y pasa al regreso desde el taller.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a RegresandoDelTaller.
     */
    @Override
    public void solicitarMantenimiento() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("Mantenimiento completado, regresando del taller");
        ambulancia.setEstado(new RegresandoDelTaller(ambulancia));
    }
}