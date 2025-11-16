package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que modela el traslado de un paciente hacia la clinica.
 */
public class TrasladandoPaciente extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Construye el estado de traslado para la ambulancia.
     *
     * @param ambulancia referencia sobre la que actua
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public TrasladandoPaciente(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;
        
        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Ignora nuevas solicitudes domiciliarias durante el traslado.
     * <br>Pre: ambulancia != null
     * <br>Post: No se modifica el estado de la ambulancia.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
    }

    /**
     * Rechaza un segundo traslado mientras el actual no finaliza.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarTrasladoClinica() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede solicitar traslado mientras ya esta trasladando un paciente");
    }

    /**
     * Cambia a disponible cuando concluye el traslado.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a Disponible.
     */
    @Override
    public void retornoAutomatico() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("Cambio a disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    /**
     * Rechaza la solicitud de mantenimiento hasta finalizar el traslado.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarMantenimiento() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras esta trasladando un paciente");

    }
}