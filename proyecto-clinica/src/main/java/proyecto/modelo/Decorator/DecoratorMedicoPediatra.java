package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

/**
 * DecoratorMedicoPediatra
 * Clase que representa un decorador para un medico pediatra
 */
public class DecoratorMedicoPediatra  extends DecoratorMedico{
    /**
     * Constructor de la clase DecoratorMedicoPediatra
     * @param medico medico a decorar
     */ 
    public DecoratorMedicoPediatra(IMedico medico) {
    	super(medico);
	}
    @Override
	public String getEspecialidad() {
		return "Pediatra";
	}

    /**
     * Calcula el sueldo del médico pediatra, aplicando un incremento del 7% al sueldo base.
     * @return El sueldo calculado del médico pediatra.
     */
    @Override
    public double calcularSueldo() {
        double aux = this.encapsulado.calcularSueldo();
        return aux*1.07;
    }
}

