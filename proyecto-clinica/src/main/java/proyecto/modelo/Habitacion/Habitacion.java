package proyecto.modelo.Habitacion;

public abstract class Habitacion {
	private double precioBase;
	
	public Habitacion(double precioBase) {
		this.precioBase = precioBase;
	}

	public abstract double calcularPrecio(int cantDias);

	public double getPrecioBase() {
		return precioBase;
	}

	@Override
	public String toString() {
		return "Habitacion [precioBase=" + precioBase + "] ";
	}
}
