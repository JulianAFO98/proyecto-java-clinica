package SegundaEntrega.persistencia;

import java.util.ArrayList;
import java.util.List;

public class PersistenciaAsociado implements AsociadoDAO{

    private ArrayList<AsociadoDTO> asociados = new ArrayList<>();
    private static int idGenerador = 0;


    @Override
    public AsociadoDTO createAsociado(AsociadoDTO asociado) {
        asociado.setId(idGenerador++);
        asociados.add(asociado);
        return asociado;
    }

    @Override
    public AsociadoDTO getAsociadobyDNI(String dni) {
        AsociadoDTO a = null;
        for (AsociadoDTO asociadoDTO : asociados) {
            if(asociadoDTO.getDni().equalsIgnoreCase(dni)){
                a = asociadoDTO;
            }
        }
        return a;
    }

    @Override
    public List<AsociadoDTO> getAllAsociados() {
        return asociados;
    }

    @Override
    public void darDeBajaAsociado(String dni) {
        for (AsociadoDTO asociadoDTO : asociados) {
             if(asociadoDTO.getDni().equalsIgnoreCase(dni)){
                asociadoDTO.setAlta(false);;
            }
        }
    }


    

}
