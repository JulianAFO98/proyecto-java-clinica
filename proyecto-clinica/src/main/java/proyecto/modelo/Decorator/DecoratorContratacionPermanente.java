package proyecto.modelo.Decorator;
import proyecto.modelo.interfaces.IMedico;

/**
 * Clase que extiende de DecoratorContratacion y representa un decorador
 * para la contratacion permanente de medicos.
 * Aplica un aumento del 10% al sueldo calculado del medico.
 * 
 */
public class DecoratorContratacionPermanente extends DecoratorContratacion {

	/**
	 * Constructor que recibe un objeto IMedico para ser decorado.
	 * 
	 * @param medico El medico a ser decorado.
	 */
	public DecoratorContratacionPermanente(IMedico medico) {
		super(medico);
	}

	/**
	 * Calcula el sueldo del medico aplicando un aumento del 10%.
	 * @return El sueldo calculado con el aumento aplicado.
	 */
	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.1;
		return aux;
	}
	
}
