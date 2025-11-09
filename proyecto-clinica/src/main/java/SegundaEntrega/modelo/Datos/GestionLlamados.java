package SegundaEntrega.modelo.Datos;

import java.util.ArrayList;
import java.util.List;

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
    public GestionLlamados(Ambulancia a) {
        this.ambulancia = a;
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
        this.ambulancia.setSimulacionActiva(false);
    }

    public void empezarSimulacion() {
        ArrayList<Asociado> asociados = (ArrayList<Asociado>) getAsociados();
        this.ambulancia.setSimulacionActiva(true);
        for (Asociado asociado : asociados) {
            asociado.setAmbulancia(ambulancia);
            Thread hiloAsociado = new Thread(asociado);
            hiloAsociado.start();
        }
    }
}
