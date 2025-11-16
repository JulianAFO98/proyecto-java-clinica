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
     */
    public RegresandoSinPaciente(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Permite volver al estado de atencion domiciliaria.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        ambulancia.notificarCambio("Cambio a atendiendo a domicilio");
        ambulancia.setEstado(new AtendiendoADomicilio(ambulancia));
    }

    /**
     * Permite pasar al traslado de paciente.
     */
    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("Cambio a trasladando paciente");
        ambulancia.setEstado(new TrasladandoPaciente(ambulancia));
    }

    /**
     * Retorna al estado disponible al finalizar el regreso.
     */
    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Cambio a disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    /**
     * Rechaza la solicitud de mantenimiento mientras se encuentra en regreso.
     */
    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras regresa sin paciente");

    }

}
