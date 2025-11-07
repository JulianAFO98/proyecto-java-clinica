package SegundaEntrega.Patrones.PatronState;

public interface EstadoAmbulancia {
    void solicitarAtencionDomicilio();

    void solicitarTrasladoClinica();

    void retornoAutomatico();

    void solicitarMantenimiento();
}
