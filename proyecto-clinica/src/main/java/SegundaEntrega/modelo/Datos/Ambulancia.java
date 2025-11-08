package SegundaEntrega.modelo.Datos;

import java.util.Observable;

import SegundaEntrega.Patrones.PatronState.Disponible;
import SegundaEntrega.Patrones.PatronState.EstadoAmbulancia;

public class Ambulancia extends Observable {
    private EstadoAmbulancia estado;
    private boolean simulacionActiva = false;
    private boolean ambulanciaEnUso = false;

    public Ambulancia() {
        this.estado = new Disponible(this);
    }

    public synchronized void ejecutarAmbulancia(Asociado asociado) {
        // Accion concurrente de la ambulancia
        if (!simulacionActiva) {
            while (ambulanciaEnUso) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            ambulanciaEnUso = true;
            resolverSolicitud(asociado);
            // Logica de la ambulancia aqui
        }
    }

    private void resolverSolicitud(Asociado asociado) {
        // Lógica para llevar al asociado
        String estadoAsociado = asociado.getEstadoAsoociado();
        switch (estadoAsociado) {
            case "ATENCION_DOMICILIO":
                solicitarAtencionDomicilio();
                break;
            case "TRASLADO_CLINICA":
                solicitarTrasladoClinica();
                break;
            default:
                System.out.println("Estado desconocido para el asociado: " + estadoAsociado);
        }

    }

    public boolean isSimulacionActiva() {
        return simulacionActiva;
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
