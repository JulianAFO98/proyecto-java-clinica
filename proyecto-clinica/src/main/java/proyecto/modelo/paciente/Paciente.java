package proyecto.modelo.paciente;

import proyecto.modelo.Domicilio;
import proyecto.modelo.Persona;
import proyecto.modelo.interfaces.IPaciente;

public abstract class Paciente extends Persona implements IPaciente{
	protected int numeroHistoriaClinica;
	
	public Paciente(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio, int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio);
		this.numeroHistoriaClinica = numeroHistoriaClinica;
	}

	public int getNumeroHistoriaClinica() {
		return numeroHistoriaClinica;
	}
}
