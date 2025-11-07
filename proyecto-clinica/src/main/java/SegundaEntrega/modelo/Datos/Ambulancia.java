package SegundaEntrega.modelo.Datos;

import SegundaEntrega.Patrones.PatronState.Disponible;
import SegundaEntrega.Patrones.PatronState.EstadoAmbulancia;

public class Ambulancia {
    private EstadoAmbulancia estado;

    public Ambulancia() {
        this.estado = new Disponible(this);
    }

    public void setEstado(EstadoAmbulancia estado) {
        this.estado = estado;
    }

    public void solicitarAtencionDomicilio() {
        estado.solicitarAtencionDomicilio();
    }

    public void solicitarTrasladoClinica() {
        estado.solicitarTrasladoClinica();
    }

    public void retornoAutomatico() {
        estado.retornoAutomatico();
    }

    public void solicitarMantenimiento() {
        estado.solicitarMantenimiento();
    }

}
