package SegundaEntrega.persistencia;

import java.util.List;

public interface AsociadoDAO {
    AsociadoDTO createAsociado();
    AsociadoDTO getAsociadobyDNI(int dni);
    List<AsociadoDTO> getAllAsociados();
    void darDeBajaAsociado(int dni);
}
