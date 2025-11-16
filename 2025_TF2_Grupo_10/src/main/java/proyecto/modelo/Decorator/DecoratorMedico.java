package proyecto.modelo.Decorator;

import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;

/**
 * Clase abstracta que implementa la interfaz IMedico y representa un decorador
 * para los medicos. Permite agregar funcionalidades adicionales a los medicos
 * sin modificar la clase original.
 */
public abstract class DecoratorMedico implements IMedico {
	protected IMedico encapsulado;

	/**
	 * Constructor que recibe un objeto IMedico a decorar.
	 * @param medico El medico a decorar.
	 */
	public DecoratorMedico(IMedico medico) {
        assert medico != null : "El medico a encapsular no puede ser nulo.";
        this.encapsulado = medico;
        assert this.encapsulado != null : "El medico encapsulado no puede ser nulo despues de la asignacion.";
	}

	@Override
	public int getNumeroMatricula() {
		assert this.encapsulado != null : "El encapsulado no debe ser nulo.";
		int matricula = this.encapsulado.getNumeroMatricula();
		assert matricula > 0 : "La matricula debe ser un numero positivo.";
		return matricula;
	}

	@Override
	public String getNombre() {
		assert this.encapsulado != null : "El encapsulado no debe ser nulo.";
		String nombre = this.encapsulado.getNombre();
		assert nombre != null && !nombre.trim().isEmpty() : "El nombre retornado no debe ser nulo o vacio.";
		return nombre;
	}

	@Override
	public String getApellido() {
		assert this.encapsulado != null : "El encapsulado no debe ser nulo.";
		String apellido = this.encapsulado.getApellido();
		assert apellido != null && !apellido.trim().isEmpty() : "El apellido retornado no debe ser nulo o vacio.";
		return apellido;
	}

	@Override
	public Domicilio getDomicilio() {
		assert this.encapsulado != null : "El encapsulado no debe ser nulo.";
		Domicilio domicilio = this.encapsulado.getDomicilio();
		assert domicilio != null : "El domicilio retornado no debe ser nulo.";
		return domicilio;
	}

	@Override
	public String getCiudad() {
		assert this.encapsulado != null : "El encapsulado no debe ser nulo.";
		String ciudad = this.encapsulado.getCiudad();
		assert ciudad != null && !ciudad.trim().isEmpty() : "La ciudad retornada no debe ser nula o vacia.";
		return ciudad;
	}

	@Override
	public String getTelefono() {
		assert this.encapsulado != null : "El encapsulado no debe ser nulo.";
		String telefono = this.encapsulado.getTelefono();
		assert telefono != null && !telefono.trim().isEmpty() : "El telefono retornado no debe ser nulo o vacio.";
		return telefono;
	}
}