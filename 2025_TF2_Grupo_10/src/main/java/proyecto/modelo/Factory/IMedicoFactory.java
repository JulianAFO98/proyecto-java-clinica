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
     * Devuelve un medico decorado con la especialidad indicada.
     *
     * @param medico instancia base que se desea especializar.
     * @param especialidad nombre de la especialidad (clinica | cirugia |
     * pediatria).
     * @return medico con la especialidad aplicada o {@code null} si el valor no
     * coincide.
     */
    public static IMedico crearMedico(IMedico medico, String especialidad) {
        // Precondiciones
        assert medico != null : "El medico base no puede ser nulo.";
        assert especialidad != null && !especialidad.trim().isEmpty() : "La especialidad no puede ser nula o vacia.";

        IMedico aux = null;
        String especialidadNormalizada = especialidad.toLowerCase();
        
        switch (especialidadNormalizada) {
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
        
        // Postcondiciones
        if (aux != null) {
            // Si aux no es nulo, debe tener la especialidad solicitada
            assert aux.getEspecialidad().toLowerCase().equals(especialidadNormalizada) : "El medico no se creo con la especialidad correcta.";
        } else {
             // Si aux es nulo, la especialidad debe haber sido incorrecta.
             assert (!especialidadNormalizada.equals("clinica") &&
                     !especialidadNormalizada.equals("cirugia") &&
                     !especialidadNormalizada.equals("pediatria")) : "Error al crear medico: la especialidad es valida pero aux es nulo.";
        }
        
        return aux;
    }
}