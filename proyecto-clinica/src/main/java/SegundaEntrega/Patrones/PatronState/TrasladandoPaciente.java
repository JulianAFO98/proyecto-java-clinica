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
     */
    public TrasladandoPaciente(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Ignora nuevas solicitudes domiciliarias durante el traslado.
     */
    @Override
    public void solicitarAtencionDomicilio() {
    }

    /**
     * Rechaza un segundo traslado mientras el actual no finaliza.
     */
    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("No puede solicitar traslado mientras ya esta trasladando un paciente");
    }

    /**
     * Cambia a disponible cuando concluye el traslado.
     */
    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Cambio a disponible");
        ambulancia.setEstado(new Disponible(ambulancia));
    }

    /**
     * Rechaza la solicitud de mantenimiento hasta finalizar el traslado.
     */
    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras esta trasladando un paciente");

    }
}
