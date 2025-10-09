package proyecto.modelo.Factura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import proyecto.modelo.ConsultaMedica;
import proyecto.modelo.Internacion;
import proyecto.modelo.paciente.Paciente;

/**
 * Clase que representa una factura generada para un paciente.
 * Contiene información sobre la fecha de ingreso, fecha de egreso,
 * consultas médicas realizadas, posibles internaciones y paciente asociado.
 */
public class Factura {
    /** Contador estático que lleva la cuenta de las facturas generadas. */
    private static int contadorOrdenFactura = 0;
    /** Número de orden único de la factura. */
    private int ordenFactura;
    private Date fechaIngreso;
     /** Lista de consultas médicas asociadas a esta factura. */
    private ArrayList<ConsultaMedica> consultas;
    /** Internacion del paciente, si corresponde */
    private Internacion internacion;
     /** Fecha de egreso del paciente. */
    private Date fechaEgreso;
    /** Paciente asociado a la factura. */
    private Paciente paciente;

     /**
     * Constructor de la clase Factura.
     * Asigna un numero  de orden unico incrementando el contador.
     * 
     * Precondiciones:
     * fechaIngreso no null
     * consultas no null
     * fechaEgreso no null
     * paciente no null
     * Poscondiciones:
     * Creacion de la factura con orden unico
     * 
     * @param fechaIngreso Fecha de ingreso del paciente.
     * @param consultas Lista de consultas medicas asociadas a un paciente.
     * @param internacion Internacion de un paciente, null en caso de que no haya sido internado.
     * @param fechaEgreso Fecha de egreso del paciente.
     * @param paciente Paciente asociado a la factura.
     */
    public Factura( Date fechaIngreso, ArrayList<ConsultaMedica> consultas,Internacion internacion, Date fechaEgreso, Paciente paciente) {
        this.fechaIngreso = fechaIngreso;
        this.consultas = consultas;
        this.internacion = internacion;
        this.fechaEgreso = fechaEgreso;
        this.paciente = paciente;
        this.ordenFactura=++contadorOrdenFactura;
    }


   

    /** 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * devuelve el valor de orden correcto de la factura
     * @return Numero de orden de la factura. 
    */
    public int getOrdenFactura() {
        return ordenFactura;
    }
    /** 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * devuelve la fecha de ingreso correcta del paciente de la factura
     * @return Fecha de ingreso del paciente. 
    */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /** 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se asigna la fecha de ingreso correctamente
     * @param fechaIngreso Asigna la fecha de ingreso del paciente.
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
     /** 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se obtiene la lista de consultas
     *  @return Lista de consultas medicas asociadas a la factura.
     */
    public ArrayList<ConsultaMedica> getConsultas() {
        return consultas;
    }
     /** 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se asigna la lista de consultas correctamente
     * @param consultas Asigna la lista de consultas medicas. 
     */
    public void setConsultas(ArrayList<ConsultaMedica> consultas) {
        this.consultas = consultas;
    }
    
    /**
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se obtiene la internacion correctamente
     *  @return Internacion del paciente asociada a la factura.
     * 
     */
    public Internacion getInternacion() {
        return internacion;
    }

    /** 
     * 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se asigna la internacion correctamente
     * @param internacion Asigna la internacion del paciente. 
     * 
    */
    public void setInternacion(Internacion internacion) {
        this.internacion = internacion;
    }
     /** 
      * Precondiciones:
      * Ninguna
      * Poscondiciones:
      * Se obtiene la fecha de egreso del paciente correctamente
      * @return Fecha de egreso del paciente.
      * 
      */
    public Date getFechaEgreso() {
        return fechaEgreso;
    }
    /** 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se obtiene  paciente correctamente
     * @return Paciente asociado a la factura.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Genera un String de la factura con la informacion relevante:
     * numero de factura, datos del paciente, fechas, internacion y consultas.
     * 
     * Precondiciones:
     * Ninguna
     * Poscondiciones:
     * Se retorna el string representativo de la factura correctamente
     * 
     * @return String con la informacion completa de la factura.
     */
    @Override
    public String toString() {
    StringBuilder factura = new StringBuilder();
    Calendar cal = Calendar.getInstance();

    cal.setTime(this.getFechaIngreso());
    int anio = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH) + 1;
    int dia = cal.get(Calendar.DAY_OF_MONTH);
    String fechaIngreso = String.format("%04d/%02d/%02d", anio, mes, dia); 
    cal.setTime(this.getFechaEgreso());
    anio = cal.get(Calendar.YEAR);
    mes = cal.get(Calendar.MONTH) + 1; 
    dia = cal.get(Calendar.DAY_OF_MONTH);
    String fechaEgreso = String.format("%04d/%02d/%02d", anio, mes, dia);   

    factura.append("N° Factura: ").append(this.ordenFactura).append("\n");
    factura.append("Nombre Paciente: ").append(this.paciente.getNombre()+" "+this.paciente.getApellido()).append("\n");
    factura.append("Fecha Ingreso: ").append(fechaIngreso).append("\n");
    factura.append("Fecha Egreso: ").append(fechaEgreso).append("\n");

    // Si hay internación
    if (internacion != null) {
        factura.append("Cantidad de días: ")
          .append(internacion.getCantidadDiasInternacion())
          .append("\n");
        factura.append("Habitación tipo: ")
          .append(internacion.getH().getTipoHabitacion())
          .append("   Costo: $ ")
          .append(internacion.getH().calcularPrecio(internacion.getCantidadDiasInternacion()))
          .append("\n");
    }

    factura.append("\nConsultas Médicas:\n");
    double suma = 0;

    if (consultas != null && !consultas.isEmpty()) {
        for (ConsultaMedica c : consultas) {
            factura.append("Médico: ").append(c.getMedico().getNombre()+" "+c.getMedico().getApellido()).append("\n");
            factura.append("Especialidad: ").append(c.getMedico().getEspecialidad()).append("\n");
            suma += c.getMedico().calcularSueldo()*1.2;
            factura.append("Subtotal: $ ").append(c.getMedico().calcularSueldo()*1.2).append("\n\n");
        }
    }
    factura.append("Total: ").append(String.format("%.2f", suma)).append("\n");
    return factura.toString();
}

}
