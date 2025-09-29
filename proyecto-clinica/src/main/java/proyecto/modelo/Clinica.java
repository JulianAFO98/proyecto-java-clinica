package proyecto.modelo;

import proyecto.*;
import java.util.ArrayList;
import java.util.Date;
import proyecto.modelo.Excepciones.NoExistePacienteException;
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
    //Agregarlo a la base de datos
    public void registrarPaciente(Paciente p){
        pacientesRegistrados.add(p);
    }

    public void ingresarPaciente(Paciente p) throws NoExistePacienteException{
        //Verificar si el paciente existe en nuestra "db"
        boolean existePaciente = pacientesRegistrados.contains(p);
       
        //si no existe tiramos excepcion
        if(!existePaciente){
            throw new NoExistePacienteException("No esta registrado el paciente");
        }
        p.setNumeroOrdenPropio();
        if(salaEsperaPrivada.getPaciente() == null){
           salaEsperaPrivada.setPaciente(p); 
        }else {
            Paciente pacienteActual = salaEsperaPrivada.getPaciente();
            Paciente pacienteGanador =pacienteActual.decidirSala(p);
            //perdedor al patio
            if(pacienteActual.equals(pacienteGanador)){
               patio.add(p);
            }else {
                patio.add(pacienteActual);
                //ganador a la sala
                salaEsperaPrivada.setPaciente(pacienteGanador);
            }
        }
    }
    
    public void atiendePaciente(IMedico m,Paciente p){
        //buscamos el menor numero de entre la salaPrivada o el patio 
        

        //lo extremos de alli


        //generamos la consulta

        //lo agregamos a atendidos
    }

    public void registrarMedico(IMedico m){
        medicos.add(m);
    }

    public void internarPaciente(Paciente p,Habitacion h){
        internaciones.add(new Internacion(p,h,new Date())); // preguntar
    }

    public void mostrarTodosLosMedicos(){
        for (IMedico m : medicos) {
            System.out.println(m.toString());
        }
    }

    public void mostrarTodosLosPacientes(){
        for (Paciente p : pacientesRegistrados) {
            System.out.println(p.toString());
        }
    }

    /*public void mostrarConsultasMedico(IMedico m,Date fecha){
        for (ConsultaMedica cm : consultaMedicas) {

        }
    }
    */

    public void egresaPaciente(Paciente p){

    }

    public void egresaPaciente(Paciente p,Date fecha){

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
