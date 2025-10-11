package proyecto.modelo.Factory;

import proyecto.modelo.Decorator.DecoratorMedicoCirujia;
import proyecto.modelo.Decorator.DecoratorMedicoClinico;
import proyecto.modelo.Decorator.DecoratorMedicoPediatra;
import proyecto.modelo.interfaces.IMedico;

/**
 * Factory centralizada para envolver instancias de {@link IMedico} con la
 * especialidad solicitada mediante el patron Decorator.
 */
public class IMedicoFactory {
    /**
     * Devuelve un médico decorado con la especialidad indicada.
     *
     * @param medico       instancia base que se desea especializar.
     * @param especialidad nombre de la especialidad (clinica | cirugia |
     *                     pediatria).
     * @return medico con la especialidad aplicada o {@code null} si el valor no
     *         coincide.
     */
    public static IMedico crearMedico(IMedico medico, String especialidad) {
        IMedico aux = null;
        switch (especialidad.toLowerCase()) {
            case "clinica":
                aux = new DecoratorMedicoClinico(medico);
                break;
            case "cirugia":
                aux = new DecoratorMedicoCirujia(medico);
                break;
            case "pediatria":
                aux = new DecoratorMedicoPediatra(medico);
                break;
            default:
                System.out.println("Tipo incorrecto" + especialidad);
                break;
        }
        return aux;
    }
}
