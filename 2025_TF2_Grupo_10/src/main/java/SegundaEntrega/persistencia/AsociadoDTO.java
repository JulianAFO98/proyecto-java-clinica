package SegundaEntrega.persistencia;

/**
 * DTO utilizado para transportar datos de Asociado entre capas.
 */
public class AsociadoDTO {
    private int id;
    private String dni;
    private boolean alta;
    private String nombre;

    /**
     * Crea un dto con todos los campos incluyendo el identificador.
     *
     * @param id identificador unico
     * @param dni documento del asociado
     * @param alta estado de alta
     * @param nombre nombre completo
     */
    public AsociadoDTO(int id,String dni, boolean alta, String nombre) {
        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.nombre = nombre;
    }
    /**
     * Crea un dto sin identificador para operaciones de insercion.
     *
     * @param dni documento del asociado
     * @param alta estado de alta
     * @param nombre nombre completo
     */
     public AsociadoDTO(String dni, boolean alta, String nombre) {
        this.dni = dni;
        this.alta = alta;
        this.nombre = nombre;
    }

    
    /**
     * Obtiene el documento asociado.
     *
     * @return cadena con dni
     */
    public String getDni() {
        return dni;
    }
    /**
     * Define el documento del asociado.
     *
     * @param dni nuevo documento
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    /**
     * Indica si el asociado esta dado de alta.
     *
     * @return true si tiene alta vigente
     */
    public boolean isAlta() {
        return alta;
    }
    /**
     * Actualiza el estado de alta.
     *
     * @param alta estado solicitado
     */
    public void setAlta(boolean alta) {
        this.alta = alta;
    }
    /**
     * Devuelve el nombre del asociado.
     *
     * @return nombre almacenado
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Ajusta el nombre del asociado.
     *
     * @param nombre texto a registrar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Devuelve el identificador del registro.
     *
     * @return id del asociado
     */
    public int getId() {
        return id;
    }


    /**
     * Establece el identificador del registro.
     *
     * @param id valor entero
     */
    public void setId(int id) {
        this.id = id;
    }

    
    
}
