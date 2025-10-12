package proyecto.modelo.Decorator;
import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;


/**
 * Clase abstracta que extiende de DecoratorMedico y representa un decorador
 * para la contratacion de medicos.
 * Permite agregar funcionalidades adicionales a los medicos
 * sin modificar la clase original.
 * 
 */
public abstract class DecoratorContratacion extends DecoratorMedico{

	/**
	 * Constructor que recibe un objeto IMedico para ser decorado.
	 * 
	 * @param medico El medico a ser decorado.
	 */
	public DecoratorContratacion(IMedico medico) {
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
