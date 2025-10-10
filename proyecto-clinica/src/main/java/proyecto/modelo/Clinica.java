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
     * Precondiciones: los parametros no deben ser null
     * Postcondiciones: se crea la clinica con listas vacias y sala de espera vacia
     */
    public Clinica(String nombreClinica, String telefono, String direccion) {
        this.nombreClinica = nombreClinica;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /**
     * Registra un paciente la clininca
     * @param p paciente a registrar
     * Precondiciones: p no debe ser null
     * Postcondiciones: el paciente queda agregado a pacientesRegistrados
     */
    public void registrarPaciente(Paciente p) {
        pacientesRegistrados.add(p);
    }

     /**
     * Ingresa un paciente a la clinica
     * @param p paciente a ingresar
     * @throws NoExistePacienteException si el paciente no esta registrado
     * Precondiciones: p no debe ser null
     * Postcondiciones: el paciente queda en sala de espera o en el patio
     */
    public void ingresarPaciente(Paciente p) throws NoExistePacienteException {
        boolean existePaciente = pacientesRegistrados.contains(p);

        // si no existe tiramos excepcion
        if (!existePaciente) {
            throw new NoExistePacienteException("No esta registrado el paciente");
        }
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
    }
    /**
     * Atiende un paciente con un medico
     * @param m medico que atiende
     * @param p paciente a atender
     * @throws NoExistePacienteException si el paciente no esta en sala ni patio ni en atencion
     * Precondiciones: 
     * m y p no deben ser null
     * Postcondiciones: 
     * se agrega una consulta medica del paciente con el medico con fechas en null
     */
    public void atiendePaciente(IMedico m, Paciente p) throws NoExistePacienteException {
        // buscamos el menor numero de entre la salaPrivada o el patio
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

        consultaMedicas.add(new ConsultaMedica(pacienteBuscado, m, null));

    }
     /**
     * Registra un medico en la clinica
     * @param m medico a registrar
     * Precondiciones: m no debe ser null
     * Postcondiciones: el medico queda agregado a la lista de medicos
     */
    public void registrarMedico(IMedico m) {
        medicos.add(m);
    }
    /**
     * Interna un paciente en una habitacion
     * @param p paciente a internar
     * @param h habitacion donde internar
     * Precondiciones: p y h no deben ser null
     * Postcondiciones: se agrega una internacion a la lista de internaciones
     */
    public void internarPaciente(Paciente p, Habitacion h) {
        internaciones.add(new Internacion(p, h, new Date())); // preguntar
    }
    
    
   
    /**
     * Calcula los honorarios de un medico entre dos fechas a partir de sus consultas
     * @param m medico
     * @param inicio fecha inicio
     * @param fin fecha fin
     * Precondiciones: la lista de consultas no debe ser null
     * Postcondiciones: retorna los honorarios del medico sumando el sueldo por cada consulta
     */
    public double calcularHonorariosMedico(ArrayList<ConsultaMedica> consultasDelMedico) {
        double honorarios = 0;
        for (ConsultaMedica consulta : consultasDelMedico) {
            honorarios += consulta.getMedico().calcularSueldo();
        }
        return honorarios;  
    }
     /**
     * Obtiene las consultas de un medico entre dos fechas
     * @param m medico
     * @param inicio fecha inicio
     * @param fin fecha fin
     * Precondiciones: m, inicio y fin no deben ser null
     * Postcondiciones: retorna  las consultas del medico entre esas fechas o un array vacio
     */
    public ArrayList<ConsultaMedica> obtenerConsultasDeUnMedicoPorFecha(IMedico m, Date inicio, Date fin) {
        ArrayList<ConsultaMedica> consultasDelMedico = new ArrayList<ConsultaMedica>();
        
        for (ConsultaMedica consulta : consultaMedicas) {
            if (consulta.getMedico().equals(m)) {
                double fechaInicio = consulta.getFechaConsulta().compareTo(inicio);
                double fechaFin = consulta.getFechaConsulta().compareTo(fin);
                if (fechaInicio >= 0 && fechaFin <= 0) {
                    consultasDelMedico.add(consulta);
                }
            }
        }

        return consultasDelMedico;
    }
    /**
     * Egreso un paciente sin internacion
     * @param p paciente a egresar
     * @return factura del paciente
     * Precondiciones: p no debe ser null
     * Postcondiciones: se crea una factura con las consultas del paciente
     */
    public Factura egresaPaciente(Paciente p) {
        ArrayList<ConsultaMedica> consultasDelPaciente = new ArrayList<ConsultaMedica>();
        Date fechaActual = new Date();
        for (ConsultaMedica cm : consultaMedicas) {
            if (cm.getPaciente() == p && cm.getFechaConsulta() == null) {
                cm.setFechaConsulta(fechaActual);
                consultasDelPaciente.add(cm);
            }
        }

        return new Factura(fechaActual, consultasDelPaciente, null, fechaActual, p);

    }
     /**
     * Egreso un paciente con internacion
     * @param p paciente a egresar
     * @param fecha fecha de egreso
     * @return factura del paciente
     * Precondiciones: p y fecha no deben ser null
     * Postcondiciones: se crea una factura con internacion y consultas actualizadas
     */
    public Factura egresaPaciente(Paciente p, Date fecha){
        ArrayList<ConsultaMedica> consultasDelPaciente = new ArrayList<ConsultaMedica>();
        Internacion internacion = null;

        Date fechaActual = new Date();
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
        internacion.darDeAlta(fecha);
        return new Factura(fechaActual, consultasDelPaciente, internacion, internacion.obtenerFechaConDiasSumados(internacion.getCantidadDiasInternacion()), p);
    }
    /** @return devuelve el nombre de la clinica */
    public String getNombreClinica() {
        return nombreClinica;
    }
    /** @return devuelve telefono de la clinica */
    public String getTelefono() {
        return telefono;
    }
    /** @param telefono Reasigna el telefono de la clinica */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    /** @return devuelve la direccion de la clinica */
    public String getDireccion() {
        return direccion;
    }

}
