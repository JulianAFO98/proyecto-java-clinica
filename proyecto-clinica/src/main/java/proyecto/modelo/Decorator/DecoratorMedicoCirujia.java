package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

/**
 * Clase que representa un decorador para un médico con especialidad en cirugía.
 * Extiende la clase DecoratorMedico y añade funcionalidad específica para médicos cirujanos.
 */
public class DecoratorMedicoCirujia extends DecoratorMedico{
    
    /**
     * Constructor que recibe un objeto IMedico para decorar.
     * 
     * @param medico El objeto IMedico a ser decorado.
     */
    public DecoratorMedicoCirujia(IMedico medico) {
    	super(medico);
	}

    @Override
	public String getEspecialidad() {
		return "Cirujia";
	}

    /**
     * Calcula el sueldo del médico cirujano, aplicando un incremento del 10% al sueldo base.
     * @return El sueldo calculado del médico cirujano.
     */
    @Override
    public double calcularSueldo() {
        double aux = this.encapsulado.calcularSueldo();
        return aux*1.1;
    }
}
