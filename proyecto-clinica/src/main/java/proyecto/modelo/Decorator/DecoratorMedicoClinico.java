package proyecto.modelo.Decorator;
import proyecto.modelo.interfaces.IMedico;

public class DecoratorMedicoClinico extends DecoratorMedico{

    public DecoratorMedicoClinico(IMedico medico) {
    	super(medico);
	}

    @Override
	public String getEspecialidad() {
		return "Clinico";
	}

    @Override
    public double calcularSueldo() {
        double aux = this.encapsulado.calcularSueldo();
        return aux*1.05;
    }


    
}
