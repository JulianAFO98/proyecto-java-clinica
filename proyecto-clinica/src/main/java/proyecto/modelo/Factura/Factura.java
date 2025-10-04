package proyecto.modelo.Factura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import proyecto.modelo.ConsultaMedica;
import proyecto.modelo.Internacion;
import proyecto.modelo.paciente.Paciente;

public class Factura {
    private static int contadorOrdenFactura = 0;
    private int ordenFactura;
    private Date fechaIngreso;
    private ArrayList<ConsultaMedica> consultas;
    private Internacion internacion;
    private Date fechaEgreso;
    private Paciente paciente;



   


    public Factura( Date fechaIngreso, ArrayList<ConsultaMedica> consultas,Internacion internacion, Date fechaEgreso, Paciente paciente) {
        this.fechaIngreso = fechaIngreso;
        this.consultas = consultas;
        this.internacion = internacion;
        this.fechaEgreso = fechaEgreso;
        this.paciente = paciente;
        this.ordenFactura=++contadorOrdenFactura;
    }


   


    public int getOrdenFactura() {
        return ordenFactura;
    }

    public void setOrdenFactura(int ordenFactura) {
        this.ordenFactura = ordenFactura;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }


    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public ArrayList<ConsultaMedica> getConsultas() {
        return consultas;
    }
    public void setConsultas(ArrayList<ConsultaMedica> consultas) {
        this.consultas = consultas;
    }
    

    public Internacion getInternacion() {
        return internacion;
    }





    public void setInternacion(Internacion internacion) {
        this.internacion = internacion;
    }





    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /*@Override
    public String toString() {
        return "N°Factura:" + this.ordenFactura+"\n"
        +"Nombre Paciente: "+this.paciente.getNombre()+"\n"
        +"Fecha Ingreso: "+ this.getFechaIngreso()+"\n"
        +"Fecha Ingreso: "+ this.getFechaEgreso()+"\n"
        +(this.internaciones==null ? "" : "Cantidad de dias: "+ internaciones.get(0).getCantidadDiasInternacion())+"\n"
        +"Habitación:"
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
            factura.append("Nombre: ").append(c.getPaciente().getNombre()).append("   ");
            factura.append("Médico: ").append(c.getMedico().getNombre()).append("\n");
            factura.append("Especialidad: ").append(c.getMedico().getEspecialidad()).append("\n");
            suma += c.getMedico().calcularSueldo();
            factura.append("Subtotal: $ ").append(c.getMedico().calcularSueldo()).append("\n\n");
        }
    }
    factura.append("Total: ").append(String.format("%.2f", suma)).append("\n");
    return factura.toString();
}

    

}
