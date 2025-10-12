package proyecto.modelo.Excepciones;

/**
 * Excepción lanzada cuando no se encuentra un paciente en el sistema.
 */
public class NoExistePacienteException extends Exception {
    /**
     * Constructor de la excepción con un mensaje personalizado.
     * @param msg Mensaje que describe el error.
     */    
    public NoExistePacienteException(String msg){
            super(msg);
        }
}
