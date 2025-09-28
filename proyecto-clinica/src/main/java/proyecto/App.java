package proyecto;

import proyecto.modelo.Domicilio;
import proyecto.modelo.Medico;
import proyecto.modelo.Factory.IMedicoFactoryConPosgrado;
import proyecto.modelo.interfaces.IMedico;

public class App 
{
    public static void main( String[] args )
    {
        Domicilio d = new Domicilio("Independencia", 1000);
        Medico m = new Medico("5000000","Pepe","Alvarez","Mardel","123456789",d,2005,100);
        IMedico medico = IMedicoFactoryConPosgrado.crearMedico(m,"clinica","residente","doctorado");
        System.out.println(medico);

        Domicilio d2 = new Domicilio("Independencia", 1000);
        Medico m2 = new Medico("5000000","Juan","Alvarez","Mardel","123456789",d2,2005,100);
        IMedico medico2 = IMedicoFactoryConPosgrado.crearMedico(m2,"clinica","residente","magister");
        System.out.println(medico2);
    }
}
