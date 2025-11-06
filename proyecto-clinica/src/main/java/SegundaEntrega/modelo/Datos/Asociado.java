package SegundaEntrega.modelo.Datos;

public class Asociado extends Thread {
    private int id;
    private String dni;
    private boolean alta;

    public Asociado(String name, int id, String dni, boolean alta) {
        super(name);
        this.id = id;
        this.dni = dni;
        this.alta = alta;
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

    @Override
    public String toString() {
        return getName() + "(id " + id + ") (dni: " + dni + ") alta: (" + alta + ")";
    }

}
