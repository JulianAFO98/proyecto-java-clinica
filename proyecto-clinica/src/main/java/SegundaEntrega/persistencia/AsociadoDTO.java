package SegundaEntrega.persistencia;

public class AsociadoDTO {
    private int id;
    private String dni;
    private boolean alta;
    private String nombre;
    public AsociadoDTO(String dni, boolean alta, String nombre) {
        this.dni = dni;
        this.alta = alta;
        this.nombre = nombre;
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
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    
    
}
