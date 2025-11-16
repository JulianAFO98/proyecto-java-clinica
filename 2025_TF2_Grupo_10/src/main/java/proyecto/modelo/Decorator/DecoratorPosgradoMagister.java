package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

/**
 * Clase que representa un decorador para un medico con posgrado de magister.
 * Extiende la clase DecoratorPosgrado.
 */
public class DecoratorPosgradoMagister extends DecoratorPosgrado {

	/**
	 * Constructor que recibe un medico y lo decora con el posgrado de magister.
	 * @param medico El medico a decorar.
	 */
	public DecoratorPosgradoMagister(IMedico medico) {
		super(medico);
        assert this.encapsulado != null : "El objeto IMedico encapsulado no debe ser nulo despues de la construccion.";
	}

	/**
	 * Calcula el sueldo del medico decorado con el posgrado de magister.
	 * Aplica un incremento del 5% al sueldo base del medico.
	 * @return El sueldo calculado con el incremento del posgrado.
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

	/**
	 * Retorna una representacion en cadena del medico decorado con el posgrado de magister.
	 * Incluye el nombre, especialidad y sueldo calculado.
	 * @return Una cadena que representa al medico con el posgrado de magister.
	 */
	@Override
	public String toString() {
        assert this.encapsulado != null : "El medico encapsulado no debe ser nulo para generar el String.";
		return "Magister: "+this.encapsulado.getNombre()+" con especialidad: "+this.encapsulado.getEspecialidad()+" sueldo: "+this.calcularSueldo();
	}

	
	

}