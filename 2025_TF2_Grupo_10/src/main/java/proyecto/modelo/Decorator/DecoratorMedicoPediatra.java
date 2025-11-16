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
    	super(medico); // La precondicion (medico != null) se verifica en el constructor de la superclase
	}
    
    @Override
	public String getEspecialidad() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
        String especialidad = "Pediatra";
        assert especialidad.equals("Pediatra") : "La especialidad debe ser 'Pediatra'.";
		return especialidad;
	}

    /**
     * Calcula el sueldo del medico pediatra, aplicando un incremento del 7% al sueldo base.
     * @return El sueldo calculado del medico pediatra.
     */
    @Override
    public double calcularSueldo() {
        assert this.encapsulado != null : "El medico encapsulado no puede ser nulo para calcular el sueldo.";
        
        double sueldoPreAumento = this.encapsulado.calcularSueldo();
        assert sueldoPreAumento >= 0 : "El sueldo base del encapsulado no puede ser negativo.";
        
        double sueldoFinal = sueldoPreAumento * 1.07;
        
        assert sueldoFinal >= sueldoPreAumento : "El sueldo final debe ser mayor o igual al sueldo base.";
        assert Math.abs(sueldoFinal - (sueldoPreAumento * 1.07)) < 0.0001 : "El sueldo final debe ser el sueldo base mas un 7%.";
        
        return sueldoFinal;
    }
}