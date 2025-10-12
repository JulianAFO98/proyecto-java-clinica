package proyecto.modelo.paciente;

import proyecto.modelo.Domicilio;

public class Mayor extends Paciente{

	
	public Mayor(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio,numeroHistoriaClinica);
	}
	
	/**
	 * Implementa el mecanismo de Double Dispatch para determinar qué paciente
	 * tiene prioridad en la asignación de sala frente a otro paciente.
	 *
	 * <p>Este metodo delega la comparación al metodo prioridadCon del
	 * otro paciente, permitiendo que el resultado dependa dinamicamente
	 * del tipo concreto de ambos objetos en tiempo de ejecución.</p>
	 *
	 * @param otro el otro paciente con el cual se compara la prioridad de sala.
	 * @return el paciente que tiene prioridad (entre this y otro).
	 *
	 * Pre: otro != null
	 * Post: se retorna una referencia válida a un objeto Paciente
	 *       que representa al paciente con mayor prioridad.
	 * Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente decidirSala(Paciente otro)
	{
	    return otro.prioridadCon(this);
	}

	/**
	 * Define la prioridad entre un paciente del tipo Nino y este paciente.
	 * En este caso, el paciente actual (this) tiene prioridad sobre el niño.
	 *
	 * @param n el paciente de tipo Nino a comparar.
	 * @return this, indicando que el paciente actual tiene prioridad.
	 *
	 * Pre: n != null
	 * Post: el valor de retorno es this
	 * Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Nino n)
	{
	    return this;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Joven y este paciente.
	 * En este caso, el joven tiene prioridad sobre el paciente actual.
	 *
	 * @param j el paciente de tipo Joven a comparar.
	 * @return j, indicando que el joven tiene prioridad.
	 *
	 * Pre: j != null
	 * Post: el valor de retorno es j
	 * Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Joven j)
	{
	    return j;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Mayor y este paciente.
	 * En este caso, el paciente actual tiene prioridad sobre el mayor.
	 *
	 * @param m el paciente de tipo Mayor a comparar.
	 * @return this, indicando que el paciente actual tiene prioridad.
	 *
	 * Pre: m != null
	 * Post: el valor de retorno es this
	 * Post: no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Mayor m)
	{
	    return this;
	}
}
