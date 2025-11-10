package SegundaEntrega.modelo.Datos;

import java.util.Observable;

import SegundaEntrega.Patrones.PatronState.Disponible;
import SegundaEntrega.Patrones.PatronState.EstadoAmbulancia;

public class Ambulancia extends Observable {
    private EstadoAmbulancia estado;
    private boolean simulacionActiva = false;
    private boolean ambulanciaEnUso = false;
    private boolean solicitaOperario = false;

    public Ambulancia() {
        this.estado = new Disponible(this);
    }

    public synchronized void ejecutarAmbulancia(Asociado asociado) {
        // Accion concurrente de la ambulancia
        if (simulacionActiva) {
            System.out.println("Ejecutando ambulancia para el asociado: " + asociado.getName());
            while ((ambulanciaEnUso || solicitaOperario) && simulacionActiva) {
                try {
                    notificarCambio("Ambulancia ocupada, asociado " + asociado.getName() + " esperando...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            ambulanciaEnUso = true;
            resolverSolicitud(asociado);
        }

    }

    public synchronized void liberarAmbulancia(Object a) {
        this.ambulanciaEnUso = false;
        this.retornoAutomatico();
        notifyAll();
    }

    private synchronized void resolverSolicitud(Asociado asociado) {
        String estadoAsociado = asociado.getEstadoAsoociado();
        System.out.println("Resolviendo solicitud para el asociado: " + asociado.getName());
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

    public synchronized void solicitarAmbulanciaOperario(Operario op) {
        this.notificarCambio("Operario solicita mantenimiento, esperando que se libere...");
        while (this.ambulanciaEnUso && this.simulacionActiva) {
            try {
                wait();
                System.out.println(this.ambulanciaEnUso);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (this.isSimulacionActiva()) {
            this.solicitarMantenimiento();
        }
    }

    public synchronized void liberarAmbulanciaOperario(Operario p) {
        this.notificarCambio("Operario finalizando mantenimiento.");
        this.setSolicitaOperario(false);
        this.ambulanciaEnUso = false;
        this.solicitarMantenimiento();
        notifyAll();
    }

    public boolean isSimulacionActiva() {
        return simulacionActiva;
    }

    public void setEstado(EstadoAmbulancia estado) {
        this.estado = estado;
        System.out.println("Estado de la ambulancia cambiado a: " + estado);
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

    public void setSimulacionActiva(boolean simulacionActiva) {
        this.simulacionActiva = simulacionActiva;
    }

    public  void solicitarMantenimiento() {
        estado.solicitarMantenimiento();
    }

    public void notificarCambio(String mensaje) {
        setChanged();
        notifyObservers(mensaje);
    }

    public void setSolicitaOperario(boolean solicitaOperario) {
        this.solicitaOperario = solicitaOperario;
    }

}
