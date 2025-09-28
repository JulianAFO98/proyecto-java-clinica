package proyecto.modelo.Decorator;
import proyecto.modelo.Domicilio;
import proyecto.modelo.interfaces.IMedico;

public abstract class DecoratorContratacion extends DecoratorMedico{

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
