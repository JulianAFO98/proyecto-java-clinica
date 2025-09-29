package proyecto.modelo.paciente;

import proyecto.modelo.Domicilio;
import proyecto.modelo.Persona;
import proyecto.modelo.interfaces.IPaciente;

public abstract class Paciente extends Persona implements IPaciente{
	protected int numeroHistoriaClinica;
	private static int contadorNumeroOrden = 0;
	private int numeroOrdenPropio;
	public Paciente(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio, int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio);
		this.numeroHistoriaClinica = numeroHistoriaClinica;
		this.numeroOrdenPropio=-1;
	}

	public int getNumeroHistoriaClinica() {
		return numeroHistoriaClinica;
	}

	public void setNumeroOrdenPropio() {
		this.numeroOrdenPropio = ++contadorNumeroOrden;
	}

	public int getNumeroOrdenPropio() {
		return this.numeroOrdenPropio;
	}

	@Override
	public String toString() {
		return "Paciente [" + getDni() + ", Nombre: " + getNombre() + ", Apellido: " + getApellido()
				+ ", Telefono: " + getTelefono() + "]";
	}

	

}
