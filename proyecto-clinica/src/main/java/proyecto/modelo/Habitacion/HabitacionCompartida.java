package proyecto.modelo.Habitacion;

/**
 * Representa una habitacion compartida.
 * El precio se calcula multiplicando el precio base por la cantidad de dias.
 */
public class HabitacionCompartida extends Habitacion {

	/**
	 * Crea una habitacion compartida con un precio base especifico.
	 * 
	 * @param precioBase precio diario de la habitacion compartida.
	 * Pre: n precioBase > 0
	 * Post: se crea una habitacion compartida con el precio base indicado.
	 */
	public HabitacionCompartida(double precioBase) {
		super(precioBase);
	}

	/**
	 * Calcula el precio total de la habitacion compartida.
	 * 
	 * @param cantDias cantidad de dias de estadia.
	 * @return precio total (precio base * cantidad de dias).
	 * Pre:n cantDias > 0
	 * Post: retorna el monto total a pagar sin recargos.
	 */
	@Override
	public double calcularPrecio(int cantDias) {
		return cantDias * super.getPrecioBase();
	}

	/**
	 * Devuelve el tipo de habitacion.
	 * 
	 * @return "Compartida"
	 */
	public String getTipoHabitacion() {
		return "Compartida";
	}

	@Override
	public String toString() {
		return "HabitacionCompartida " + super.toString();
	}
}
