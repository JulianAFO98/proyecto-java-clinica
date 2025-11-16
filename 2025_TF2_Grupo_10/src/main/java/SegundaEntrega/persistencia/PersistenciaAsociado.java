package SegundaEntrega.persistencia;

import java.util.ArrayList;
import java.util.List;

public class PersistenciaAsociado implements AsociadoDAO {

    private ArrayList<AsociadoDTO> asociados = new ArrayList<>();
    private static int idGenerador = 0;

    /**
     * Agrega un nuevo AsociadoDTO a la lista simulada.
     *
     * @param asociado Objeto DTO a crear.
     * <br>Pre: asociado != null, asociado.getDni() no debe ser nulo o vacio.
     * <br>Post: asociado.getId() se actualiza con un nuevo valor unico. El asociado se agrega a la lista interna.
     * @return El AsociadoDTO con el ID asignado.
     */
    @Override
    public AsociadoDTO createAsociado(AsociadoDTO asociado) {
        // Precondiciones
        assert asociado != null : "El asociado no puede ser nulo.";
        assert asociado.getDni() != null && !asociado.getDni().trim().isEmpty() : "El DNI del asociado no debe ser nulo o vacio.";
        
        int idAnterior = asociado.getId();
        int nuevoId = idGenerador;
        
        asociado.setId(idGenerador++);
        asociados.add(asociado);
        
        // Postcondiciones
        assert asociado.getId() == nuevoId : "El ID no se asigno correctamente.";
        assert asociados.contains(asociado) : "El asociado no se agrego a la lista.";
        
        return asociado;
    }

    /**
     * Busca un AsociadoDTO por su DNI.
     *
     * @param dni Documento del asociado a buscar.
     * <br>Pre: dni != null y no vacio.
     * <br>Post: Retorna un AsociadoDTO cuyo DNI coincide (ignorando mayusculas/minusculas) o null si no se encuentra.
     * @return AsociadoDTO encontrado o null.
     */
    @Override
    public AsociadoDTO getAsociadobyDNI(String dni) {
        // Precondicion
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        
        AsociadoDTO a = null;
        for (AsociadoDTO asociadoDTO : asociados) {
            if (asociadoDTO.getDni().equalsIgnoreCase(dni)) {
                a = asociadoDTO;
                // Invariante de ciclo: Si se encuentra, el DNI coincide
                assert a.getDni().equalsIgnoreCase(dni) : "El DNI encontrado no coincide.";
                break; // Se agrega un break para optimizar la busqueda
            }
        }
        
        // Postcondicion: si no es nulo, el DNI debe coincidir.
        if (a != null) {
            assert a.getDni().equalsIgnoreCase(dni) : "El asociado retornado no tiene el DNI correcto.";
        }
        return a;
    }

    /**
     * Devuelve todos los AsociadoDTO almacenados en la lista.
     *
     * @return Lista de AsociadoDTO.
     * <br>Pre: No aplica.
     * <br>Post: Retorna la lista interna de asociados, no nula.
     */
    @Override
    public List<AsociadoDTO> getAllAsociados() {
        // Postcondicion
        assert asociados != null : "La lista de asociados no puede ser nula.";
        return asociados;
    }

    /**
     * Marca un asociado como dado de baja (alta = false) utilizando su ID.
     *
     * @param id Identificador unico del asociado a dar de baja.
     * <br>Pre: id >= 0
     * <br>Post: Si existe un asociado con ese ID, su atributo 'alta' sera false.
     */
    @Override
    public void darDeBajaAsociado(int id) {
        // Precondicion
        assert id >= 0 : "El ID debe ser no negativo.";
        
        for (AsociadoDTO asociadoDTO : asociados) {
            if (asociadoDTO.getId() == id) {
                asociadoDTO.setAlta(false);
                // Postcondicion de caso exitoso
                assert asociadoDTO.isAlta() == false : "El asociado no se marco como dado de baja.";
                return; // Se agrega un return para optimizar
            }
        }
    }

}