package proyecto.modelo.Decorator;
import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;


/**
 * Clase abstracta que extiende de DecoratorMedico y representa un decorador
 * para la contratacion de medicos.
 * Permite agregar funcionalidades adicionales a los medicos
 * sin modificar la clase original.
 * */
public abstract class DecoratorContratacion extends DecoratorMedico{

	/**
	 * Constructor que recibe un objeto IMedico para ser decorado.
	 * * @param medico El medico a ser decorado.
	 */
	public DecoratorContratacion(IMedico medico) {
		super(medico);
	}
	
	@Override
	public String getEspecialidad() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		return this.encapsulado.getEspecialidad();
	}

	@Override
	public int getNumeroMatricula() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		int matricula = this.encapsulado.getNumeroMatricula();
		assert matricula > 0 : "El numero de matricula debe ser positivo.";
		return matricula;
	}

	@Override
	public String getNombre() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		String nombre = this.encapsulado.getNombre();
		assert nombre != null && !nombre.trim().isEmpty() : "El nombre retornado no debe ser nulo o vacio.";
		return nombre;
	}

	@Override
	public String getApellido() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		String apellido = this.encapsulado.getApellido();
		assert apellido != null && !apellido.trim().isEmpty() : "El apellido retornado no debe ser nulo o vacio.";
		return apellido;
	}

	@Override
	public Domicilio getDomicilio() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		Domicilio domicilio = this.encapsulado.getDomicilio();
		assert domicilio != null : "El domicilio retornado no debe ser nulo.";
		return domicilio;
	}

	@Override
	public String getCiudad() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		String ciudad = this.encapsulado.getCiudad();
		assert ciudad != null && !ciudad.trim().isEmpty() : "La ciudad retornada no debe ser nula o vacia.";
		return ciudad;
	}

	@Override
	public String getTelefono() {
		assert this.encapsulado != null : "El medico encapsulado no puede ser nulo al delegar el metodo.";
		String telefono = this.encapsulado.getTelefono();
		assert telefono != null && !telefono.trim().isEmpty() : "El telefono retornado no debe ser nulo o vacio.";
		return telefono;
	}
}