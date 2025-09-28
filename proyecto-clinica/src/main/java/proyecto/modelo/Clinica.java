package proyecto.modelo;

import java.util.ArrayList;
import java.util.Date;

import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Paciente;

public class Clinica {
    private String nombreClinica;
    private String telefono;
    private String direccion;
    private SalaEsperaPrivada salaEsperaPrivada;
    private ArrayList<Paciente> patio = new ArrayList<Paciente>();
    private ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
    private ArrayList<ConsultaMedica> consultaMedicas = new ArrayList<ConsultaMedica>();
    private ArrayList<Paciente> pacientesAtendidos = new ArrayList<Paciente>();
    private ArrayList<IMedico> medicos = new ArrayList<IMedico>();
    private ArrayList<Internacion> internaciones = new ArrayList<Internacion>();

    public Clinica(String nombreClinica, String telefono, String direccion, SalaEsperaPrivada salaEsperaPrivada) {
        this.nombreClinica = nombreClinica;
        this.telefono = telefono;
        this.direccion = direccion;
        this.salaEsperaPrivada = salaEsperaPrivada;
    }


    public void ingresarPaciente(Paciente p){
        //if(salaEsperaPrivada.getPaciente() == null){
           // salaEsperaPrivada.setPaciente(p); // metodo ingresar
        //}else {
            //calcular paciente ganador

            //ganador a la sala

            //perdedor al patio
        //}
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

    public void internarPaciente(Paciente p){
        //generar nueva internacion

        //agregar internacion al a las internaciones
    }

    public void mostrarTodosLosMedicos(){
        for (IMedico m : medicos) {
            System.out.println(m.toString());
        }
    }

    public void mostrarConsultasMedico(IMedico m,Date fecha){
        for (ConsultaMedica cm : consultaMedicas) {

        }
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
