package SegundaEntrega.persistencia;

import java.util.List;

/**
 * Contrato de acceso a datos para entidades Asociado.
 */
public interface AsociadoDAO {
    /**
     * Busca un asociado mediante su dni.
     *
     * @param dni documento a consultar
     * @return dto encontrado o null
     */
    AsociadoDTO getAsociadobyDNI(String dni);

    /**
     * Devuelve la lista completa de asociados registrados.
     *
     * @return coleccion de dtos
     */
    List<AsociadoDTO> getAllAsociados();

    /**
     * Elimina un asociado por identificador primario.
     *
     * @param id clave del registro a borrar
     */
    void darDeBajaAsociado(int id);

    /**
     * Inserta un nuevo asociado y devuelve el dto utilizado.
     *
     * @param asociado datos a persistir
     * @return dto insertado o null si fallo
     */
    AsociadoDTO createAsociado(AsociadoDTO asociado);
    
}
