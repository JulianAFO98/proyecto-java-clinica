package proyecto.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.paciente.Paciente;

public class Internacion {
	private Paciente paciente;
	private Habitacion h;
	private int cantidadDiasInternacion;
	private Date fechaInternacion;
	
	public Internacion(Paciente paciente, Habitacion h, Date fechaInternacion) {
		this.paciente = paciente;
		this.h = h;
		this.cantidadDiasInternacion = 0;
		this.fechaInternacion = fechaInternacion;
	}
	
	public void darDeAlta(Date fechaDadoDeAlta) {
		long diffMillis = fechaDadoDeAlta.getTime() - this.fechaInternacion.getTime();
		int dias = (int) TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
		setCantidadDiasInternacion(dias);
	}
	
	public Date obtenerFechaConDiasSumados(int dias) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(this.fechaInternacion);
    	calendar.add(Calendar.DAY_OF_MONTH, dias);
    	return calendar.getTime();
	}

	private void setCantidadDiasInternacion(int cantidadDiasInternacion) {
		this.cantidadDiasInternacion = cantidadDiasInternacion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Habitacion getH() {
		return h;
	}

	public int getCantidadDiasInternacion() {
		return cantidadDiasInternacion;
	}

	public Date getFechaInternacion() {
		return fechaInternacion;
	}

	@Override
	public String toString() {
		return "Internacion [paciente=" + paciente + ", h=" + h + ", cantidadDiasInternacion=" + cantidadDiasInternacion
				+ ", fechaInternacion=" + fechaInternacion + "]";
	}
}
