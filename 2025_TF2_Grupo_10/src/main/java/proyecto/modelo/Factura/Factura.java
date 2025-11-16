package proyecto.modelo.Factura;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import proyecto.modelo.ConsultaMedica;
import proyecto.modelo.Internacion;
import proyecto.modelo.paciente.Paciente;

/**
 * Clase que representa una factura generada para un paciente.
 * Contiene informacion sobre la fecha de ingreso, fecha de egreso,
 * consultas medicas realizadas, posibles internaciones y paciente asociado.
 */
public class Factura {
    private static int contadorOrdenFactura = 0;
    private int ordenFactura;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String fechaIngreso;
    private String fechaEgreso;
    private Integer cantidadDiasInternacion;
    private String tipoHabitacion;
    private Double costoHabitacion;
    private ArrayList<String> consultas;
    private Double total;

    /**
     * Constructor de la clase Factura.
     * <br>Pre: Los parametros no deben ser nulos (excepto internacion si no hubo)
     * <br>Post: Se crea una factura con los datos historicos e inmutables
     */
    public Factura(Date fechaIngreso, ArrayList<ConsultaMedica> consultasMedicas, Internacion internacion,
            Date fechaEgreso, Paciente paciente) {

        // Precondiciones
        assert fechaIngreso != null : "La fecha de ingreso no puede ser nula.";
        assert fechaEgreso != null : "La fecha de egreso no puede ser nula.";
        assert paciente != null : "El paciente no puede ser nulo.";
        assert fechaEgreso.compareTo(fechaIngreso) >= 0 : "La fecha de egreso debe ser posterior o igual a la fecha de ingreso.";

        int contadorPre = contadorOrdenFactura;
        double totalPre = 0.0;
        
        this.ordenFactura = ++contadorOrdenFactura;
        this.nombrePaciente = paciente.getNombre();
        this.apellidoPaciente = paciente.getApellido();
        this.fechaIngreso = formatearFecha(fechaIngreso);
        this.fechaEgreso = formatearFecha(fechaEgreso);
        
        if (internacion != null) {
            this.cantidadDiasInternacion = internacion.getCantidadDiasInternacion();
            this.tipoHabitacion = internacion.getH().getTipoHabitacion();
            this.costoHabitacion = internacion.getH().calcularPrecio(internacion.getCantidadDiasInternacion());
            this.total = this.costoHabitacion;
            totalPre = this.costoHabitacion;
            
            // Invariantes/Postcondiciones de Internacion
            assert this.cantidadDiasInternacion >= 0 : "La cantidad de dias de internacion no puede ser negativa.";
            assert this.tipoHabitacion != null && !this.tipoHabitacion.trim().isEmpty() : "El tipo de habitacion no puede ser nulo o vacio.";
            assert this.costoHabitacion >= 0 : "El costo de la habitacion no puede ser negativo.";
        } else {
            this.total = 0.0;
            this.cantidadDiasInternacion = null;
            this.tipoHabitacion = null;
            this.costoHabitacion = null;
        }
        
        this.consultas = new ArrayList<>();

        double costoConsultasTotal = 0.0;
        
        if (consultasMedicas != null) {
            for (ConsultaMedica c : consultasMedicas) {
                assert c != null && c.getMedico() != null : "La consulta medica o su medico asociado no pueden ser nulos.";
                
                double costoConsulta = c.getMedico().calcularSueldo() * 1.2;
                costoConsultasTotal += costoConsulta;
                
                String consultaStr = "Medico: " + c.getMedico().getNombre() + " " + c.getMedico().getApellido() + "\n" +
                        "Especialidad: " + c.getMedico().getEspecialidad() + "\n" +
                        "Subtotal: $ " + String.format("%.2f", costoConsulta) + "\n";
                this.consultas.add(consultaStr);
                this.total += costoConsulta;
            }
        }
        
        // Postcondiciones finales
        assert this.ordenFactura > contadorPre : "La orden de factura no se incremento correctamente.";
        assert this.nombrePaciente != null && !this.nombrePaciente.trim().isEmpty() : "El nombre del paciente no se asigno correctamente.";
        assert this.apellidoPaciente != null && !this.apellidoPaciente.trim().isEmpty() : "El apellido del paciente no se asigno correctamente.";
        assert this.fechaIngreso != null && !this.fechaIngreso.trim().isEmpty() : "La fecha de ingreso no se asigno correctamente.";
        assert this.fechaEgreso != null && !this.fechaEgreso.trim().isEmpty() : "La fecha de egreso no se asigno correctamente.";
        
        double totalCalculadoEsperado = totalPre + costoConsultasTotal;
        assert Math.abs(this.total - totalCalculadoEsperado) < 0.01 : "El total de la factura no se calculo correctamente.";
    }

    /**
     * Formatea una fecha a String yyyy/MM/dd.
     * <br>Pre: fecha no nula.
     * <br>Post: Devuelve la fecha en formato String.
     */
    private String formatearFecha(Date fecha) {
        // Precondicion
        assert fecha != null : "La fecha a formatear no puede ser nula.";

        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        String fechaFormateada = String.format("%04d/%02d/%02d", anio, mes, dia);
        
        // Postcondicion
        assert fechaFormateada != null && fechaFormateada.length() == 10 : "La fecha no se formateo correctamente a yyyy/MM/dd.";
        
        return fechaFormateada;
    }

    /**
     * Devuelve el numero de orden de la factura.
     * <br>Pre: ninguna.
     * <br>Post: retorna el numero unico de la factura.
     */
    public int getOrdenFactura() {
        assert ordenFactura > 0 : "La orden de factura debe ser un numero positivo.";
        return ordenFactura;
    }

    /**
     * Devuelve el nombre del paciente.
     * <br>Pre: ninguna.
     * <br>Post: retorna el nombre guardado en la factura.
     */
    public String getNombrePaciente() {
        assert nombrePaciente != null && !nombrePaciente.trim().isEmpty() : "El nombre del paciente no debe ser nulo o vacio.";
        return nombrePaciente;
    }

    /**
     * Devuelve el apellido del paciente.
     * <br>Pre: ninguna.
     * <br>Post: retorna el apellido guardado en la factura.
     */
    public String getApellidoPaciente() {
        assert apellidoPaciente != null && !apellidoPaciente.trim().isEmpty() : "El apellido del paciente no debe ser nulo o vacio.";
        return apellidoPaciente;
    }

    /**
     * Devuelve la fecha de ingreso.
     * <br>Pre: ninguna.
     * <br>Post: retorna la fecha de ingreso como String.
     */
    public String getFechaIngreso() {
        assert fechaIngreso != null && !fechaIngreso.trim().isEmpty() : "La fecha de ingreso no debe ser nula o vacia.";
        return fechaIngreso;
    }

    /**
     * Devuelve la fecha de egreso.
     * <br>Pre: ninguna.
     * <br>Post: retorna la fecha de egreso como String.
     */
    public String getFechaEgreso() {
        assert fechaEgreso != null && !fechaEgreso.trim().isEmpty() : "La fecha de egreso no debe ser nula o vacia.";
        return fechaEgreso;
    }

    /**
     * Devuelve la cantidad de dias de internacion.
     * <br>Pre: ninguna.
     * <br>Post: retorna la cantidad de dias o null si no hubo internacion.
     */
    public Integer getCantidadDiasInternacion() {
        if (cantidadDiasInternacion != null) {
            assert cantidadDiasInternacion >= 0 : "La cantidad de dias de internacion no puede ser negativa.";
        }
        return cantidadDiasInternacion;
    }

    /**
     * Devuelve el tipo de habitacion.
     * <br>Pre: ninguna.
     * <br>Post: retorna el tipo de habitacion o null si no hubo internacion.
     */
    public String getTipoHabitacion() {
        if (tipoHabitacion != null) {
            assert !tipoHabitacion.trim().isEmpty() : "El tipo de habitacion no debe ser vacio si no es nulo.";
        }
        return tipoHabitacion;
    }

    /**
     * Devuelve el costo de la habitacion.
     * <br>Pre: ninguna.
     * <br>Post: retorna el costo o null si no hubo internacion.
     */
    public Double getCostoHabitacion() {
        if (costoHabitacion != null) {
            assert costoHabitacion >= 0 : "El costo de la habitacion no puede ser negativo.";
        }
        return costoHabitacion;
    }


    /**
     * Devuelve el total de la factura.
     * <br>Pre: ninguna.
     * <br>Post: retorna el total calculado.
     */
    public Double getTotal() {
        assert total != null && total >= 0 : "El total de la factura no debe ser nulo o negativo.";
        return total;
    }

    
    
    /**
     * Devuelve la factura como String con toda la informacion.
     * <br>Pre: ninguna.
     * <br>Post: retorna el resumen de la factura.
     */
    @Override
    public String toString() {
        StringBuilder factura = new StringBuilder();
        factura.append("N\u00b0 Factura: ").append(this.ordenFactura).append("\n");
        factura.append("Nombre Paciente: ").append(this.nombrePaciente + " " + this.apellidoPaciente).append("\n");
        factura.append("Fecha Ingreso: ").append(this.fechaIngreso).append("\n");
        factura.append("Fecha Egreso: ").append(this.fechaEgreso).append("\n");
        if (cantidadDiasInternacion != null) {
            factura.append("Cantidad de dias: ").append(cantidadDiasInternacion).append("\n");
            factura.append("Habitacion tipo: ").append(tipoHabitacion).append("    Costo: $ ").append(costoHabitacion)
                    .append("\n");
        }
        factura.append("\nConsultas Medicas:\n");
        if (consultas != null && !consultas.isEmpty()) {
            for (String consulta : consultas) {
                factura.append(consulta).append("\n");
            }
        }
        factura.append("Total: ").append(String.format("%.2f", total)).append("\n");
        return factura.toString();
    }

}