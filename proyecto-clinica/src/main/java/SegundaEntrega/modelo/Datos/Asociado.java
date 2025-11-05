package SegundaEntrega.modelo.Datos;

public class Asociado extends Thread{
    private int id;
    private String dni;
    private boolean alta;

    
    public Asociado(String name, int id, String dni, boolean alta) {
        super(name);
        this.id = id;
        this.dni = dni;
        this.alta = alta;
    }


    @Override
    public String toString() {
        return getName()+"(id " + id + ") (dni: " + dni + ") alta: (" + alta+")";
    }


    

}
