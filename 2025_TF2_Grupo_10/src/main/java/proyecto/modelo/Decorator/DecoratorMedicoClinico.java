package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

/**
 * DecoratorMedicoClinico
 * Clase que representa un decorador para un medico clinico
 */
public class DecoratorMedicoClinico extends DecoratorMedico{

    /**
     * Constructor de la clase DecoratorMedicoClinico
     * @param medico medico a decorar
     */
    public DecoratorMedicoClinico(IMedico medico) {
    	super(medico);
	}

    @Override
	public String getEspecialidad() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
        String especialidad = "Clinico";
        assert especialidad.equals("Clinico") : "La especialidad debe ser 'Clinico'.";
		return especialidad;
	}

   /**
     * Calcula el sueldo del medico clinico, aplicando un incremento del 5% al sueldo base.
     * @return El sueldo calculado del medico clinico.
     */
    @Override
    public double calcularSueldo() {
        assert this.encapsulado != null : "El medico encapsulado no puede ser nulo para calcular el sueldo.";
        
        double sueldoPreAumento = this.encapsulado.calcularSueldo();
        assert sueldoPreAumento >= 0 : "El sueldo base del encapsulado no puede ser negativo.";
        
        double sueldoFinal = sueldoPreAumento * 1.05;
        
        assert sueldoFinal >= sueldoPreAumento : "El sueldo final debe ser mayor o igual al sueldo base.";
        assert Math.abs(sueldoFinal - (sueldoPreAumento * 1.05)) < 0.0001 : "El sueldo final debe ser el sueldo base mas un 5%.";
        
        return sueldoFinal;
    }
}