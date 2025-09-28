package proyecto.modelo.Decorator;
import proyecto.modelo.interfaces.IMedico;

public class DecoratorContratacionPermanente extends DecoratorContratacion {

	public DecoratorContratacionPermanente(IMedico medico) {
		super(medico);
	}

	@Override
	public double calcularSueldo() {
		double aux = this.encapsulado.calcularSueldo();
		aux *= 1.1;
		return aux;
	}

	
	
}
