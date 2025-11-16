package proyecto.modelo.Habitacion;

/**
 * Representa una habitacion privada.
 * Puede tener distintos precios segun la cantidad de dias de uso.
 * * - 1 dia: costo base
 * - de 2 a 5 dias: costo base * 1.3 por dia
 * - 6 dias o mas: costo base * 2 por dia
 * * Tambien permite indicar si la habitacion esta ocupada o no.
 * */
public class HabitacionPrivada extends Habitacion {
	private boolean ocupada;

	/**
	 * Crea una habitacion privada con un precio base y estado libre por defecto.
	 * * @param precioBase precio diario base.
	 * <br>Pre:n precioBase > 0
	 * <br>Post: se crea una habitacion privada no ocupada con el precio base indicado.
	 */
	public HabitacionPrivada(double precioBase) {
		super(precioBase);
		this.ocupada = false;
        
        // Postcondicion
        assert !this.ocupada : "La habitacion debe crearse desocupada.";
        assert this.getTipoHabitacion().equals("Privada") : "El tipo de habitacion debe ser Privada.";
	}

	/**
	 * Calcula el precio total de la habitacion privada segun la cantidad de dias.
	 * * @param cantDias cantidad de dias de estadia.
	 * @return precio total de la habitacion privada.
	 * <br>Pre: n cantDias > 0
	 * <br>Post: retorna el monto a pagar aplicando los recargos correspondientes.
	 */
	@Override
	public double calcularPrecio(int cantDias) {
        // Precondicion
        assert cantDias > 0 : "La cantidad de dias debe ser positiva.";

		double precioAPagar = 0;
		double costoHabPrivada = super.getPrecioBase();
        assert costoHabPrivada > 0 : "El costo base debe ser positivo para el calculo.";

		if (cantDias == 1)
			precioAPagar = costoHabPrivada;
		else if (cantDias >= 2 && cantDias < 6)
			precioAPagar = cantDias * costoHabPrivada * 1.3;
		else if (cantDias >= 6)
			precioAPagar = cantDias * costoHabPrivada * 2;
        // Invariante: precioAPagar ya fue asignado en alguna rama.
        assert precioAPagar > 0 : "El precio a pagar debe ser positivo si la cantidad de dias es positiva.";
        
        // Postcondicion de calculo
        if (cantDias == 1)
            assert Math.abs(precioAPagar - costoHabPrivada) < 0.0001 : "El calculo para 1 dia es incorrecto.";
        else if (cantDias >= 2 && cantDias < 6)
            assert Math.abs(precioAPagar - (cantDias * costoHabPrivada * 1.3)) < 0.0001 : "El calculo para 2-5 dias es incorrecto.";
        else if (cantDias >= 6)
            assert Math.abs(precioAPagar - (cantDias * costoHabPrivada * 2)) < 0.0001 : "El calculo para 6 o mas dias es incorrecto.";

		return precioAPagar;
	}

	/**
	 * Indica si la habitacion esta ocupada.
	 * * @return true si esta ocupada, false si esta libre.
	 */
	public boolean isOcupada() {
		return ocupada;
	}

	/**
	 * Devuelve el tipo de habitacion.
	 * * @return "Privada"
	 */
	@Override
	public String getTipoHabitacion() {
		return "Privada";
	}

	/**
	 * Cambia el estado de ocupacion de la habitacion.
	 * * @param ocupada true para marcar como ocupada, false para libre.
	 * <br>Post: el estado de ocupacion se actualiza al valor indicado.
	 */
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
        // Postcondicion
        assert this.ocupada == ocupada : "El estado de ocupacion no se actualizo correctamente.";
	}

	@Override
	public String toString() {
		return "HabitacionPrivada [ocupada=" + ocupada + "] " + super.toString();
	}
}