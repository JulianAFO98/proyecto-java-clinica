package proyecto.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.paciente.Paciente;

/**
 * Representa la internacion de un paciente en una habitacion del
 * establecimiento.
 * Contiene la informacion sobre el paciente, la habitacion asignada,
 * la fecha de ingreso y la cantidad de dias de internacion.
 * 
 * Esta clase permite calcular automaticamente la cantidad de dias
 * transcurridos entre la fecha de ingreso y la fecha de alta,
 * asi como obtener una fecha estimada agregando una cantidad de dias a la fecha
 * inicial.
 * 
 * @author (tu nombre)
 */
public class Internacion {
	private Paciente paciente;
	private Habitacion h;
	private int cantidadDiasInternacion;
	private Date fechaInternacion;

	/**
	 * Crea una internacion para un paciente en una habitacion determinada.
	 * 
	 * @param paciente         objeto de tipo Paciente que representa al paciente
	 *                         internado.
	 * @param h                habitacion asignada al paciente.
	 * @param fechaInternacion fecha de ingreso del paciente.
	 * @pre paciente != null, h != null, fechaInternacion != null
	 * @post se inicializa la internacion con 0 dias y la fecha de ingreso indicada.
	 */
	public Internacion(Paciente paciente, Habitacion h, Date fechaInternacion) {
		this.paciente = paciente;
		this.h = h;
		this.cantidadDiasInternacion = 0;
		this.fechaInternacion = fechaInternacion;
	}

	/**
	 * Calcula la cantidad de dias que el paciente estuvo internado,
	 * en base a la fecha de alta recibida.
	 * 
	 * @param fechaDadoDeAlta fecha en que el paciente fue dado de alta.
	 * @pre fechaDadoDeAlta != null y fechaDadoDeAlta posterior a fechaInternacion.
	 * @post se actualiza el atributo cantidadDiasInternacion con la diferencia de
	 *       dias entre ambas fechas.
	 */
	public void darDeAlta(Date fechaDadoDeAlta) {
		long diffMillis = fechaDadoDeAlta.getTime() - this.fechaInternacion.getTime();
		int dias = (int) TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
		setCantidadDiasInternacion(dias);
	}

	/**
	 * Retorna una nueva fecha sumando una cantidad de dias a la fecha de
	 * internacion.
	 * 
	 * @param dias cantidad de dias a sumar.
	 * @return objeto Date con la nueva fecha resultante.
	 * @pre dias >= 0
	 * @post retorna una nueva fecha sin modificar la fecha original de internacion.
	 */
	public Date obtenerFechaConDiasSumados(int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.fechaInternacion);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		return calendar.getTime();
	}

	/**
	 * Establece la cantidad de dias de internacion del paciente.
	 * 
	 * @param cantidadDiasInternacion cantidad total de dias internado.
	 * @pre cantidadDiasInternacion >= 0
	 * @post se actualiza el atributo cantidadDiasInternacion con el valor indicado.
	 */
	private void setCantidadDiasInternacion(int cantidadDiasInternacion) {
		this.cantidadDiasInternacion = cantidadDiasInternacion;
	}

	/**
	 * Retorna el paciente asociado a la internacion.
	 * 
	 * @return el paciente internado.
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * Retorna la habitacion asignada al paciente internado.
	 * 
	 * @return objeto Habitacion correspondiente a la internacion.
	 */
	public Habitacion getH() {
		return h;
	}

	/**
	 * Retorna la cantidad de dias de internacion registrados.
	 * 
	 * @return numero de dias de internacion.
	 */
	public int getCantidadDiasInternacion() {
		return cantidadDiasInternacion;
	}

	/**
	 * Retorna la fecha en que comenzo la internacion.
	 * 
	 * @return fecha de ingreso del paciente.
	 */
	public Date getFechaInternacion() {
		return fechaInternacion;
	}

	@Override
	public String toString() {
		return "Internacion [paciente=" + paciente + ", h=" + h + ", cantidadDiasInternacion="
				+ cantidadDiasInternacion + ", fechaInternacion=" + fechaInternacion + "]";
	}
}
