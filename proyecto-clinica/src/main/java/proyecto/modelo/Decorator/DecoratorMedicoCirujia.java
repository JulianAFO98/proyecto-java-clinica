package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

public class DecoratorMedicoCirujia extends DecoratorMedico{
     public DecoratorMedicoCirujia(IMedico medico) {
    	super(medico);
	}

    @Override
	public String getEspecialidad() {
		return "Cirujia";
	}

    @Override
    public double calcularSueldo() {
        double aux = this.encapsulado.calcularSueldo();
        return aux*1.1;
    }
}
