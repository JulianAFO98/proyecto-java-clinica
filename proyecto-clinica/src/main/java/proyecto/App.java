package proyecto;

import proyecto.modelo.Clinica;
import proyecto.modelo.Domicilio;
import proyecto.modelo.Medico;
import proyecto.modelo.Factory.IMedicoFactoryConPosgrado;
import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Nino;
import proyecto.modelo.paciente.Paciente;

public class App 
{
    public static void main( String[] args )
    {
        Clinica c = new Clinica("HPC", "123456789", "Cordoba 9999");
        Domicilio d = new Domicilio("Independencia", 1000);
        Medico m = new Medico("5000000","Pepe","Alvarez","Mardel","123456789",d,2005,100);
        IMedico medico = IMedicoFactoryConPosgrado.crearMedico(m,"clinica","residente","doctorado");
        
        Domicilio d2 = new Domicilio("Independencia", 1000);
        Medico m2 = new Medico("5000000","Juan","Alvarez","Mardel","123456789",d2,2005,100);
        IMedico medico2 = IMedicoFactoryConPosgrado.crearMedico(m2,"clinica","residente","magister");
        
        Paciente p1 = new Nino("4444444", "Ciro", "Ganduglia", "Mardel","123456789", d,56000);
        Paciente p2 = new Nino("1234567", "Julian", "Ganduglia", "Mardel","123456789", d,51000);
        Paciente p3 = new Nino("4441234", "Leonel", "Ganduglia", "Mardel","123456789", d,52000);

        c.registrarMedico(medico);
        c.registrarMedico(medico2);
        c.registrarPaciente(p1);
        c.registrarPaciente(p2);
        c.registrarPaciente(p3);
        c.mostrarTodosLosMedicos();
        c.mostrarTodosLosPacientes();

    }
}
