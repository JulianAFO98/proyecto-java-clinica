package SegundaEntrega.utils;

import java.util.ArrayList;
import java.util.List;

import SegundaEntrega.modelo.Datos.Asociado;
import SegundaEntrega.persistencia.AsociadoDTO;

/**
 * Funciones utilitarias para conversiones entre capas.
 */
public class Utils {

    /**
     * Convierte dtos de asociados en objetos de dominio basicos.
     *
     * @param lista coleccion de dtos provenientes de persistencia
     * <br>Pre: lista != null. Todos los DTOs en la lista son validos (campos nombre, dni, id no nulos/invalidos).
     * <br>Post: Retorna una lista no nula de objetos Asociado con los datos transferidos. El tamaño de la lista de salida es igual al de la lista de entrada.
     * @return lista de entidades Asociado con los datos del dto
     */
    public static List<Asociado> fromDTOtoClass(List<AsociadoDTO> lista){
        // Precondicion
        assert lista != null : "La lista de DTOs no puede ser nula.";
        
        List<Asociado> asociados = new ArrayList<>();
        int tamanoInicial = lista.size();
        
        for (AsociadoDTO asociadoDTO : lista) {
            // Invariante de ciclo: Verificar validez de los datos criticos del DTO
            assert asociadoDTO != null : "Un DTO en la lista no debe ser nulo.";
            assert asociadoDTO.getDni() != null && !asociadoDTO.getDni().trim().isEmpty() : "El DNI del DTO no debe ser nulo o vacio.";
            assert asociadoDTO.getNombre() != null && !asociadoDTO.getNombre().trim().isEmpty() : "El nombre del DTO no debe ser nulo o vacio.";
            assert asociadoDTO.getId() >= 0 : "El ID del DTO debe ser no negativo.";


            String dni = asociadoDTO.getDni();
            String nombre = asociadoDTO.getNombre();
            int id = asociadoDTO.getId();
            boolean alta = asociadoDTO.isAlta();
            
            // Nota: Se pasa 'null' como Ambulancia, ya que esta capa solo realiza la conversion de datos.
            Asociado nuevoAsociado = new Asociado(nombre, id, dni, alta, null);
            asociados.add(nuevoAsociado);
        }

        // Postcondiciones
        assert asociados != null : "La lista de Asociados no debe ser nula.";
        assert asociados.size() == tamanoInicial : "La lista de salida debe tener el mismo tamano que la lista de entrada.";
        
        return asociados;
    }

}