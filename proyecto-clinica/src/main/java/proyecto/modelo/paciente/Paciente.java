package proyecto.modelo.paciente;

import proyecto.modelo.Domicilio;
import proyecto.modelo.Persona;
import proyecto.modelo.interfaces.IPaciente;

public abstract class Paciente extends Persona implements IPaciente {
	protected int numeroHistoriaClinica;
	private static int contadorNumeroOrden = 0;
	private int numeroOrdenPropio;

	public Paciente(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,
			int numeroHistoriaClinica) {
		super(dni, nombre, apellido, ciudad, telefono, domicilio);
		this.numeroHistoriaClinica = numeroHistoriaClinica;
		this.numeroOrdenPropio = -1;
	}

	/**
	 * Devuelve el numero de historia clinica asociado al paciente.
	 *
	 * @return el número de historia clinica del paciente.
	 *
	 * <br>Pre: el atributo numeroHistoriaClinica debe haber sido asignado previamente.
	 * <br>Post: no se modifica el estado interno del objeto.
	 */
	public int getNumeroHistoriaClinica() {
		return numeroHistoriaClinica;
	}

	/**
	 * Asigna al paciente un número de orden propio incremental.
	 * 
	 * <p>
	 * El número se obtiene incrementando en uno el contador estático
	 * contadorNumeroOrden y almacenando el resultado en el atributo
	 * numeroOrdenPropio del paciente actual.
	 * </p>
	 *
	 * <br>Pre:  contadorNumeroOrden debe estar correctamente inicializado.
	 * <br>Post:  numeroOrdenPropio del paciente se incrementa en 1 con respecto
	 *       al último valor asignado globalmente.
	 * <br>Post:  contadorNumeroOrden se incrementa en 1.
	 */
	public void setNumeroOrdenPropio() {
		this.numeroOrdenPropio = ++contadorNumeroOrden;
	}

	/**
	 * Devuelve el número de orden propio asignado al paciente.
	 *
	 * @return el número de orden propio de este paciente.
	 *
	 * <br>Pre:  numeroOrdenPropio debe haber sido asignado previamente
	 *      mediante  setNumeroOrdenPropio().
	 * <br>Post: no se modifica el estado interno del objeto.
	 */
	public int getNumeroOrdenPropio() {
		return this.numeroOrdenPropio;
	}

	/**
	 * Devuelve una representación en formato texto del paciente,
	 * mostrando sus datos principales.
	 *
	 * @return una cadena con el formato:
	 *         "Paciente [dni, Nombre: nombre, Apellido: apellido, Telefono:
	 *         telefono]"
	 *
	 * <br>Pre: los atributos  dni, code nombre, code apellido y code telefono
	 *      deben estar correctamente inicializados.
	 * <br>Post: no se modifica el estado interno del objeto.
	 */
	@Override
	public String toString() {
		return String.format(
				"Paciente [DNI: %s, Nombre: %s, Apellido: %s, Teléfono: %s, Ciudad: %s, Historia Clínica: %d, Orden: %d]",
				getDni(), getNombre(), getApellido(), getTelefono(), getCiudad(), numeroHistoriaClinica,
				numeroOrdenPropio);
	}

}
