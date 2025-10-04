package proyecto.modelo.Habitacion;

public class HabitacionPrivada extends Habitacion{
	private boolean ocupada;	
	
	public HabitacionPrivada(double precioBase) {
		super(precioBase);
		this.ocupada = false;
	}

	/*
	 * @pre recibir cantidad de dias > 0
	 * @post retorna el total a pagar
	 */
	
	@Override
	public double calcularPrecio(int cantDias) {
		double precioAPagar = 0;
		double costoHabPrivada = super.getPrecioBase();
		
		if(cantDias == 1)
			precioAPagar = costoHabPrivada;
		else if(cantDias >=2 && cantDias< 6)
			precioAPagar = cantDias*costoHabPrivada*1.3;
		else if(cantDias >=6)
			precioAPagar = cantDias*costoHabPrivada*2;
		
		return precioAPagar;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public  String getTipoHabitacion(){
		return "Privada";
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	@Override
	public String toString() {
		return "HabitacionPrivada [ocupada=" + ocupada + "] " + super.toString();
	}
	
	
	
}
