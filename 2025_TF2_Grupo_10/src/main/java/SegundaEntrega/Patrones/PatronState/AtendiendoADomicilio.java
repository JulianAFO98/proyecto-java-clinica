package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que modela la atencion domiciliaria en curso.
 */
public class AtendiendoADomicilio implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Crea el estado de atencion domiciliaria.
     *
     * @param ambulancia referencia que cambia de estado
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public AtendiendoADomicilio(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;

        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Ignora nuevas solicitudes de atencion domiciliaria porque ya esta atendiendo.
     * <br>Pre: No aplica
     * <br>Post: No se modifica el estado de la ambulancia.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // Invariante
        assert this.ambulancia != null : "La ambulancia no debe ser nula.";
    }

    /**
     * Rechaza el traslado debido a la atencion actual.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado de la ambulancia no cambia.
     */
    @Override
    public void solicitarTrasladoClinica() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede solicitar traslado mientras atiende a domicilio");
    }

    /**
     * Cambia al estado de regreso sin paciente.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a RegresandoSinPaciente.
     */
    @Override
    public void retornoAutomatico() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("Regresando sin paciente");
        ambulancia.setEstado(new RegresandoSinPaciente(ambulancia));
    }

    /**
     * Rechaza el mantenimiento hasta finalizar la atencion.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado de la ambulancia no cambia.
     */
    @Override
    public void solicitarMantenimiento() {
        // Precondicion/Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras atiende a domicilio");
    }
}