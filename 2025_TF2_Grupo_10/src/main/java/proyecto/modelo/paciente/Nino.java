package proyecto.modelo.paciente;
import proyecto.modelo.Domicilio;


public class Nino extends Paciente{
	
	public Nino(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio,numeroHistoriaClinica);
	}
	/**
	 * Implementa el mecanismo de Double Dispatch para determinar que paciente
	 * tiene prioridad en la asignacion de sala frente a otro paciente.
	 *
	 * <p>Este metodo utiliza doble despacho: en lugar de decidir la prioridad
	 * directamente, delega la comparacion al metodo  prioridadCon(this)
	 * del otro paciente. De esta forma, la decision final depende del tipo
	 * dinamico de ambos objetos en tiempo de ejecucion.</p>
	 *
	 * @param otro el otro paciente con el cual se compara la prioridad de sala.
	 * @return el paciente que tiene prioridad (entre this y otro).
	 *
	 * <br>Pre: otro != null.
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
	 * @return code this, indicando que el paciente actual tiene prioridad.
	 *
	 * <br>Pre: n != null.
	 * <br>Post: el valor de retorno es this.
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
	 * En este caso, el paciente actual (this) tiene prioridad sobre el joven.
	 *
	 * @param j el paciente de tipo Joven a comparar.
	 * @return this, indicando que el paciente actual tiene prioridad.
	 *
	 * <br>Pre: j != null
	 * <br>Post: el valor de retorno es this
	 * <br>Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Joven j)
	{
	    assert j != null : "El paciente Joven no debe ser nulo.";
	    
	    Paciente resultado = this;
	    
	    assert resultado == this : "El resultado debe ser el paciente actual (this).";
	    
	    return resultado;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Mayor y este paciente.
	 * En este caso, el paciente Mayor tiene prioridad sobre el actual.
	 *
	 * @param m el paciente de tipo  Mayor a comparar.
	 * @return m, indicando que el mayor tiene prioridad.
	 *
	 * <br>Pre: m != null
	 * <br>Post: el valor de retorno es m
	 * <br>Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Mayor m)
	{
	    assert m != null : "El paciente Mayor no debe ser nulo.";
	    
	    Paciente resultado = m;
	    
	    assert resultado == m : "El resultado debe ser el paciente Mayor.";
	    
	    return resultado;
	}

}