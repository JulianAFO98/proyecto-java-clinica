package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

public class DecoratorPosgradoDoctorado extends DecoratorPosgrado {

	public DecoratorPosgradoDoctorado(IMedico medico) {
		super(medico);
	}

	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.10;
		return aux;
	}

	@Override
	public String toString() {
		return "DecoratorPosgradoDoctorado "+this.encapsulado.getNombre()+" sueldo: "+this.calcularSueldo();
	}
}
