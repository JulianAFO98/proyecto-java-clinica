package proyecto.modelo.Factory;

import proyecto.modelo.Decorator.DecoratorPosgradoDoctorado;
import proyecto.modelo.Decorator.DecoratorPosgradoMagister;
import proyecto.modelo.interfaces.IMedico;

/**
 * Factory centralizada para envolver instancias de {@link IMedico} con la
 * especialidad, tipo de contratacion y posgrado solicitada mediante el patron
 * Decorator.
 */
public class IMedicoFactoryConPosgrado extends IMedicoFactoryConContratacion {

    /**
     * Devuelve un medico decorado con la especialidad, tipo de contratacion y
     * posgrado indicada.
     *
     * @param medico       instancia base que se desea especializar.
     * @param especialidad nombre de la especialidad (clinica | cirugia |
     *                     pediatria).
     * @param contratacion tipo de contratacion (permanente | residente).
     * @param posgrado     tipo de posgrado (magister | doctorado).
     * @return medico con la especialidad, contratacion y posgrado aplicada o
     *         {@code null} si el valor no
     *         coincide.
     */
    public static IMedico crearMedico(IMedico medico, String especialidad, String contratacion, String posgrado) {
        IMedico aux = IMedicoFactoryConContratacion.crearMedicoConContratacion(medico, especialidad, contratacion);
        IMedico auxCreacion = null;
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