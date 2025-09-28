package proyecto.modelo.Factory;
import proyecto.modelo.Decorator.DecoratorContratacionPermanente;
import proyecto.modelo.Decorator.DecoratorContratacionResidente;
import proyecto.modelo.interfaces.IMedico;

public class IMedicoFactoryConContratacion extends IMedicoFactory {

    public static IMedico crearMedicoConContratacion(IMedico m,String especialidad,String contratacion) {
        IMedico aux = IMedicoFactory.crearMedico(m,especialidad);
        IMedico auxCreacion=null;
        switch (contratacion.toLowerCase()) {
            case "permanente":
                auxCreacion = new DecoratorContratacionPermanente(aux);
                break;
            case "residente":
                auxCreacion = new DecoratorContratacionResidente(aux);
                break;
            default:
                System.out.println("Contratacion incorrecta" + contratacion);
                break;
        }
        return auxCreacion;
    }
}
