package SegundaEntrega.modelo.Datos;

import java.util.List;

import SegundaEntrega.persistencia.AsociadoDAO;
import SegundaEntrega.persistencia.AsociadoDTO;
import SegundaEntrega.persistencia.PersistenciaAsociado;
import SegundaEntrega.utils.Utils;

/*
 * Clase facade actual
 */
public class GestionLlamados {
    private Ambulancia ambulancia;
    private Operario ope;
    private AsociadoDAO asociadoDao;

    public GestionLlamados() {
        this.ambulancia = new Ambulancia();
        this.ope = new Operario();
        this.asociadoDao = new PersistenciaAsociado();
    }

    public void agregarAsociado(String nombre, String dni) {
        try {
            AsociadoDTO a = asociadoDao.createAsociado(new AsociadoDTO(dni, true, nombre));
        } catch (Exception e) {
        }
    }

    public List<Asociado> getAsociados() {
        List<AsociadoDTO> asociadosDTO = asociadoDao.getAllAsociados();
        List<Asociado> asociados = Utils.fromDTOtoClass(asociadosDTO);
        return asociados;
    }
}
