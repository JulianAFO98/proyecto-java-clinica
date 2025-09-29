package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

public class DecoratorPosgradoMagister extends DecoratorPosgrado {

	public DecoratorPosgradoMagister(IMedico medico) {
		super(medico);
		
	}

	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.05;
		return aux;
	}

	@Override
	public String toString() {
		return "Magister: "+this.encapsulado.getNombre()+" con especialidad: "+this.encapsulado.getEspecialidad()+" sueldo: "+this.calcularSueldo();
	}

	
	

}
