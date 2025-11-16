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
     * Devuelve un medico decorado con la especialidad y tipo de contratacion
     * indicada.
     *
     * @param medico instancia base que se desea especializar.
     * @param especialidad nombre de la especialidad (clinica | cirugia |
     * pediatria).
     * @param contratacion tipo de contratacion (permanente | residente).
     * @return medico con la especialidad y contratacion aplicada o {@code null} si
     * el valor no
     * coincide.
     */
    public static IMedico crearMedicoConContratacion(IMedico medico, String especialidad, String contratacion) {
        // Precondiciones
        assert medico != null : "El medico base no puede ser nulo.";
        assert especialidad != null && !especialidad.trim().isEmpty() : "La especialidad no puede ser nula o vacia.";
        assert contratacion != null && !contratacion.trim().isEmpty() : "La contratacion no puede ser nula o vacia.";
        
        IMedico aux = IMedicoFactory.crearMedico(medico, especialidad);
        IMedico auxCreacion = null;
        
        String contratacionNormalizada = contratacion.toLowerCase();
        String especialidadPre = aux != null ? aux.getEspecialidad() : null;

        switch (contratacionNormalizada) {
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

        // Postcondiciones
        if (auxCreacion != null) {
            // Debe mantener la especialidad obtenida en el primer paso (si aux no era nulo).
            assert auxCreacion.getEspecialidad().equals(especialidadPre) : "La especialidad fue alterada por el decorador de contratacion.";
            
            // Debe ser del tipo de contratacion solicitado.
            if (contratacionNormalizada.equals("permanente")) {
                 assert auxCreacion instanceof DecoratorContratacionPermanente : "El medico deberia ser DecoratorContratacionPermanente.";
            } else if (contratacionNormalizada.equals("residente")) {
                 assert auxCreacion instanceof DecoratorContratacionResidente : "El medico deberia ser DecoratorContratacionResidente.";
            }
        } else {
             // Si auxCreacion es nulo, la unica razon (asumiendo que aux != null y no hubo AssertionError) es la contratacion invalida.
             if (aux != null) {
                 assert !contratacionNormalizada.equals("permanente") && !contratacionNormalizada.equals("residente") : "Error: auxCreacion es nulo a pesar de que el medico y la contratacion eran validos.";
             }
        }
        
        return auxCreacion;
    }
}