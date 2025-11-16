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
     * <br>Pre: id >= 0
     * <br>Post: this.id = id
     * @param dni documento del asociado
     * <br>Pre: dni != null y no vacio
     * <br>Post: this.dni = dni
     * @param alta estado de alta
     * <br>Pre: No aplica
     * <br>Post: this.alta = alta
     * @param nombre nombre completo
     * <br>Pre: nombre != null y no vacio
     * <br>Post: this.nombre = nombre
     */
    public AsociadoDTO(int id,String dni, boolean alta, String nombre) {
        // Precondiciones
        assert id >= 0 : "El ID debe ser no negativo.";
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        assert nombre != null && !nombre.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        
        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.nombre = nombre;
        
        // Postcondiciones
        assert this.id == id : "El ID no se asigno correctamente.";
        assert this.dni.equals(dni) : "El DNI no se asigno correctamente.";
        assert this.nombre.equals(nombre) : "El nombre no se asigno correctamente.";
    }
    
    /**
     * Crea un dto sin identificador para operaciones de insercion.
     *
     * @param dni documento del asociado
     * <br>Pre: dni != null y no vacio
     * <br>Post: this.dni = dni
     * @param alta estado de alta
     * <br>Pre: No aplica
     * <br>Post: this.alta = alta
     * @param nombre nombre completo
     * <br>Pre: nombre != null y no vacio
     * <br>Post: this.nombre = nombre
     */
     public AsociadoDTO(String dni, boolean alta, String nombre) {
        // Precondiciones
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        assert nombre != null && !nombre.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";

        this.dni = dni;
        this.alta = alta;
        this.nombre = nombre;
        
        // Postcondiciones
        assert this.dni.equals(dni) : "El DNI no se asigno correctamente.";
        assert this.nombre.equals(nombre) : "El nombre no se asigno correctamente.";
    }

    
    /**
     * Obtiene el documento asociado.
     *
     * @return cadena con dni
     * <br>Pre: No aplica
     * <br>Post: Retorna una cadena no nula y no vacia.
     */
    public String getDni() {
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        return dni;
    }
    
    /**
     * Define el documento del asociado.
     *
     * @param dni nuevo documento
     * <br>Pre: dni != null y no vacio
     * <br>Post: this.dni = dni
     */
    public void setDni(String dni) {
        assert dni != null && !dni.trim().isEmpty() : "El nuevo DNI no debe ser nulo o vacio.";
        this.dni = dni;
        assert this.dni.equals(dni) : "El DNI no se asigno correctamente.";
    }
    
    /**
     * Indica si el asociado esta dado de alta.
     *
     * @return true si tiene alta vigente
     * <br>Pre: No aplica
     * <br>Post: No modifica el estado.
     */
    public boolean isAlta() {
        return alta;
    }
    
    /**
     * Actualiza el estado de alta.
     *
     * @param alta estado solicitado
     * <br>Pre: No aplica
     * <br>Post: this.alta = alta
     */
    public void setAlta(boolean alta) {
        this.alta = alta;
        assert this.alta == alta : "El estado de alta no se asigno correctamente.";
    }
    
    /**
     * Devuelve el nombre del asociado.
     *
     * @return nombre almacenado
     * <br>Pre: No aplica
     * <br>Post: Retorna una cadena no nula y no vacia.
     */
    public String getNombre() {
        assert nombre != null && !nombre.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        return nombre;
    }
    
    /**
     * Ajusta el nombre del asociado.
     *
     * @param nombre texto a registrar
     * <br>Pre: nombre != null y no vacio
     * <br>Post: this.nombre = nombre
     */
    public void setNombre(String nombre) {
        assert nombre != null && !nombre.trim().isEmpty() : "El nuevo nombre no debe ser nulo o vacio.";
        this.nombre = nombre;
        assert this.nombre.equals(nombre) : "El nombre no se asigno correctamente.";
    }


    /**
     * Devuelve el identificador del registro.
     *
     * @return id del asociado
     * <br>Pre: No aplica
     * <br>Post: Retorna un valor >= 0.
     */
    public int getId() {
        assert id >= 0 : "El ID debe ser no negativo.";
        return id;
    }


    /**
     * Establece el identificador del registro.
     *
     * @param id valor entero
     * <br>Pre: id >= 0
     * <br>Post: this.id = id
     */
    public void setId(int id) {
        assert id >= 0 : "El ID debe ser no negativo.";
        this.id = id;
        assert this.id == id : "El ID no se asigno correctamente.";
    }
    
    
}