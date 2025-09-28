package proyecto.modelo.Habitacion;

public class HabitacionCompartida extends Habitacion{

	public HabitacionCompartida(double precioBase) {
		super(precioBase);
	}

	@Override
	public double calcularPrecio(int cantDias) {
		return cantDias*super.getPrecioBase();
	}

	@Override
	public String toString() {
		return "HabitacionCompartida " + super.toString();
	}
}
