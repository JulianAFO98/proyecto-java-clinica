package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;
/**
 * Clase que representa un decorador para medicos con posgrado de doctorado.
 * Extiende de DecoratorPosgrado.
 */
public class DecoratorPosgradoDoctorado extends DecoratorPosgrado {

	/**
	 * Constructor que recibe un medico para decorar.
	 * @param medico El medico a decorar.
	 */
	public DecoratorPosgradoDoctorado(IMedico medico) {
		super(medico);
        assert this.encapsulado != null : "El objeto IMedico encapsulado no debe ser nulo despues de la construccion.";
	}

	/**
	 * Calcula el sueldo del medico con un incremento del 10% por tener posgrado de doctorado.
	 * @return El sueldo calculado.
	 */
	@Override
	public double calcularSueldo() {
        assert this.encapsulado != null : "El medico encapsulado no puede ser nulo para calcular el sueldo.";
        
		double sueldoPreAumento = this.encapsulado.calcularSueldo();
        assert sueldoPreAumento >= 0 : "El sueldo base del encapsulado no puede ser negativo.";
        
		double sueldoFinal = sueldoPreAumento * 1.10;
        
        assert sueldoFinal >= sueldoPreAumento : "El sueldo final debe ser mayor o igual al sueldo base.";
        assert Math.abs(sueldoFinal - (sueldoPreAumento * 1.10)) < 0.0001 : "El sueldo final debe ser el sueldo base mas un 10%.";
        
		return sueldoFinal;
	}

	/**
	 * Devuelve una representacion en cadena del medico decorado con su especialidad y sueldo.
	 * @return Una cadena que representa al medico decorado.
	 */
	@Override
	public String toString() {
        assert this.encapsulado != null : "El medico encapsulado no debe ser nulo para generar el String.";
		return "Doctor: "+this.encapsulado.getNombre()+" con especialidad: "+this.encapsulado.getEspecialidad()+" sueldo: "+this.calcularSueldo();
	}
}