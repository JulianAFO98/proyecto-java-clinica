package proyecto.modelo.Habitacion;

/**
 * Clase abstracta que representa una habitacion de un establecimiento.
 * Contiene el precio base y define el comportamiento general de cualquier tipo
 * de habitacion.
 * Las clases hijas deben implementar el calculo del precio segun su tipo y
 * cantidad de dias.
 * 
 */
public abstract class Habitacion {
	private double precioBase;

	/**
	 * Crea una habitacion con un precio base determinado.
	 * 
	 * @param precioBase valor base diario de la habitacion.
	 * <br>Pre: n precioBase > 0
	 * <br>Post: se inicializa el atributo precioBase con el valor recibido.
	 */
	public Habitacion(double precioBase) {
		this.precioBase = precioBase;
	}

	/**
	 * Calcula el precio total de la habitacion segun la cantidad de dias de uso.
	 * 
	 * @param cantDias cantidad de dias de estadia.
	 * @return el precio total a pagar.
	 * <br>Pre: n cantDias > 0
	 * <br>Post: retorna el costo total segun la logica definida por cada tipo de
	 *       habitacion.
	 */
	public abstract double calcularPrecio(int cantDias);

	/**
	 * Retorna el precio base de la habitacion.
	 * 
	 * @return el precio base diario.
	 */
	public double getPrecioBase() {
		return precioBase;
	}

	/**
	 * Retorna una cadena con el tipo especifico de habitacion.
	 * 
	 * @return tipo de habitacion (por ejemplo, "Privada" o "Compartida").
	 */
	public abstract String getTipoHabitacion();

	@Override
	public String toString() {
		return "Habitacion [precioBase=" + precioBase + "] ";
	}
}
