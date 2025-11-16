package proyecto.modelo.Factory;

import proyecto.modelo.Decorator.DecoratorContratacionPermanente;
import proyecto.modelo.Decorator.DecoratorContratacionResidente;
import proyecto.modelo.interfaces.IMedico;

/**
 * Factory centralizada para envolver instancias de {@link IMedico} con la
 * especialidad y tipo de contratacion solicitada mediante el patron Decorator.
 */
public class IMedicoFactoryConContratacion extends IMedicoFactory {

    /**
     * Devuelve un médico decorado con la especialidad y tipo de contratacion
     * indicada.
     *
     * @param medico       instancia base que se desea especializar.
     * @param especialidad nombre de la especialidad (clinica | cirugia |
     *                     pediatria).
     * @param contratacion tipo de contratacion (permanente | residente).
     * @return medico con la especialidad y contratacion aplicada o {@code null} si
     *         el valor no
     *         coincide.
     */
    public static IMedico crearMedicoConContratacion(IMedico medico, String especialidad, String contratacion) {
        IMedico aux = IMedicoFactory.crearMedico(medico, especialidad);
        IMedico auxCreacion = null;
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
