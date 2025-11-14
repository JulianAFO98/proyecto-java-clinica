package SegundaEntrega.persistencia;

import java.util.List;

public interface AsociadoDAO {
    AsociadoDTO getAsociadobyDNI(String dni);
    List<AsociadoDTO> getAllAsociados();
    void darDeBajaAsociado(int id);
    AsociadoDTO createAsociado(AsociadoDTO asociado);
    
}
