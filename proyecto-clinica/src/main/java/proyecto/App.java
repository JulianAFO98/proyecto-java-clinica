package proyecto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import proyecto.modelo.Clinica;
import proyecto.modelo.ConsultaMedica;
import proyecto.modelo.Domicilio;
import proyecto.modelo.Medico;
import proyecto.modelo.Excepciones.NoExistePacienteException;
import proyecto.modelo.Factory.IMedicoFactoryConPosgrado;
import proyecto.modelo.Factura.Factura;
import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.Habitacion.HabitacionCompartida;
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

        Habitacion h = new HabitacionCompartida(10000);

        c.registrarMedico(medico);
        c.registrarMedico(medico2);
        c.registrarPaciente(p1);
        c.registrarPaciente(p2);
        c.registrarPaciente(p3);
        try {
            c.ingresarPaciente(p1);
            c.ingresarPaciente(p2);
            c.ingresarPaciente(p3);
            c.atiendePaciente(medico, p1);
            c.atiendePaciente(medico2, p3);
            c.atiendePaciente(medico2, p2);
            c.internarPaciente(p2, h);
            c.atiendePaciente(medico2, p1);
        } catch (NoExistePacienteException e) {
            e.printStackTrace();
        }

        Factura factura1 = c.egresaPaciente(p1);
        Factura factura2 = c.egresaPaciente(p3);
        Factura factura3 = c.egresaPaciente(p2, new Date(125, 10, 15));
        System.out.println(factura1);
        System.out.println(factura2);
        System.out.println(factura3);

        System.out.println("Consultas del medico "+ medico2.getNombre()+" "+medico2.getApellido() + "\n");
        ArrayList<ConsultaMedica> consultasDelMedico = c.obtenerConsultasDeUnMedicoPorFecha(medico2, new Date(120, 0, 1), new Date(125, 11, 31));
        for (ConsultaMedica consulta : consultasDelMedico) {
            System.out.println(consulta);
        }
        System.out.println("Honorarios del medico: " + c.calcularHonorariosMedico(consultasDelMedico));

    }
}
