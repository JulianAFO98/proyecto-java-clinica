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
     */
    public EnTaller(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Rechaza solicitudes domiciliarias mientras permanece en taller.
     */
    @Override
    public void solicitarAtencionDomicilio() {
        ambulancia.notificarCambio("En taller, no se puede atender a domicilio");
    }

    /**
     * Rechaza los traslados a clinica durante el mantenimiento.
     */
    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("En taller, no se puede trasladar a clinica");
    }

    /**
     * Indica que la ambulancia continua en taller.
     */
    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Continua en taller");
    }

    /**
     * Marca la finalizacion del mantenimiento y pasa al regreso desde el taller.
     */
    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("Mantenimiento completado, regresando del taller");
        ambulancia.setEstado(new RegresandoDelTaller(ambulancia));
    }
}
