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
     * @return lista de entidades Asociado con los datos del dto
     */
    public static List<Asociado> fromDTOtoClass(List<AsociadoDTO> lista){
        List<Asociado> asociados = new ArrayList<>();
        
        for (AsociadoDTO asociadoDTO : lista) {
            String dni = asociadoDTO.getDni();
            String nombre = asociadoDTO.getNombre();
            int id = asociadoDTO.getId();
            boolean alta = asociadoDTO.isAlta();
            asociados.add(new Asociado(nombre, id, dni, alta, null));
        }

        return asociados;
    }

}
