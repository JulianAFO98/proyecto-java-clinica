package proyecto.modelo.Decorator;

import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;

/**
 * Clase abstracta que representa un decorador para agregar posgrados a un medico.
 * Hereda de DecoratorMedico.
 *
 * */
public abstract class DecoratorPosgrado extends DecoratorMedico{
	/**
	 * Constructor que recibe un medico a decorar.
	 * @param medico El medico a decorar.
	 */
	public DecoratorPosgrado(IMedico medico) {
		super(medico); // La precondicion (medico != null) se verifica en el constructor de la superclase
	}

	@Override
	public String getEspecialidad() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		String especialidad = this.encapsulado.getEspecialidad();
		// No se valida si la especialidad es nula/vacia, ya que depende del medico base o de otros decoradores.
		return especialidad;
	}
	
	@Override
	public int getNumeroMatricula() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		int matricula = this.encapsulado.getNumeroMatricula();
		assert matricula > 0 : "El numero de matricula debe ser positivo.";
		return matricula;
	}

	@Override
	public String getNombre() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		String nombre = this.encapsulado.getNombre();
		assert nombre != null && !nombre.trim().isEmpty() : "El nombre retornado no debe ser nulo o vacio.";
		return nombre;
	}

	@Override
	public String getApellido() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		String apellido = this.encapsulado.getApellido();
		assert apellido != null && !apellido.trim().isEmpty() : "El apellido retornado no debe ser nulo o vacio.";
		return apellido;
	}

	@Override
	public Domicilio getDomicilio() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		Domicilio domicilio = this.encapsulado.getDomicilio();
		assert domicilio != null : "El domicilio retornado no debe ser nulo.";
		return domicilio;
	}

	@Override
	public String getCiudad() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		String ciudad = this.encapsulado.getCiudad();
		assert ciudad != null && !ciudad.trim().isEmpty() : "La ciudad retornada no debe ser nula o vacia.";
		return ciudad;
	}

	@Override
	public String getTelefono() {
		assert this.encapsulado != null : "El medico encapsulado no debe ser nulo.";
		String telefono = this.encapsulado.getTelefono();
		assert telefono != null && !telefono.trim().isEmpty() : "El telefono retornado no debe ser nulo o vacio.";
		return telefono;
	}

}