package SegundaEntrega.modelo.Datos;

import java.util.Random;

public class Asociado implements Runnable {
    private int id;
    private String dni;
    private boolean alta;
    private String name;
    private Ambulancia ambulancia;
    private String estadoAsociado; // Nuevo atributo para el estado del asociado
    private int cantidadAtenciones;
    private final Random random = new Random();

    public Asociado(String name, int id, String dni, boolean alta, Ambulancia ambulancia) {
        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.name = name;
        this.ambulancia = ambulancia;
        this.cantidadAtenciones = 0;
        this.estadoAsociado = "ATENCION_DOMICILIO";
    }

    @Override
    public void run() {
        while (ambulancia.isSimulacionActiva() && cantidadAtenciones > 0) {
            try {
                this.estadoAsociado = random.nextBoolean() ? "ATENCION_DOMICILIO" : "TRASLADO_CLINICA";
                ambulancia.ejecutarAmbulancia(this); // Ejemplo de llamada a un metodo de Ambulancia
                Thread.sleep(1500); // Simula trabajo con una pausa
                ambulancia.liberarAmbulancia(this);
                cantidadAtenciones--;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String getEstadoAsoociado() {
        return estadoAsociado;
    }

    public void setAmbulancia(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    public int getId() {
        return  id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + "(id " + id + ") (dni: " + dni + ") alta: (" + alta + ")";
    }

    public int getCantidadAtenciones() {
        return cantidadAtenciones;
    }

    public void setCantidadAtenciones(int cantidadAtenciones) {
        this.cantidadAtenciones = cantidadAtenciones;
    }

    
}
