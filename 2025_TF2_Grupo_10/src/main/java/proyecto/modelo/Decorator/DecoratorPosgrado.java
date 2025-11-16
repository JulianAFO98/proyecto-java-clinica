package proyecto.modelo.Decorator;

import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;

/**
 * Clase abstracta que representa un decorador para agregar posgrados a un medico.
 * Hereda de DecoratorMedico.
 *
 */
public abstract class DecoratorPosgrado extends DecoratorMedico{
	/**
	 * Constructor que recibe un medico a decorar.
	 * @param medico El medico a decorar.
	 */
	public DecoratorPosgrado(IMedico medico) {
		super(medico);
	}

	@Override
	public String getEspecialidad() {
		
		return this.encapsulado.getEspecialidad();
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
