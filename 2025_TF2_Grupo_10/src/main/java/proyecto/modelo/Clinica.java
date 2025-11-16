package proyecto.modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import proyecto.modelo.Excepciones.NoExistePacienteException;
import proyecto.modelo.Factura.Factura;
import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Paciente;

/**
 * Clase que representa una clinica,aplicadora del patron facade.
 * Se pueden registrar pacientes, medicos, internaciones y consultas.
 * Tambien se puede egresar pacientes y calcular honorarios de medicos.
 */
public class Clinica {
    private String nombreClinica;
    private String telefono;
    private String direccion;
    private SalaEsperaPrivada salaEsperaPrivada = new SalaEsperaPrivada(null);
    private ArrayList<Paciente> patio = new ArrayList<Paciente>();
    private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
    private ArrayList<ConsultaMedica> consultaMedicas = new ArrayList<ConsultaMedica>();
    private ArrayList<Paciente> pacientesRegistrados = new ArrayList<Paciente>();
    private ArrayList<Paciente> pacientesEnAtencion = new ArrayList<Paciente>();
    private ArrayList<IMedico> medicos = new ArrayList<IMedico>();
    private ArrayList<Internacion> internaciones = new ArrayList<Internacion>();

    
     /**
     * Constructor de la clinica
     * @param nombreClinica nombre de la clinica
     * @param telefono telefono de la clinica
     * @param direccion direccion de la clinica
     * <br>Pre: los parametros no deben ser null
     * <br>Post: se crea la clinica con listas vacias y sala de espera vacia
     */
    public Clinica(String nombreClinica, String telefono, String direccion) {
        assert nombreClinica != null && !nombreClinica.trim().isEmpty() : "El nombre de la clinica no debe ser nulo o vacio.";
        assert telefono != null && !telefono.trim().isEmpty() : "El telefono no debe ser nulo o vacio.";
        assert direccion != null && !direccion.trim().isEmpty() : "La direccion no debe ser nula o vacia.";
        
        this.nombreClinica = nombreClinica;
        this.telefono = telefono;
        this.direccion = direccion;

        assert salaEsperaPrivada != null : "La SalaEsperaPrivada debe estar inicializada.";
    }

    /**
     * Registra un paciente la clininca
     * @param p paciente a registrar
     * <br>Pre: p no debe ser null
     * <br>Post: el paciente queda agregado a pacientesRegistrados
     */
    public void registrarPaciente(Paciente p) {
        assert p != null : "El paciente a registrar no puede ser nulo.";
        
        pacientesRegistrados.add(p);

        assert pacientesRegistrados.contains(p) : "El paciente debe haber sido agregado a la lista de registrados.";
    }

     /**
     * Ingresa un paciente a la clinica
     * @param p paciente a ingresar
     * @throws NoExistePacienteException si el paciente no esta registrado
     * <br>Pre: p no debe ser null
     * <br>Post: el paciente queda en sala de espera o en el patio
     */
    public void ingresarPaciente(Paciente p) throws NoExistePacienteException {
        assert p != null : "El paciente a ingresar no puede ser nulo.";
        
        boolean existePaciente = pacientesRegistrados.contains(p);

        // si no existe tiramos excepcion
        if (!existePaciente) {
            throw new NoExistePacienteException("No esta registrado el paciente");
        }
        
        assert pacientesRegistrados.contains(p) : "El paciente debe estar registrado para su ingreso.";
        
        p.setNumeroOrdenPropio();
        if (salaEsperaPrivada.getPaciente() == null) {
            salaEsperaPrivada.setPaciente(p);
        } else {
            Paciente pacienteActual = salaEsperaPrivada.getPaciente();
            Paciente pacienteGanador = pacienteActual.decidirSala(p);
            // perdedor al patio
            if (pacienteActual.equals(pacienteGanador)) {
                patio.add(p);
            } else {
                patio.add(pacienteActual);
                // ganador a la sala
                salaEsperaPrivada.setPaciente(pacienteGanador);
            }
        }
        
        assert salaEsperaPrivada.getPaciente() == p || patio.contains(p) : "El paciente debe quedar en la Sala de Espera o en el Patio.";
    }
    
    /**
     * Atiende un paciente con un medico
     * @param m medico que atiende
     * @param p paciente a atender
     * @throws NoExistePacienteException si el paciente no esta en sala ni patio ni en atencion
     * <br>Pre: 
     * m y p no deben ser null
     * <br>Post: 
     * se agrega una consulta medica del paciente con el medico con fechas en null
     */
    public void atiendePaciente(IMedico m, Paciente p) throws NoExistePacienteException {
        assert m != null : "El medico no puede ser nulo.";
        assert p != null : "El paciente no puede ser nulo.";
        
        int consultasPre = consultaMedicas.size();
        Paciente pacienteBuscado = null;
        
        // si nunca fue atendido
        if (salaEsperaPrivada.getPaciente() == p || patio.contains(p)) {
            pacienteBuscado = p;
            // si esta en la sala, la vacio
            if (salaEsperaPrivada.getPaciente() == p) {
                salaEsperaPrivada.setPaciente(null);
            } else {
                patio.remove(p);
            }
            pacientesEnAtencion.add(p);
            // si ya fue atendido antes
        } else if (pacientesEnAtencion.contains(p)) {
            pacienteBuscado = p;
        }

        if (pacienteBuscado == null)
            throw new NoExistePacienteException("No esta registrado el paciente");

        assert pacienteBuscado != null : "El paciente debe existir en alguna de las listas para ser atendido.";

        consultaMedicas.add(new ConsultaMedica(pacienteBuscado, m, null));

        assert consultaMedicas.size() == consultasPre + 1 : "Una consulta medica debe haber sido agregada.";
        assert pacientesEnAtencion.contains(p) : "El paciente debe estar en 'pacientesEnAtencion' despues de la atencion.";
    }
    
     /**
     * Registra un medico en la clinica
     * @param m medico a registrar
     * <br>Pre: m no debe ser null
     * <br>Post: el medico queda agregado a la lista de medicos
     */
    public void registrarMedico(IMedico m) {
        assert m != null : "El medico a registrar no puede ser nulo.";
        
        medicos.add(m);
        
        assert medicos.contains(m) : "El medico debe haber sido agregado a la lista de medicos.";
    }
    
    /**
     * Interna un paciente en una habitacion
     * @param p paciente a internar
     * @param h habitacion donde internar
     * <br>Pre: p y h no deben ser null
     * <br>Post: se agrega una internacion a la lista de internaciones
     */
    public void internarPaciente(Paciente p, Habitacion h) {
        assert p != null : "El paciente a internar no puede ser nulo.";
        assert h != null : "La habitacion no puede ser nula.";
        
        int internacionesPre = internaciones.size();
        
        internaciones.add(new Internacion(p, h, new Date())); // preguntar
        
        assert internaciones.size() == internacionesPre + 1 : "Una nueva internacion debe haber sido registrada.";
    }
    
    
   
    /**
     * Calcula los honorarios de un medico entre dos fechas a partir de sus consultas
     * @param m medico
     * @param inicio fecha inicio
     * @param fin fecha fin
     * <br>Pre: la lista de consultas no debe ser null
     * <br>Post: retorna los honorarios del medico sumando el sueldo por cada consulta
     */
    public double calcularHonorariosMedico(ArrayList<ConsultaMedica> consultasDelMedico) {
        assert consultasDelMedico != null : "La lista de consultas del medico no puede ser nula.";
        
        double honorarios = 0;
        for (ConsultaMedica consulta : consultasDelMedico) {
            assert consulta.getMedico() != null : "La consulta debe tener un medico asociado para calcular su sueldo.";
            honorarios += consulta.getMedico().calcularSueldo();
        }
        
        assert honorarios >= 0 : "Los honorarios calculados no pueden ser negativos.";
        
        return honorarios;  
    }
    
     /**
     * Obtiene las consultas de un medico entre dos fechas
     * @param m medico
     * @param inicio fecha inicio
     * @param fin fecha fin
     * <br>Pre: m, inicio y fin no deben ser null
     * <br>Post: retorna  las consultas del medico entre esas fechas o un array vacio
     */
    public ArrayList<ConsultaMedica> obtenerConsultasDeUnMedicoPorFecha(IMedico m, Date inicio, Date fin) {
        assert m != null : "El medico no puede ser nulo.";
        assert inicio != null : "La fecha de inicio no puede ser nula.";
        assert fin != null : "La fecha de fin no puede ser nula.";
        assert inicio.compareTo(fin) <= 0 : "La fecha de inicio no puede ser posterior a la fecha de fin.";
        
        ArrayList<ConsultaMedica> consultasDelMedico = new ArrayList<ConsultaMedica>();
        
        for (ConsultaMedica consulta : consultaMedicas) {
            if (consulta.getMedico().equals(m)) {
                assert consulta.getFechaConsulta() != null : "La consulta debe tener una fecha registrada para ser filtrada."; 
                double fechaInicio = consulta.getFechaConsulta().compareTo(inicio);
                double fechaFin = consulta.getFechaConsulta().compareTo(fin);
                if (fechaInicio >= 0 && fechaFin <= 0) {
                    consultasDelMedico.add(consulta);
                }
            }
        }

        assert consultasDelMedico != null : "La lista de consultas retornada no puede ser nula.";
        
        return consultasDelMedico;
    }
    
    /**
     * Egreso un paciente sin internacion
     * @param p paciente a egresar
     * @return factura del paciente
     * <br>Pre: p no debe ser null
     * <br>Post: se crea una factura con las consultas del paciente
     */
    public Factura egresaPaciente(Paciente p) {
        assert p != null : "El paciente a egresar no puede ser nulo.";
        
        ArrayList<ConsultaMedica> consultasDelPaciente = new ArrayList<ConsultaMedica>();
        Date fechaActual = new Date();
        
        assert consultaMedicas.stream().anyMatch(cm -> cm.getPaciente().equals(p) && cm.getFechaConsulta() == null) : "El paciente deberia tener consultas pendientes para egresar sin internacion.";
        
        for (ConsultaMedica cm : consultaMedicas) {
            if (cm.getPaciente() == p && cm.getFechaConsulta() == null) {
                cm.setFechaConsulta(fechaActual);
                consultasDelPaciente.add(cm);
            }
        }

        Factura factura = new Factura(fechaActual, consultasDelPaciente, null, fechaActual, p);

        assert factura != null : "Se debe retornar un objeto Factura no nulo.";
        
        return factura;

    }
    
     /**
     * Egreso un paciente con internacion
     * @param p paciente a egresar
     * @param fecha fecha de egreso
     * @return factura del paciente
     * <br>Pre: p y fecha no deben ser null
     * <br>Post: se crea una factura con internacion y consultas actualizadas
     */
    public Factura egresaPaciente(Paciente p, Date fecha){
        assert p != null : "El paciente a egresar no puede ser nulo.";
        assert fecha != null : "La fecha de egreso no puede ser nula.";
        
        ArrayList<ConsultaMedica> consultasDelPaciente = new ArrayList<ConsultaMedica>();
        Internacion internacion = null;

        Date fechaActual = new Date();
        assert internaciones.stream().anyMatch(i -> i.getPaciente().equals(p)) : "El paciente debe tener una internacion activa para egresar con internacion.";
        
        for (ConsultaMedica cm : consultaMedicas) {
            if (cm.getPaciente() == p && cm.getFechaConsulta() == null) {
                cm.setFechaConsulta(fechaActual);
                consultasDelPaciente.add(cm);
            }
        }
        for (Internacion i : internaciones) {
            if(i.getPaciente() == p){
                internacion = i;
            }
            
        }
        
        assert internacion != null : "Se debe encontrar una internacion activa para el paciente.";
        
        internacion.darDeAlta(fecha);
        Factura factura = new Factura(fechaActual, consultasDelPaciente, internacion, internacion.obtenerFechaConDiasSumados(internacion.getCantidadDiasInternacion()), p);
        
        assert factura != null : "Se debe retornar un objeto Factura no nulo.";
        
        return factura;
    }
    
    /** @return devuelve el nombre de la clinica */
    public String getNombreClinica() {
        assert nombreClinica != null : "El nombre de la clinica no puede ser nulo.";
        return nombreClinica;
    }
    
    /** @return devuelve telefono de la clinica */
    public String getTelefono() {
        assert telefono != null : "El telefono de la clinica no puede ser nulo.";
        return telefono;
    }
    
    /** @param telefono Reasigna el telefono de la clinica */
    public void setTelefono(String telefono) {
        assert telefono != null && !telefono.trim().isEmpty() : "El nuevo telefono no debe ser nulo o vacio.";
        this.telefono = telefono;
    }
    
    /** @return devuelve la direccion de la clinica */
    public String getDireccion() {
        assert direccion != null : "La direccion de la clinica no puede ser nula.";
        return direccion;
    }

}