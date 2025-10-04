package proyecto.modelo;

import java.util.ArrayList;
import java.util.Date;
import proyecto.modelo.Excepciones.NoExistePacienteException;
import proyecto.modelo.Factura.Factura;
import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Paciente;

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

    public Clinica(String nombreClinica, String telefono, String direccion) {
        this.nombreClinica = nombreClinica;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    // Agregarlo a la base de datos
    public void registrarPaciente(Paciente p) {
        pacientesRegistrados.add(p);
    }

    public void ingresarPaciente(Paciente p) throws NoExistePacienteException {
        // Verificar si el paciente existe en nuestra "db"
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

    public void registrarMedico(IMedico m) {
        medicos.add(m);
    }

    public void internarPaciente(Paciente p, Habitacion h) {
        internaciones.add(new Internacion(p, h, new Date())); // preguntar
    }

    public void mostrarTodosLosMedicos() {
        for (IMedico m : medicos) {
            System.out.println(m.toString());
        }
    }
    public void mostrarInternaciones() {
        for (Internacion i : internaciones) {
            System.out.println(i.toString());
        }
    }

    public void mostrarTodosLosPacientes() {
        for (Paciente p : pacientesRegistrados) {
            System.out.println(p.toString());
        }
    }

   public void calcularHonorariosMedico(IMedico m, Date inicio, Date fin) {

        double honorarios = 0;
        double sueldo = m.calcularSueldo();
        System.out.println("Honorarios del medico:"+m.getNombre()+" "+m.getApellido());
        for (ConsultaMedica consulta : consultaMedicas) {
            if (consulta.getMedico().equals(m)) {
                double fechaInicio = consulta.getFechaConsulta().compareTo(inicio);
                double fechaFin = consulta.getFechaConsulta().compareTo(fin);
                if (fechaInicio >= 0 && fechaFin <= 0) {
                    System.out.println(consulta.getPaciente());
                    honorarios += sueldo;
                }
            }
        }

        System.out.println("Honorarios:"+honorarios);
    }

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
            if(i.getPaciente() == p)
                internacion=i;
        }

        System.out.println(internacion.getPaciente().getNombre());



        return new Factura(fechaActual, consultasDelPaciente, internacion, fechaActual, p);
    }

    public String getNombreClinica() {
        return nombreClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

}
