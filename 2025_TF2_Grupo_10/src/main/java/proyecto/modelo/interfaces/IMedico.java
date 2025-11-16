package proyecto.modelo.interfaces;

/**
 * Interfaz que define el comportamiento de un médico en el sistema.
 */
public interface IMedico extends IPersona{
    double calcularSueldo();
    String getEspecialidad();
    int getNumeroMatricula();
}
