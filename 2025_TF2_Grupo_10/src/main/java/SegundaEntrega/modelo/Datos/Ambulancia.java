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
     * <br>Pre: La clase Disponible debe existir.
     * <br>Post: El estado se inicializa a Disponible y los flags logicos a false.
     */
    public Ambulancia() {
        this.estado = new Disponible(this);
        // Postcondiciones
        assert this.estado != null : "El estado de la ambulancia no debe ser nulo al inicializar.";
        assert this.estado.getClass().getSimpleName().equals("Disponible") : "El estado inicial debe ser Disponible.";
        assert !this.simulacionActiva : "Simulacion activa debe ser false al inicializar.";
        assert !this.ambulanciaEnUso : "Ambulancia en uso debe ser false al inicializar.";
    }

    /**
     * Gestiona la atencion para un asociado respetando la sincronizacion del recurso.
     *
     * @param asociado solicitante que requiere la ambulancia
     * <br>Pre: asociado != null
     * <br>Post: La ambulancia atiende la solicitud o pone en espera al asociado. ambulanciaEnUso = true si es atendido.
     */
    public synchronized void ejecutarAmbulancia(Asociado asociado) {
        // Precondicion
        assert asociado != null : "El asociado no puede ser nulo.";
        
        // Accion concurrente de la ambulancia
        if (simulacionActiva) {
            
            // Invariante de ciclo: antes de entrar a esperar, debe estar en uso o solicitada por el operario
            assert !ambulanciaEnUso || !solicitaOperario || !simulacionActiva : "La ambulancia deberia estar disponible si se va a salir del while.";

            while ((ambulanciaEnUso || solicitaOperario) && simulacionActiva) {
                try {
                    notificarCambio("Ambulancia ocupada, asociado " + asociado.getName() + " esperando...");
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    // Invariante de interrupcion
                    assert Thread.currentThread().isInterrupted() : "El thread deberia estar interrumpido despues de manejar la excepcion.";
                }
            }
            
            // Postcondicion: si sale del bucle, la ambulancia se marca como en uso.
            this.ambulanciaEnUso = true;
            assert this.ambulanciaEnUso : "La ambulancia debe marcarse como en uso despues de la espera.";

            resolverSolicitud(asociado);
        }
        // Invariante general: el estado nunca es nulo
        assert this.estado != null : "El estado de la ambulancia nunca debe ser nulo.";
    }

    /**
     * Libera la ambulancia luego de servir a un asociado.
     *
     * @param a parametro sin uso que mantiene compatibilidad con el observer
     * <br>Pre: ambulanciaEnUso = true
     * <br>Post: ambulanciaEnUso = false, se llama a retornoAutomatico() y se notifica a los threads en espera.
     */
    public synchronized void liberarAmbulancia(Object a) {
        // Precondicion
        assert this.ambulanciaEnUso : "Solo se puede liberar si la ambulancia estaba en uso.";
        
        this.ambulanciaEnUso = false;
        this.retornoAutomatico();
        notifyAll();
        
        // Postcondicion
        assert !this.ambulanciaEnUso : "La ambulancia debe quedar marcada como no en uso.";
    }

    /**
     * Define la estrategia a ejecutar segun el estado del asociado.
     *
     * @param asociado solicitante evaluado
     * <br>Pre: asociado != null y asociado.getEstadoAsoociado() es un estado valido.
     * <br>Post: Se llama al metodo correspondiente en el objeto estado.
     */
    private synchronized void resolverSolicitud(Asociado asociado) {
        // Precondicion
        assert asociado != null : "El asociado no puede ser nulo.";
        assert asociado.getEstadoAsoociado() != null : "El estado del asociado no debe ser nulo.";

        String estadoAsociado = asociado.getEstadoAsoociado();
        // Invariante: el estado de la ambulancia nunca es nulo.
        assert this.estado != null : "El estado de la ambulancia no debe ser nulo para resolver la solicitud.";
        
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
     * <br>Pre: op != null
     * <br>Post: Si la ambulancia esta activa y libre, se llama a solicitarMantenimiento().
     */
    public synchronized void solicitarAmbulanciaOperario(Operario op) {
        // Precondicion
        assert op != null : "El operario no puede ser nulo.";

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
        // Postcondicion: si sale del while y la simulacion esta activa, se llamo a solicitarMantenimiento.
    }

    /**
     * Finaliza la intervencion del operario y reactiva la ambulancia.
     *
     * @param p operario que libera el recurso
     * <br>Pre: p != null
     * <br>Post: solicitaOperario = false, ambulanciaEnUso = false y se notifica a los threads en espera.
     */
    public synchronized void liberarAmbulanciaOperario(Operario p) {
        // Precondicion
        assert p != null : "El operario no puede ser nulo.";

        this.notificarCambio("Operario finalizando mantenimiento.");
        this.setSolicitaOperario(false);
        this.ambulanciaEnUso = false;
        this.solicitarMantenimiento();
        notifyAll();
        
        // Postcondicion
        assert !this.solicitaOperario : "El flag solicitaOperario debe ser false.";
        assert !this.ambulanciaEnUso : "La ambulancia debe quedar marcada como no en uso.";
    }

    /**
     * Indica si la simulacion se encuentra activa.
     *
     * @return true cuando la simulacion esta en ejecucion
     * <br>Pre: No aplica
     * <br>Post: no se modifica el estado interno del objeto.
     */
    public boolean isSimulacionActiva() {
        return simulacionActiva;
    }

    /**
     * Cambia el estado de la ambulancia.
     *
     * @param estado nuevo estado a aplicar
     * <br>Pre: estado != null
     * <br>Post: this.estado = estado
     */
    public void setEstado(EstadoAmbulancia estado) {
        // Precondicion
        assert estado != null : "El nuevo estado no puede ser nulo.";

        this.estado = estado;
        System.out.println("Estado de la ambulancia cambiado a: " + estado);
        
        // Postcondicion
        assert this.estado == estado : "El estado no se asigno correctamente.";
    }

    /**
     * Solicita atencion domiciliaria mediante el estado actual.
     * <br>Pre: this.estado != null
     * <br>Post: Se llama al metodo solicitarAtencionDomicilio() del estado actual.
     */
    public void solicitarAtencionDomicilio() {
        // Precondicion
        assert this.estado != null : "El estado no debe ser nulo para llamar al metodo.";
        estado.solicitarAtencionDomicilio();
    }

    /**
     * Solicita traslado a clinica mediante el estado actual.
     * <br>Pre: this.estado != null
     * <br>Post: Se llama al metodo solicitarTrasladoClinica() del estado actual.
     */
    public void solicitarTrasladoClinica() {
        // Precondicion
        assert this.estado != null : "El estado no debe ser nulo para llamar al metodo.";
        estado.solicitarTrasladoClinica();
    }

    /**
     * Realiza el retorno automatico segun el estado configurado.
     * <br>Pre: this.estado != null
     * <br>Post: Se llama al metodo retornoAutomatico() del estado actual.
     */
    public void retornoAutomatico() {
        // Precondicion
        assert this.estado != null : "El estado no debe ser nulo para llamar al metodo.";
        estado.retornoAutomatico();
    }

    /**
     * Habilita o frena la simulacion.
     *
     * @param simulacionActiva indicador de estado global
     * <br>Pre: No aplica
     * <br>Post: this.simulacionActiva = simulacionActiva
     */
    public void setSimulacionActiva(boolean simulacionActiva) {
        this.simulacionActiva = simulacionActiva;
        // Postcondicion
        assert this.simulacionActiva == simulacionActiva : "El flag de simulacion no se asigno correctamente.";
    }

    /**
     * Solicita mantenimiento utilizando el estado actual.
     * <br>Pre: this.estado != null
     * <br>Post: Se llama al metodo solicitarMantenimiento() del estado actual.
     */
    public void solicitarMantenimiento() {
        // Precondicion
        assert this.estado != null : "El estado no debe ser nulo para llamar al metodo.";
        estado.solicitarMantenimiento();
    }

    /**
     * Notifica al observer acerca de un cambio relevante.
     *
     * @param mensaje detalle del evento a propagar
     * <br>Pre: mensaje != null
     * <br>Post: Los observers son notificados con el mensaje.
     */
    public void notificarCambio(String mensaje) {
        // Precondicion
        assert mensaje != null : "El mensaje a notificar no debe ser nulo.";
        setChanged();
        notifyObservers(mensaje);
    }

    /**
     * Define si el operario mantiene retenida la ambulancia.
     *
     * @param solicitaOperario true cuando el operario requiere el recurso
     * <br>Pre: No aplica
     * <br>Post: this.solicitaOperario = solicitaOperario
     */
    public void setSolicitaOperario(boolean solicitaOperario) {
        this.solicitaOperario = solicitaOperario;
        // Postcondicion
        assert this.solicitaOperario == solicitaOperario : "El flag solicitaOperario no se asigno correctamente.";
    }

	public boolean isSolicitaOperario() {
		return solicitaOperario;
	}
    
    

}