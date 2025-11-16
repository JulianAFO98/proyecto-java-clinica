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
		
	}

	/**
	 * Calcula el sueldo del medico decorado con el posgrado de magister.
	 * Aplica un incremento del 5% al sueldo base del medico.
	 * @return El sueldo calculado con el incremento del posgrado.
	 */
	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.05;
		return aux;
	}

	/**
	 * Retorna una representación en cadena del medico decorado con el posgrado de magister.
	 * Incluye el nombre, especialidad y sueldo calculado.
	 * @return Una cadena que representa al medico con el posgrado de magister.
	 */
	@Override
	public String toString() {
		return "Magister: "+this.encapsulado.getNombre()+" con especialidad: "+this.encapsulado.getEspecialidad()+" sueldo: "+this.calcularSueldo();
	}

	
	

}
