package proyecto.modelo.Decorator;
import proyecto.modelo.interfaces.IMedico;

/**
 * DecoratorMedicoClinico
 * Clase que representa un decorador para un medico clinico
 */
public class DecoratorMedicoClinico extends DecoratorMedico{

    /**
     * Constructor de la clase DecoratorMedicoClinico
     * @param medico medico a decorar
     */
    public DecoratorMedicoClinico(IMedico medico) {
    	super(medico);
	}

    @Override
	public String getEspecialidad() {
		return "Clinico";
	}

   /**
     * Calcula el sueldo del médico clinico, aplicando un incremento del 5% al sueldo base.
     * @return El sueldo calculado del médico clinico.
     */
    @Override
    public double calcularSueldo() {
        double aux = this.encapsulado.calcularSueldo();
        return aux*1.05;
    }


    
}
