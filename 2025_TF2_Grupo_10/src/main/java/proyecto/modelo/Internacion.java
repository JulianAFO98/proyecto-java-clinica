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
 * * Esta clase permite calcular automaticamente la cantidad de dias
 * transcurridos entre la fecha de ingreso y la fecha de alta,
 * asi como obtener una fecha estimada agregando una cantidad de dias a la fecha
 * inicial.
 * * @author (tu nombre)
 */
public class Internacion {
	private Paciente paciente;
	private Habitacion h;
	private int cantidadDiasInternacion;
	private Date fechaInternacion;

	/**
	 * Crea una internacion para un paciente en una habitacion determinada.
	 * * @param paciente         objeto de tipo Paciente que representa al paciente
	 *                         internado.
	 * @param h                habitacion asignada al paciente.
	 * @param fechaInternacion fecha de ingreso del paciente.
	 * <br>Pre: paciente != null, h != null, fechaInternacion != null
	 * <br>Post: se inicializa la internacion con 0 dias y la fecha de ingreso indicada.
	 */
	public Internacion(Paciente paciente, Habitacion h, Date fechaInternacion) {
		assert paciente != null : "El paciente no puede ser nulo.";
		assert h != null : "La habitacion no puede ser nula.";
		assert fechaInternacion != null : "La fecha de internacion no puede ser nula.";
		assert fechaInternacion.compareTo(new Date()) <= 0 : "La fecha de internacion no puede ser futura.";
		
		this.paciente = paciente;
		this.h = h;
		this.cantidadDiasInternacion = 0;
		this.fechaInternacion = fechaInternacion;
		
		assert this.cantidadDiasInternacion == 0 : "La cantidad inicial de dias debe ser cero.";
	}

	/**
	 * Calcula la cantidad de dias que el paciente estuvo internado,
	 * en base a la fecha de alta recibida.
	 * * @param fechaDadoDeAlta fecha en que el paciente fue dado de alta.
	 * <br>Pre: fechaDadoDeAlta != null y fechaDadoDeAlta posterior a fechaInternacion.
	 * <br>Post: se actualiza el atributo cantidadDiasInternacion con la diferencia de
	 *       dias entre ambas fechas.
	 */
	public void darDeAlta(Date fechaDadoDeAlta) {
		assert fechaDadoDeAlta != null : "La fecha de alta no puede ser nula.";
		assert this.fechaInternacion != null : "La fecha de internacion no debe ser nula.";
		assert fechaDadoDeAlta.compareTo(this.fechaInternacion) >= 0 : "La fecha de alta debe ser posterior o igual a la de ingreso.";
		
		long diffMillis = fechaDadoDeAlta.getTime() - this.fechaInternacion.getTime();
		int dias = (int) TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
		
		int cantidadDiasPre = this.cantidadDiasInternacion;
		
		setCantidadDiasInternacion(dias);
		
		assert this.cantidadDiasInternacion >= cantidadDiasPre : "La cantidad de dias de internacion debe aumentar o mantenerse.";
	}

	/**
	 * Retorna una nueva fecha sumando una cantidad de dias a la fecha de
	 * internacion.
	 * * @param dias cantidad de dias a sumar.
	 * @return objeto Date con la nueva fecha resultante.
	 * <br>Pre: dias >= 0
	 * <br>Post: retorna una nueva fecha sin modificar la fecha original de internacion.
	 */
	public Date obtenerFechaConDiasSumados(int dias) {
		assert dias >= 0 : "La cantidad de dias a sumar debe ser un valor no negativo.";
		assert this.fechaInternacion != null : "La fecha de internacion no debe ser nula para realizar el calculo.";
		
		Date fechaOriginal = (Date) this.fechaInternacion.clone();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.fechaInternacion);
		calendar.add(Calendar.DAY_OF_MONTH, dias);
		Date fechaResultante = calendar.getTime();
		
		assert this.fechaInternacion.equals(fechaOriginal) : "La fecha de internacion original no debe ser modificada.";
		assert fechaResultante.compareTo(this.fechaInternacion) >= 0 : "La fecha resultante debe ser posterior o igual a la de internacion.";

		return fechaResultante;
	}

	/**
	 * Establece la cantidad de dias de internacion del paciente.
	 * * @param cantidadDiasInternacion cantidad total de dias internado.
	 * <br>Pre: cantidadDiasInternacion >= 0
	 * <br>Post: se actualiza el atributo cantidadDiasInternacion con el valor indicado.
	 */
	private void setCantidadDiasInternacion(int cantidadDiasInternacion) {
		assert cantidadDiasInternacion >= 0 : "La cantidad de dias de internacion debe ser no negativa.";
		this.cantidadDiasInternacion = cantidadDiasInternacion;
		assert this.cantidadDiasInternacion == cantidadDiasInternacion : "La cantidad de dias debe haberse actualizado correctamente.";
	}

	/**
	 * Retorna el paciente asociado a la internacion.
	 * * @return el paciente internado.
	 */
	public Paciente getPaciente() {
		assert paciente != null : "El paciente asociado a la internacion no debe ser nulo.";
		return paciente;
	}

	/**
	 * Retorna la habitacion asignada al paciente internado.
	 * * @return objeto Habitacion correspondiente a la internacion.
	 */
	public Habitacion getH() {
		assert h != null : "La habitacion asignada no debe ser nula.";
		return h;
	}

	/**
	 * Retorna la cantidad de dias de internacion registrados.
	 * * @return numero de dias de internacion.
	 */
	public int getCantidadDiasInternacion() {
		assert cantidadDiasInternacion >= 0 : "La cantidad de dias de internacion debe ser no negativa.";
		return cantidadDiasInternacion;
	}

	/**
	 * Retorna la fecha en que comenzo la internacion.
	 * * @return fecha de ingreso del paciente.
	 */
	public Date getFechaInternacion() {
		assert fechaInternacion != null : "La fecha de internacion no debe ser nula.";
		return fechaInternacion;
	}

	@Override
	public String toString() {
		return "Internacion [paciente=" + paciente + ", h=" + h + ", cantidadDiasInternacion="
				+ cantidadDiasInternacion + ", fechaInternacion=" + fechaInternacion + "]";
	}
}