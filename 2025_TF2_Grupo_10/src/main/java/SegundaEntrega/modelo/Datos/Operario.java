package SegundaEntrega.modelo.Datos;

/**
 * Modela al operario encargado del mantenimiento de la ambulancia.
 */
public class Operario implements Runnable {
    private Ambulancia ambulancia;

    /**
     * Construye un operario vinculado a una ambulancia.
     *
     * @param ambulancia recurso sujeto a mantenimiento
     */
    public Operario(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Ejecuta el ciclo de mantenimiento sobre la ambulancia cuando la simulacion esta activa.
     */
    @Override
    public void run() {
        if (this.ambulancia.isSimulacionActiva()) {
            this.ambulancia.setSolicitaOperario(true);
            this.ambulancia.solicitarAmbulanciaOperario(this);
            this.ambulancia.liberarAmbulanciaOperario(this);
        }
    }

}
