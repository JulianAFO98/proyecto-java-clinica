package proyecto.modelo.paciente;
import proyecto.modelo.Domicilio;


public class Nino extends Paciente{
	
	public Nino(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio,numeroHistoriaClinica);
	}
	
	@Override
	public Paciente decidirSala(Paciente otro)
	{
		return otro.prioridadCon(this);
	}
	
	@Override
	public Paciente prioridadCon(Nino n)
	{
		return this;
	}
	
	@Override
	public Paciente prioridadCon(Joven j)
	{
		return this;
	}
	@Override
	public Paciente prioridadCon(Mayor m)
	{
		return m;
	}

}
