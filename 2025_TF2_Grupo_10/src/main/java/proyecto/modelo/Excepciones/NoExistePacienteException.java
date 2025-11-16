package proyecto.modelo.Excepciones;

/**
 * Excepcion lanzada cuando no se encuentra un paciente en el sistema.
 */
public class NoExistePacienteException extends Exception {
    /**
     * Constructor de la excepcion con un mensaje personalizado.
     * @param msg Mensaje que describe el error.
     */    
    public NoExistePacienteException(String msg){
    	super(msg);
        assert (msg == null && this.getMessage() == null) || (msg != null && msg.equals(this.getMessage())) : "El mensaje de la excepcion no se asigno correctamente.";
    }
}