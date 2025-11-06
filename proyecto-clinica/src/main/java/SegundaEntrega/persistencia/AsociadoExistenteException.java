package SegundaEntrega.persistencia;

public class AsociadoExistenteException extends Exception{
    private String dni;

    public AsociadoExistenteException(String message, String dni) {
        super(message);
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    
    
}
