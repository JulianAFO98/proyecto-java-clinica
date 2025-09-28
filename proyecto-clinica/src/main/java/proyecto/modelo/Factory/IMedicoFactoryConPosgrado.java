package proyecto.modelo.Factory;
import proyecto.modelo.Decorator.DecoratorPosgradoDoctorado;
import proyecto.modelo.Decorator.DecoratorPosgradoMagister;
import proyecto.modelo.interfaces.IMedico;

public class IMedicoFactoryConPosgrado extends IMedicoFactoryConContratacion {

    public static IMedico crearMedico(IMedico m,String especialidad,String contratacion,String posgrado) {
        IMedico aux = IMedicoFactoryConContratacion.crearMedicoConContratacion(m,especialidad,contratacion);
        IMedico auxCreacion=null;
        switch (posgrado.toLowerCase()) {
            case "magister":
                auxCreacion = new DecoratorPosgradoMagister(aux);
                break;
            case "doctorado":
                auxCreacion = new DecoratorPosgradoDoctorado(aux);
                break;
            default:
                System.out.println("Posgrado incorrecto" + posgrado);
                break;
        }
        return auxCreacion;
    }
}