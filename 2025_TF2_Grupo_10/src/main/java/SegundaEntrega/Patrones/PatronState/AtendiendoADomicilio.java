package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

/**
 * Estado que modela la atencion domiciliaria en curso.
 */
public class AtendiendoADomicilio  implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    /**
     * Crea el estado de atencion domiciliaria.
     *
     * @param ambulancia referencia que cambia de estado
     */
    public AtendiendoADomicilio(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Ignora nuevas solicitudes de atencion domiciliaria porque ya esta atendiendo.
     */
    @Override
    public void solicitarAtencionDomicilio() {
    }

    /**
     * Rechaza el traslado debido a la atencion actual.
     */
    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("No puede solicitar traslado mientras atiende a domicilio");
       
    }

    /**
     * Cambia al estado de regreso sin paciente.
     */
    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Regresando sin paciente");
        ambulancia.setEstado(new RegresandoSinPaciente(ambulancia));
    }

    /**
     * Rechaza el mantenimiento hasta finalizar la atencion.
     */
    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras atiende a domicilio");
       
    }
}
