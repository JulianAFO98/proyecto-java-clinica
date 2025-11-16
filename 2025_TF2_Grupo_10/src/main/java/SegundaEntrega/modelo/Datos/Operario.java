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
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public Operario(Ambulancia ambulancia) {
        // Precondicion
        assert ambulancia != null : "La ambulancia no puede ser nula.";

        this.ambulancia = ambulancia;

        // Postcondicion
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Ejecuta el ciclo de mantenimiento sobre la ambulancia cuando la simulacion esta activa.
     * <br>Pre: this.ambulancia != null
     * <br>Post: Si la simulacion esta activa, se completa el ciclo de solicitud y liberacion de la ambulancia.
     */
    @Override
    public void run() {
        // Invariante
        assert this.ambulancia != null : "La ambulancia no debe ser nula durante la ejecucion.";

        if (this.ambulancia.isSimulacionActiva()) {
            
            // Postcondicion parcial: El operario solicita el recurso.
            this.ambulancia.setSolicitaOperario(true);
            assert this.ambulancia.isSolicitaOperario() : "El flag solicitaOperario debe ser true.";

            // Postcondicion parcial: El operario espera y obtiene la ambulancia.
            this.ambulancia.solicitarAmbulanciaOperario(this);

            // Postcondicion final: El operario libera la ambulancia y finaliza el ciclo.
            this.ambulancia.liberarAmbulanciaOperario(this);
            assert !this.ambulancia.isSolicitaOperario() : "El flag solicitaOperario debe ser false al finalizar.";
        }
    }

}