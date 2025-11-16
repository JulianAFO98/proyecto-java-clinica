package proyecto.modelo.Decorator;

import proyecto.modelo.interfaces.IMedico;

/**
 * Clase que representa un decorador para un medico con especialidad en cirujia.
 * Extiende la clase DecoratorMedico y anade funcionalidad especifica para medicos cirujanos.
 */
public class DecoratorMedicoCirujia extends DecoratorMedico{
    
    /**
     * Constructor que recibe un objeto IMedico para decorar.
     * * @param medico El objeto IMedico a ser decorado.
     */
    public DecoratorMedicoCirujia(IMedico medico) {
    	super(medico); // La precondicion (medico != null) se verifica en el constructor de la superclase
	}

    @Override
	public String getEspecialidad() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
        String especialidad = "Cirujia";
        assert especialidad.equals("Cirujia") : "La especialidad debe ser 'Cirujia'.";
		return especialidad;
	}

    /**
     * Calcula el sueldo del medico cirujano, aplicando un incremento del 10% al sueldo base.
     * @return El sueldo calculado del medico cirujano.
     */
    @Override
    public double calcularSueldo() {
        assert this.encapsulado != null : "El medico encapsulado no puede ser nulo para calcular el sueldo.";
        
        double sueldoPreAumento = this.encapsulado.calcularSueldo();
        assert sueldoPreAumento >= 0 : "El sueldo base del encapsulado no puede ser negativo.";
        
        double sueldoFinal = sueldoPreAumento * 1.1;
        
        assert sueldoFinal >= sueldoPreAumento : "El sueldo final debe ser mayor o igual al sueldo base.";
        assert Math.abs(sueldoFinal - (sueldoPreAumento * 1.1)) < 0.0001 : "El sueldo final debe ser el sueldo base mas un 10%.";
        
        return sueldoFinal;
    }
}