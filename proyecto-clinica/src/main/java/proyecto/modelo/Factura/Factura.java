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
    private static int contadorOrdenFactura = 0;
    private final int ordenFactura;
    private final String nombrePaciente;
    private final String apellidoPaciente;
    private final String fechaIngreso;
    private final String fechaEgreso;
    private final Integer cantidadDiasInternacion;
    private final String tipoHabitacion;
    private final Double costoHabitacion;
    private final ArrayList<String> consultas;
    private final Double total;

    /**
     * Constructor de la clase Factura.
     * Pre: Los parámetros no deben ser nulos (excepto internacion si no hubo)
     * Post: Se crea una factura con los datos historicos e inmutables
     */
    public Factura(Date fechaIngreso, ArrayList<ConsultaMedica> consultasMedicas, Internacion internacion,
            Date fechaEgreso, Paciente paciente) {
        this.ordenFactura = ++contadorOrdenFactura;
        this.nombrePaciente = paciente.getNombre();
        this.apellidoPaciente = paciente.getApellido();
        this.fechaIngreso = formatearFecha(fechaIngreso);
        this.fechaEgreso = formatearFecha(fechaEgreso);
        if (internacion != null) {
            this.cantidadDiasInternacion = internacion.getCantidadDiasInternacion();
            this.tipoHabitacion = internacion.getH().getTipoHabitacion();
            this.costoHabitacion = internacion.getH().calcularPrecio(internacion.getCantidadDiasInternacion());
        } else {
            this.cantidadDiasInternacion = null;
            this.tipoHabitacion = null;
            this.costoHabitacion = null;
        }
        this.consultas = new ArrayList<>();
        double suma = (costoHabitacion != null) ? costoHabitacion : 0;
        if (consultasMedicas != null) {
            for (ConsultaMedica c : consultasMedicas) {
                String consultaStr = "Médico: " + c.getMedico().getNombre() + " " + c.getMedico().getApellido() + "\n" +
                        "Especialidad: " + c.getMedico().getEspecialidad() + "\n" +
                        "Subtotal: $ " + String.format("%.2f", c.getMedico().calcularSueldo() * 1.2) + "\n";
                this.consultas.add(consultaStr);
                suma += c.getMedico().calcularSueldo() * 1.2;
            }
        }
        this.total = suma;
    }

    /**
     * Formatea una fecha a String yyyy/MM/dd.
     * Pre: fecha no nula.
     * Post: Devuelve la fecha en formato String.
     */
    private String formatearFecha(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return String.format("%04d/%02d/%02d", anio, mes, dia);
    }

    /**
     * Devuelve el número de orden de la factura.
     * Pre: ninguna.
     * Post: retorna el número único de la factura.
     */
    public int getOrdenFactura() {
        return ordenFactura;
    }

    /**
     * Devuelve el nombre del paciente.
     * Pre: ninguna.
     * Post: retorna el nombre guardado en la factura.
     */
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    /**
     * Devuelve el apellido del paciente.
     * Pre: ninguna.
     * Post: retorna el apellido guardado en la factura.
     */
    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    /**
     * Devuelve la fecha de ingreso.
     * Pre: ninguna.
     * Post: retorna la fecha de ingreso como String.
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Devuelve la fecha de egreso.
     * Pre: ninguna.
     * Post: retorna la fecha de egreso como String.
     */
    public String getFechaEgreso() {
        return fechaEgreso;
    }

    /**
     * Devuelve la cantidad de días de internación.
     * Pre: ninguna.
     * Post: retorna la cantidad de días o null si no hubo internación.
     */
    public Integer getCantidadDiasInternacion() {
        return cantidadDiasInternacion;
    }

    /**
     * Devuelve el tipo de habitación.
     * Pre: ninguna.
     * Post: retorna el tipo de habitación o null si no hubo internación.
     */
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    /**
     * Devuelve el costo de la habitación.
     * Pre: ninguna.
     * Post: retorna el costo o null si no hubo internación.
     */
    public Double getCostoHabitacion() {
        return costoHabitacion;
    }

    /**
     * Devuelve la lista de consultas médicas como Strings.
     * Pre: ninguna.
     * Post: retorna una copia de la lista de consultas.
     */
    public ArrayList<String> getConsultas() {
        return new ArrayList<>(consultas);
    }

    /**
     * Devuelve el total de la factura.
     * Pre: ninguna.
     * Post: retorna el total calculado.
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Devuelve la factura como String con toda la información.
     * Pre: ninguna.
     * Post: retorna el resumen de la factura.
     */
    @Override
    public String toString() {
        StringBuilder factura = new StringBuilder();
        factura.append("N° Factura: ").append(this.ordenFactura).append("\n");
        factura.append("Nombre Paciente: ").append(this.nombrePaciente + " " + this.apellidoPaciente).append("\n");
        factura.append("Fecha Ingreso: ").append(this.fechaIngreso).append("\n");
        factura.append("Fecha Egreso: ").append(this.fechaEgreso).append("\n");
        if (cantidadDiasInternacion != null) {
            factura.append("Cantidad de días: ").append(cantidadDiasInternacion).append("\n");
            factura.append("Habitación tipo: ").append(tipoHabitacion).append("   Costo: $ ").append(costoHabitacion)
                    .append("\n");
        }
        factura.append("\nConsultas Médicas:\n");
        if (consultas != null && !consultas.isEmpty()) {
            for (String consulta : consultas) {
                factura.append(consulta).append("\n");
            }
        }
        factura.append("Total: ").append(String.format("%.2f", total)).append("\n");
        return factura.toString();
    }

}
