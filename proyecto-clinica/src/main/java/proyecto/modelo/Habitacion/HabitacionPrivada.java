package proyecto.modelo.Habitacion;

/**
 * Representa una habitacion privada.
 * Puede tener distintos precios segun la cantidad de dias de uso.
 * 
 * - 1 dia: costo base
 * - de 2 a 5 dias: costo base * 1.3 por dia
 * - 6 dias o mas: costo base * 2 por dia
 * 
 * Tambien permite indicar si la habitacion esta ocupada o no.
 * 
 */
public class HabitacionPrivada extends Habitacion {
	private boolean ocupada;

	/**
	 * Crea una habitacion privada con un precio base y estado libre por defecto.
	 * 
	 * @param precioBase precio diario base.
	 * @pre precioBase > 0
	 * @post se crea una habitacion privada no ocupada con el precio base indicado.
	 */
	public HabitacionPrivada(double precioBase) {
		super(precioBase);
		this.ocupada = false;
	}

	/**
	 * Calcula el precio total de la habitacion privada segun la cantidad de dias.
	 * 
	 * @param cantDias cantidad de dias de estadia.
	 * @return precio total de la habitacion privada.
	 * @pre cantDias > 0
	 * @post retorna el monto a pagar aplicando los recargos correspondientes.
	 */
	@Override
	public double calcularPrecio(int cantDias) {
		double precioAPagar = 0;
		double costoHabPrivada = super.getPrecioBase();

		if (cantDias == 1)
			precioAPagar = costoHabPrivada;
		else if (cantDias >= 2 && cantDias < 6)
			precioAPagar = cantDias * costoHabPrivada * 1.3;
		else if (cantDias >= 6)
			precioAPagar = cantDias * costoHabPrivada * 2;

		return precioAPagar;
	}

	/**
	 * Indica si la habitacion esta ocupada.
	 * 
	 * @return true si esta ocupada, false si esta libre.
	 */
	public boolean isOcupada() {
		return ocupada;
	}

	/**
	 * Devuelve el tipo de habitacion.
	 * 
	 * @return "Privada"
	 */
	public String getTipoHabitacion() {
		return "Privada";
	}

	/**
	 * Cambia el estado de ocupacion de la habitacion.
	 * 
	 * @param ocupada true para marcar como ocupada, false para libre.
	 * @post el estado de ocupacion se actualiza al valor indicado.
	 */
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	@Override
	public String toString() {
		return "HabitacionPrivada [ocupada=" + ocupada + "] " + super.toString();
	}
}
