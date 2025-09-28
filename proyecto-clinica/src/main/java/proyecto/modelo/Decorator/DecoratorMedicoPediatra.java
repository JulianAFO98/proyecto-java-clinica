package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

public class DecoratorMedicoPediatra  extends DecoratorMedico{
     public DecoratorMedicoPediatra(IMedico medico) {
    	super(medico);
	}
    @Override
	public String getEspecialidad() {
		return "Pediatra";
	}

    @Override
    public double calcularSueldo() {
        double aux = this.encapsulado.calcularSueldo();
        return aux*1.07;
    }
}

