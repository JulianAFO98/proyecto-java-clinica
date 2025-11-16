package proyecto.modelo.Decorator;
import proyecto.modelo.interfaces.IMedico;

/**
 * Clase que extiende de DecoratorContratacion y representa un decorador
 * para la contratacion de medicos residentes.
 * Aplica un aumento del 5% al sueldo calculado del medico.
 * */
public class DecoratorContratacionResidente extends DecoratorContratacion {
	
	/**
	 * Constructor que recibe un objeto IMedico para ser decorado.
	 * * @param medico El medico a ser decorado.
	 */
	public DecoratorContratacionResidente(IMedico medico) {
		super(medico);
	}

	/**
	 * Calcula el sueldo del medico aplicando un aumento del 5%.
	 * @return El sueldo calculado con el aumento aplicado.
	 */
	@Override
	public double calcularSueldo() {
        assert this.encapsulado != null : "El medico encapsulado no puede ser nulo para calcular el sueldo.";
        
		double sueldoPreAumento = this.encapsulado.calcularSueldo();
        assert sueldoPreAumento >= 0 : "El sueldo base del encapsulado no puede ser negativo.";
        
		double sueldoFinal = sueldoPreAumento * 1.05;
        
        assert sueldoFinal >= sueldoPreAumento : "El sueldo final debe ser mayor o igual al sueldo base.";
        assert Math.abs(sueldoFinal - (sueldoPreAumento * 1.05)) < 0.0001 : "El sueldo final debe ser el sueldo base mas un 5%.";
        
		return sueldoFinal;
	}

}