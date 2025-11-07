package SegundaEntrega.Patrones.PatronState;

import java.util.List;

import SegundaEntrega.modelo.Datos.Ambulancia;
import SegundaEntrega.modelo.Datos.Asociado;
import SegundaEntrega.modelo.Datos.Operario;
import SegundaEntrega.persistencia.AsociadoDAO;
import SegundaEntrega.persistencia.AsociadoDTO;
import SegundaEntrega.persistencia.AsociadoExistenteException;
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

    public void agregarAsociado(String nombre, String dni) throws AsociadoExistenteException {

        AsociadoDTO asociado = asociadoDao.getAsociadobyDNI(dni);
        if (asociado != null) {
            throw new AsociadoExistenteException("El dni ya existe", dni);
        }
        AsociadoDTO a = asociadoDao.createAsociado(new AsociadoDTO(dni, true, nombre));

    }

    public List<Asociado> getAsociados() {
        List<AsociadoDTO> asociadosDTO = asociadoDao.getAllAsociados();
        List<Asociado> asociados = Utils.fromDTOtoClass(asociadosDTO);
        return asociados;
    }

    public void eliminarAsociado(Asociado asociadoSeleccionado) {
        asociadoDao.darDeBajaAsociado(asociadoSeleccionado.getId());
    }

    public void llamarOperario() {
        //code
    }

    public void finalizarSimulacion() {
        //code
    }

    public void empezarSimulacion() {
        //code
    }
}
