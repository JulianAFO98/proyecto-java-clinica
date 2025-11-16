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
		
		// Precondicion
		assert numeroHistoriaClinica > 0 : "El numero de historia clinica debe ser positivo.";
		
		this.numeroHistoriaClinica = numeroHistoriaClinica;
		this.numeroOrdenPropio = -1;
		
		// Postcondicion
		assert this.numeroHistoriaClinica == numeroHistoriaClinica : "La historia clinica no se asigno correctamente.";
		assert this.numeroOrdenPropio == -1 : "El numero de orden propio debe inicializarse a -1.";
	}

	/**
	 * Devuelve el numero de historia clinica asociado al paciente.
	 *
	 * @return el numero de historia clinica del paciente.
	 *
	 * <br>Pre: el atributo numeroHistoriaClinica debe haber sido asignado previamente.
	 * <br>Post: no se modifica el estado interno del objeto.
	 */
	public int getNumeroHistoriaClinica() {
		// Invariante
		assert numeroHistoriaClinica > 0 : "El numero de historia clinica debe ser positivo.";
		return numeroHistoriaClinica;
	}

	/**
	 * Asigna al paciente un numero de orden propio incremental.
	 * * <p>
	 * El numero se obtiene incrementando en uno el contador estatico
	 * contadorNumeroOrden y almacenando el resultado en el atributo
	 * numeroOrdenPropio del paciente actual.
	 * </p>
	 *
	 * <br>Pre: contadorNumeroOrden debe estar correctamente inicializado.
	 * <br>Post: numeroOrdenPropio del paciente se incrementa en 1 con respecto
	 * al ultimo valor asignado globalmente.
	 * <br>Post: contadorNumeroOrden se incrementa en 1.
	 */
	public void setNumeroOrdenPropio() {
		int contadorPre = contadorNumeroOrden;
		
		this.numeroOrdenPropio = ++contadorNumeroOrden;
		
		// Postcondiciones
		assert contadorNumeroOrden == contadorPre + 1 : "El contador estatico no se incremento en 1.";
		assert this.numeroOrdenPropio == contadorNumeroOrden : "El numero de orden propio no se asigno correctamente.";
		assert this.numeroOrdenPropio > 0 : "El numero de orden propio debe ser positivo.";
	}

	/**
	 * Devuelve el numero de orden propio asignado al paciente.
	 *
	 * @return el numero de orden propio de este paciente.
	 *
	 * <br>Pre: numeroOrdenPropio debe haber sido asignado previamente
	 * mediante setNumeroOrdenPropio().
	 * <br>Post: no se modifica el estado interno del objeto.
	 */
	public int getNumeroOrdenPropio() {
		// Precondicion (se valida que haya sido asignado)
		assert this.numeroOrdenPropio > 0 : "El numero de orden propio debe haber sido asignado (es mayor a 0).";
		return this.numeroOrdenPropio;
	}

	/**
	 * Devuelve una representacion en formato texto del paciente,
	 * mostrando sus datos principales.
	 *
	 * @return una cadena con el formato:
	 * "Paciente [dni, Nombre: nombre, Apellido: apellido, Telefono:
	 * telefono]"
	 *
	 * <br>Pre: los atributos dni, code nombre, code apellido y code telefono
	 * deben estar correctamente inicializados.
	 * <br>Post: no se modifica el estado interno del objeto.
	 */
	@Override
	public String toString() {
		// Invariantes
		assert getDni() != null && !getDni().isEmpty() : "El DNI no debe ser nulo o vacio.";
		assert getNombre() != null && !getNombre().isEmpty() : "El nombre no debe ser nulo o vacio.";
		assert numeroHistoriaClinica > 0 : "La historia clinica debe ser positiva.";
		
		return String.format(
				"Paciente [DNI: %s, Nombre: %s, Apellido: %s, Telefono: %s, Ciudad: %s, Historia Clinica: %d, Orden: %d]",
				getDni(), getNombre(), getApellido(), getTelefono(), getCiudad(), numeroHistoriaClinica,
				numeroOrdenPropio);
	}

}