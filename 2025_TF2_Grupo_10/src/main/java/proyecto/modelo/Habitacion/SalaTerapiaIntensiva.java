package proyecto.modelo.Habitacion;

/**
 * Representa una sala de terapia intensiva.
 * Su precio se calcula elevando el precio base a la potencia de la cantidad de
 * dias.
 * * Este tipo de habitacion es especial y se asume que solo se cobra cuando el
 * paciente fue dado de alta.
 * */
public class SalaTerapiaIntensiva extends Habitacion {

	/**
	 * Crea una sala de terapia intensiva con el precio base indicado.
	 * * @param precioBase valor diario base de la sala.
	 * <br>Pre:n precioBase > 0
	 * <br>Post: se crea una sala de terapia intensiva con el precio base recibido.
	 */
	public SalaTerapiaIntensiva(double precioBase) {
		super(precioBase);
        assert this.getTipoHabitacion().equals("Sala de Terapia Intensiva") : "El tipo de habitacion debe ser Sala de Terapia Intensiva.";
	}

	/**
	 * Calcula el precio total de la sala de terapia intensiva.
	 * * @param cantDias cantidad de dias de uso de la sala.
	 * @return precio total calculado como (precioBase ^ cantDias).
	 * <br>Pre: n paciente debe estar dado de alta y cantDias > 0
	 * <br>Post: retorna el monto total correspondiente a la estadia.
	 */
	@Override
	public double calcularPrecio(int cantDias) {
        // Precondicion
        assert cantDias > 0 : "La cantidad de dias debe ser positiva.";

		double costoTerapiaIntensiva = super.getPrecioBase();
        assert costoTerapiaIntensiva > 0 : "El costo base debe ser positivo para el calculo.";
        
		double precioTotal = Math.pow(costoTerapiaIntensiva, cantDias);

        // Postcondicion
        assert precioTotal >= 0 : "El precio total calculado no puede ser negativo.";
        assert Math.abs(precioTotal - Math.pow(costoTerapiaIntensiva, cantDias)) < 0.0001 : "El precio total no se calculo correctamente (potencia).";

		return precioTotal;
	}

	/**
	 * Devuelve el tipo de habitacion.
	 * * @return "Sala de Terapia Intensiva"
	 */
	@Override
	public String getTipoHabitacion() {
		return "Sala de Terapia Intensiva";
	}

	@Override
	public String toString() {
		return "SalaTerapiaIntensiva " + super.toString();
	}
}