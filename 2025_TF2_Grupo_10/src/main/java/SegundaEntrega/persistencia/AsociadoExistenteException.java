package SegundaEntrega.persistencia;

/**
 * Excepcion especifica para indicar duplicidad de asociados por dni.
 */
public class AsociadoExistenteException extends Exception{
    private String dni;

    /**
     * Construye la excepcion guardando el dni conflictivo.
     *
     * @param message detalle a mostrar
     * <br>Pre: message != null y no vacio
     * <br>Post: El mensaje se pasa a la clase base Exception.
     * @param dni documento repetido
     * <br>Pre: dni != null y no vacio
     * <br>Post: this.dni = dni
     */
    public AsociadoExistenteException(String message, String dni) {
        super(message);
    	// Precondiciones
        assert message != null && !message.trim().isEmpty() : "El mensaje de la excepcion no debe ser nulo o vacio.";
        assert dni != null && !dni.trim().isEmpty() : "El DNI conflictivo no debe ser nulo o vacio.";
       
        this.dni = dni;
        
        // Postcondicion
        assert this.dni.equals(dni) : "El DNI no se asigno correctamente en la excepcion.";
    }

    /**
     * Devuelve el dni implicado en el conflicto.
     *
     * @return documento repetido
     * <br>Pre: No aplica
     * <br>Post: Retorna el dni almacenado.
     */
    public String getDni() {
        assert dni != null && !dni.trim().isEmpty() : "El DNI de la excepcion no debe ser nulo o vacio.";
        return dni;
    }
}