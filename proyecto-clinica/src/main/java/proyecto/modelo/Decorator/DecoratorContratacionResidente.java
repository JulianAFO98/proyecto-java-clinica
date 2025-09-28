package proyecto.modelo.Decorator;
import proyecto.modelo.interfaces.IMedico;

public class DecoratorContratacionResidente extends DecoratorContratacion {
	
	public DecoratorContratacionResidente(IMedico medico) {
		super(medico);
	}

	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.05;
		return aux;
	}

}
