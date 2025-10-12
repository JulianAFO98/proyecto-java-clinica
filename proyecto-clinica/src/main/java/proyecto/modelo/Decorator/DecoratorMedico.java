package proyecto.modelo.Decorator;

import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;

/**
 * Clase abstracta que implementa la interfaz IMedico y representa
 * un decorador para los medicos.
 * Permite agregar funcionalidades adicionales a los medicos
 * sin modificar la clase original.
 * 
 */
public abstract class DecoratorMedico implements IMedico{
	protected IMedico encapsulado;
	
	/**
	 * Constructor que recibe un objeto IMedico a decorar.
	 * @param medico El medico a decorar.
	 */
	public DecoratorMedico(IMedico medico) {
    		this.encapsulado = medico;
	}
	
	@Override
	public int getNumeroMatricula() {
		return this.encapsulado.getNumeroMatricula();
	}

	@Override
	public String getNombre() {
		return this.encapsulado.getNombre();
	}

	@Override
	public String getApellido() {
		return this.encapsulado.getApellido();
	}

	@Override
	public Domicilio getDomicilio() {
		return this.encapsulado.getDomicilio();
	}

	@Override
	public String getCiudad() {
		return this.encapsulado.getCiudad();
	}

	@Override
	public String getTelefono() {
		return this.encapsulado.getTelefono();
	}
}
