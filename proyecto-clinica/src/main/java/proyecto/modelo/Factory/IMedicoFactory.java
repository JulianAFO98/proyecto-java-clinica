package proyecto.modelo.Factory;
import proyecto.modelo.Decorator.DecoratorMedicoCirujia;
import proyecto.modelo.Decorator.DecoratorMedicoClinico;
import proyecto.modelo.Decorator.DecoratorMedicoPediatra;
import proyecto.modelo.interfaces.IMedico;

public class IMedicoFactory {

    

    public static IMedico crearMedico(IMedico medico,String especialidad) {
        IMedico aux = null;
        switch (especialidad.toLowerCase()) {
            case "clinica":
                aux= new DecoratorMedicoClinico(medico);
                break;
            case "cirugia":
                aux= new DecoratorMedicoCirujia(medico);
                break;
            case "pediatria":
                aux= new DecoratorMedicoPediatra(medico);
                break;
            default:
                System.out.println("Tipo incorrecto" + especialidad);
                break;
        }
        return aux;
    }
}
