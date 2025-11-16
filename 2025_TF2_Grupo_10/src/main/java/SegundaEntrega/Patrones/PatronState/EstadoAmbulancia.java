package SegundaEntrega.Patrones.PatronState;

/**
 * Define las operaciones del estado de una ambulancia dentro del patron State.
 */
public interface EstadoAmbulancia {

    /**
     * Atiende la solicitud de atencion domiciliaria.
     */
    void solicitarAtencionDomicilio();

    /**
     * Gestiona el traslado de un asociado a la clinica.
     */
    void solicitarTrasladoClinica();

    /**
     * Ordena el retorno de la ambulancia a la base.
     */
    void retornoAutomatico();

    /**
     * Solicita la operacion de mantenimiento del vehiculo.
     */
    void solicitarMantenimiento();
}
