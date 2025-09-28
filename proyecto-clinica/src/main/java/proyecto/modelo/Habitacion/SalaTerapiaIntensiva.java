package proyecto.modelo.Habitacion;

public class SalaTerapiaIntensiva extends Habitacion {

	public SalaTerapiaIntensiva(double precioBase) {
		super(precioBase);
	}

	/**
	 * @pre paciente debe de estar dado de alta.
	 * @post retorna un double con el calculo del precio de la sala de terapia intensiva.
	 */
	
	@Override
	public double calcularPrecio(int cantDias) {
		double costoTerapiaIntensiva = super.getPrecioBase();
		return  Math.pow(costoTerapiaIntensiva, cantDias);
	}

	@Override
	public String toString() {
		return "SalaTerapiaIntensiva " + super.toString();
	}
}
