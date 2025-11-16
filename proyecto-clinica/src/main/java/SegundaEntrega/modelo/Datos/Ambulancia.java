package SegundaEntrega.modelo.Datos;

import java.util.Observable;

import SegundaEntrega.Patrones.PatronState.Disponible;
import SegundaEntrega.Patrones.PatronState.EstadoAmbulancia;

/**
 * Representa el recurso compartido Ambulancia aplicando el patron State y Observable.
 */
public class Ambulancia extends Observable {
    private EstadoAmbulancia estado;
    private boolean simulacionActiva = false;
    private boolean ambulanciaEnUso = false;
    private boolean solicitaOperario = false;

    /**
     * Inicializa la ambulancia con el estado disponible.
     */
    public Ambulancia() {
        this.estado = new Disponible(this);
    }

    /**
     * Gestiona la atencion para un asociado respetando la sincronizacion del recurso.
     *
     * @param asociado solicitante que requiere la ambulancia
     */
    public synchronized void ejecutarAmbulancia(Asociado asociado) {
        // Accion concurrente de la ambulancia
        if (simulacionActiva) {
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

    /**
     * Libera la ambulancia luego de servir a un asociado.
     *
     * @param a parametro sin uso que mantiene compatibilidad con el observer
     */
    public synchronized void liberarAmbulancia(Object a) {
        this.ambulanciaEnUso = false;
        this.retornoAutomatico();
        notifyAll();
    }

    /**
     * Define la estrategia a ejecutar segun el estado del asociado.
     *
     * @param asociado solicitante evaluado
     */
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

    /**
     * Atiende la solicitud de mantenimiento emitida por el operario.
     *
     * @param op operario que dispara el pedido
     */
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

    /**
     * Finaliza la intervencion del operario y reactiva la ambulancia.
     *
     * @param p operario que libera el recurso
     */
    public synchronized void liberarAmbulanciaOperario(Operario p) {
        this.notificarCambio("Operario finalizando mantenimiento.");
        this.setSolicitaOperario(false);
        this.ambulanciaEnUso = false;
        this.solicitarMantenimiento();
        notifyAll();
    }

    /**
     * Indica si la simulacion se encuentra activa.
     *
     * @return true cuando la simulacion esta en ejecucion
     */
    public boolean isSimulacionActiva() {
        return simulacionActiva;
    }

    /**
     * Cambia el estado de la ambulancia.
     *
     * @param estado nuevo estado a aplicar
     */
    public void setEstado(EstadoAmbulancia estado) {
        this.estado = estado;
        System.out.println("Estado de la ambulancia cambiado a: " + estado);
    }

    /**
     * Solicita atencion domiciliaria mediante el estado actual.
     */
    public void solicitarAtencionDomicilio() {
        estado.solicitarAtencionDomicilio();
    }

    /**
     * Solicita traslado a clinica mediante el estado actual.
     */
    public void solicitarTrasladoClinica() {
        estado.solicitarTrasladoClinica();
    }

    /**
     * Realiza el retorno automatico segun el estado configurado.
     */
    public void retornoAutomatico() {
        estado.retornoAutomatico();
    }

    /**
     * Habilita o frena la simulacion.
     *
     * @param simulacionActiva indicador de estado global
     */
    public void setSimulacionActiva(boolean simulacionActiva) {
        this.simulacionActiva = simulacionActiva;
    }

    /**
     * Solicita mantenimiento utilizando el estado actual.
     */
    public  void solicitarMantenimiento() {
        estado.solicitarMantenimiento();
    }

    /**
     * Notifica al observer acerca de un cambio relevante.
     *
     * @param mensaje detalle del evento a propagar
     */
    public void notificarCambio(String mensaje) {
        setChanged();
        notifyObservers(mensaje);
    }

    /**
     * Define si el operario mantiene retenida la ambulancia.
     *
     * @param solicitaOperario true cuando el operario requiere el recurso
     */
    public void setSolicitaOperario(boolean solicitaOperario) {
        this.solicitaOperario = solicitaOperario;
    }

}
