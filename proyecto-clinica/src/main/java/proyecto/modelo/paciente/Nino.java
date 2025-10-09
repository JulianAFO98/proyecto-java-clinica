package proyecto.modelo.paciente;
import proyecto.modelo.Domicilio;


public class Nino extends Paciente{
	
	public Nino(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroHistoriaClinica)
	{
		super(dni, nombre, apellido, ciudad, telefono, domicilio,numeroHistoriaClinica);
	}
	/**
	 * Implementa el mecanismo de Double Dispatch para determinar qué paciente
	 * tiene prioridad en la asignación de sala frente a otro paciente.
	 *
	 * <p>Este método utiliza doble despacho: en lugar de decidir la prioridad
	 * directamente, delega la comparación al método  prioridadCon(this)
	 * del otro paciente. De esta forma, la decisión final depende del tipo
	 * dinámico de ambos objetos en tiempo de ejecución.</p>
	 *
	 * @param otro el otro paciente con el cual se compara la prioridad de sala.
	 * @return el paciente que tiene prioridad (entre this y otro).
	 *
	 * @pre otro != null.
	 * @post se retorna una referencia válida a un objeto Paciente
	 *       que representa al paciente con mayor prioridad.
	 * @post no se modifica el estado interno de ninguno de los objetos.
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
	 * @return code this, indicando que el paciente actual tiene prioridad.
	 *
	 * @pre n != null.
	 * @post el valor de retorno es this.
	 * @post no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Nino n)
	{
	    return this;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Joven y este paciente.
	 * En este caso, el paciente actual (this) tiene prioridad sobre el joven.
	 *
	 * @param j el paciente de tipo Joven a comparar.
	 * @return this, indicando que el paciente actual tiene prioridad.
	 *
	 * @pre j != null
	 * @post el valor de retorno es this
	 * @post no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Joven j)
	{
	    return this;
	}

	/**
	 * Define la prioridad entre un paciente del tipo Mayor y este paciente.
	 * En este caso, el paciente Mayor tiene prioridad sobre el actual.
	 *
	 * @param m el paciente de tipo  Mayor a comparar.
	 * @return m, indicando que el mayor tiene prioridad.
	 *
	 * @pre m != null
	 * @post el valor de retorno es m
	 * @post no se modifica el estado interno de ninguno de los objetos.
	 */
	@Override
	public Paciente prioridadCon(Mayor m)
	{
	    return m;
	}

}
