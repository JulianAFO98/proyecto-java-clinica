package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que representa el regreso de la ambulancia sin transportar pacientes.
 */
public class RegresandoSinPaciente extends Observable implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Construye el estado de regreso sin paciente asociado a la ambulancia.
     *
     * @param ambulancia instancia administrada
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public RegresandoSinPaciente(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;
        
        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Permite volver al estado de atencion domiciliaria.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a AtendiendoADomicilio.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("Cambio a atendiendo a domicilio");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    /**
     * Permite pasar al traslado de paciente.
     * <br>Pre: ambulancia != null
     * <br>Post: El estado de la ambulancia cambia a TrasladandoPaciente.
     */
    @Override
    public void solicitarTrasladoClinica() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";
        
        ambulancia.notificarCambio("Cambio a trasladando paciente");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    /**
     * Retorna al estado disponible al finalizar el regreso.
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
     * Rechaza la solicitud de mantenimiento mientras se encuentra en regreso.
     * <br>Pre: ambulancia != null
     * <br>Post: Se notifica el rechazo a la vista. El estado no cambia.
     */
    @Override
    public void solicitarMantenimiento() {
        // Invariante
        assert ambulancia != null : "La ambulancia no debe ser nula.";

        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras regresa sin paciente");
    }

}