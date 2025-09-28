package proyecto.modelo.interfaces;

public interface IMedico extends IPersona{
    double calcularSueldo();
    String getEspecialidad();
    int getNumeroMatricula();
}
