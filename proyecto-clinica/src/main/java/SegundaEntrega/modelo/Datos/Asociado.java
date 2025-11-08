package SegundaEntrega.modelo.Datos;

import java.util.Random;

public class Asociado implements Runnable {
    private int id;
    private String dni;
    private boolean alta;
    private String name;
    private Ambulancia ambulancia;
    private String estadoAsociado; // Nuevo atributo para el estado del asociado

    public Asociado(String name, int id, String dni, boolean alta, Ambulancia ambulancia) {
        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.name = name;
        this.ambulancia = ambulancia;
        Random r = new Random();
        this.estadoAsociado = (r.nextInt(2) == 0)
                ? "ATENCION_DOMICILIO"
                : "TRASLADO_CLINICA";
    }

    @Override
    public void run() {
        System.out.println("Ejecutando hilo de asociado: " + name);
        while (ambulancia.isSimulacionActiva()) {
            try {
                ambulancia.ejecutarAmbulancia(this); // Ejemplo de llamada a un metodo de Ambulancia
                Thread.sleep(2000); // Simula trabajo con una pausa
                ambulancia.liberarAmbulancia(this);
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

    public long getId() {
        return (long) id;
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

}
