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
     * @param dni documento repetido
     */
    public AsociadoExistenteException(String message, String dni) {
        super(message);
        this.dni = dni;
    }

    /**
     * Devuelve el dni implicado en el conflicto.
     *
     * @return documento repetido
     */
    public String getDni() {
        return dni;
    }

    
    
}
