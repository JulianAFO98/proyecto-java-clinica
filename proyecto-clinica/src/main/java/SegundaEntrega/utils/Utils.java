package SegundaEntrega.utils;

import java.util.ArrayList;
import java.util.List;

import SegundaEntrega.modelo.Datos.Asociado;
import SegundaEntrega.persistencia.AsociadoDTO;

public class Utils {

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
