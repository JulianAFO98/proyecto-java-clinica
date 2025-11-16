package proyecto.modelo.paciente;

import proyecto.modelo.Domicilio;

public class Mayor extends Paciente{

	
	public Mayor(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio,numeroHistoriaClinica);
	}
	
	/**
	 * Implementa el mecanismo de Double Dispatch para determinar que paciente
	 * tiene prioridad en la asignacion de sala frente a otro paciente.
	 *
	 * <p>Este metodo delega la comparacion al metodo prioridadCon del
	 * otro paciente, permitiendo que el resultado dependa dinamicamente
	 * del tipo concreto de ambos objetos en tiempo de ejecucion.</p>
	 *
	 * @param otro el otro paciente con el cual se compara la prioridad de sala.
	 * @return el paciente que tiene prioridad (entre this y otro).
	 *
	 * <br>Pre: otro != null
	 * <br>Post: se retorna una referencia valida a un objeto Paciente
	 * que representa al paciente con mayor prioridad.
	 * <br>Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente decidirSala(Paciente otro)
	{
	    assert otro != null : "El paciente otro no debe ser nulo.";
	    
	    Paciente resultado = otro.prioridadCon(this);
	    
	    assert resultado != null : "El resultado de la decision de sala no puede ser nulo.";
	    assert resultado == this || resultado == otro : "El paciente con prioridad debe ser uno de los dos pacientes comparados.";
	    
	    return resultado;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Nino y este paciente.
	 * En este caso, el paciente actual (this) tiene prioridad sobre el nino.
	 *
	 * @param n el paciente de tipo Nino a comparar.
	 * @return this, indicando que el paciente actual tiene prioridad.
	 *
	 * <br>Pre: n != null
	 * <br>Post: el valor de retorno es this
	 * <br>Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Nino n)
	{
	    assert n != null : "El paciente Nino no debe ser nulo.";
	    
	    Paciente resultado = this;
	    
	    assert resultado == this : "El resultado debe ser el paciente actual (this).";
	    
	    return resultado;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Joven y este paciente.
	 * En este caso, el joven tiene prioridad sobre el paciente actual.
	 *
	 * @param j el paciente de tipo Joven a comparar.
	 * @return j, indicando que el joven tiene prioridad.
	 *
	 * <br>Pre: j != null
	 * <br>Post: el valor de retorno es j
	 * <br>Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Joven j)
	{
	    assert j != null : "El paciente Joven no debe ser nulo.";
	    
	    Paciente resultado = j;
	    
	    assert resultado == j : "El resultado debe ser el paciente Joven.";
	    
	    return resultado;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Mayor y este paciente.
	 * En este caso, el paciente actual tiene prioridad sobre el mayor.
	 *
	 * @param m el paciente de tipo Mayor a comparar.
	 * @return this, indicando que el paciente actual tiene prioridad.
	 *
	 * <br>Pre: m != null
	 * <br>Post: el valor de retorno es this
	 * <br>Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Mayor m)
	{
	    assert m != null : "El paciente Mayor no debe ser nulo.";
	    
	    Paciente resultado = this;
	    
	    assert resultado == this : "El resultado debe ser el paciente actual (this).";
	    
	    return resultado;
	}
}