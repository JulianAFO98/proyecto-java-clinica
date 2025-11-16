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
     * @param medico instancia base que se desea especializar.
     * @param especialidad nombre de la especialidad (clinica | cirugia |
     * pediatria).
     * @param contratacion tipo de contratacion (permanente | residente).
     * @param posgrado tipo de posgrado (magister | doctorado).
     * @return medico con la especialidad, contratacion y posgrado aplicada o
     * {@code null} si el valor no
     * coincide.
     */
    public static IMedico crearMedico(IMedico medico, String especialidad, String contratacion, String posgrado) {
        // Precondiciones
        assert medico != null : "El medico base no puede ser nulo.";
        assert especialidad != null && !especialidad.trim().isEmpty() : "La especialidad no puede ser nula o vacia.";
        assert contratacion != null && !contratacion.trim().isEmpty() : "La contratacion no puede ser nula o vacia.";
        assert posgrado != null && !posgrado.trim().isEmpty() : "El posgrado no puede ser nulo o vacio.";
        
        IMedico aux = IMedicoFactoryConContratacion.crearMedicoConContratacion(medico, especialidad, contratacion);
        IMedico auxCreacion = null;
        

        if (aux != null) {
            String posgradoNormalizado = posgrado.toLowerCase();
            String especialidadPre = aux.getEspecialidad(); // Invariante: debe mantener la especialidad

            switch (posgradoNormalizado) {
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

            // Postcondiciones
            if (auxCreacion != null) {
                boolean posgradoValido = posgradoNormalizado.equals("magister") || posgradoNormalizado.equals("doctorado");
                assert posgradoValido : "Si se creo el medico, el posgrado debe ser valido.";

                // La especialidad no debe haber cambiado
                assert auxCreacion.getEspecialidad().equals(especialidadPre) : "El decorador de posgrado no debe modificar la especialidad base.";

                // Debe ser del tipo de posgrado solicitado.
                if (posgradoNormalizado.equals("magister")) {
                     assert auxCreacion instanceof DecoratorPosgradoMagister : "El medico deberia ser DecoratorPosgradoMagister.";
                } else if (posgradoNormalizado.equals("doctorado")) {
                     assert auxCreacion instanceof DecoratorPosgradoDoctorado : "El medico deberia ser DecoratorPosgradoDoctorado.";
                }
            } else {
                 boolean posgradoValido = posgradoNormalizado.equals("magister") || posgradoNormalizado.equals("doctorado");
                 assert !posgradoValido : "auxCreacion es nulo a pesar de que el posgrado era valido.";
            }
        }

        return auxCreacion;
    }
}