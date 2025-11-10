package SegundaEntrega.modelo.Datos;

public class Operario implements Runnable {
    private Ambulancia ambulancia;

    public Operario(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void run() {
        if (this.ambulancia.isSimulacionActiva()) {
            this.ambulancia.setSolicitaOperario(true);
            this.ambulancia.solicitarAmbulanciaOperario(this);
            this.ambulancia.liberarAmbulanciaOperario(this);
        }
    }

}
