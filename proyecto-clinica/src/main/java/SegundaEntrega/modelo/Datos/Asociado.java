package SegundaEntrega.modelo.Datos;

public class Asociado implements Runnable {
    private int id;
    private String dni;
    private boolean alta;
    private String name;
    private Ambulancia ambulancia;

    public Asociado( String name, int id, String dni, boolean alta, Ambulancia ambulancia) {
        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.name = name;
        this.ambulancia = ambulancia;
    }

    @Override
    public void run() {
        // Lógica que se ejecutará en el hilo
        ambulancia = new Ambulancia();
        System.out.println("Ejecutando hilo de asociado: " + name);
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
