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
	}

	/**
	 * Calcula el sueldo del medico con un incremento del 10% por tener posgrado de doctorado.
	 * @return El sueldo calculado.
	 */
	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.10;
		return aux;
	}

	/**
	 * Devuelve una representación en cadena del medico decorado con su especialidad y sueldo.
	 * @return Una cadena que representa al medico decorado.
	 */
	@Override
	public String toString() {
		return "Doctor: "+this.encapsulado.getNombre()+" con especialidad: "+this.encapsulado.getEspecialidad()+" sueldo: "+this.calcularSueldo();
	}
}
