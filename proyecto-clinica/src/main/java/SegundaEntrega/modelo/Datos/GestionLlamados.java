package SegundaEntrega.modelo.Datos;

import java.util.ArrayList;
import java.util.List;

import SegundaEntrega.persistencia.AsociadoDAO;
import SegundaEntrega.persistencia.AsociadoDTO;
import SegundaEntrega.persistencia.AsociadoExistenteException;
import SegundaEntrega.persistencia.PersistenciaDatabase;
import SegundaEntrega.utils.Utils;

/**
 * Actua como fachada entre la vista, la ambulancia y la capa de persistencia.
 */
public class GestionLlamados {
    private Ambulancia ambulancia;
    private Operario ope;
    private AsociadoDAO asociadoDao;

    /**
     * Inicializa la fachada con una ambulancia y crea los colaboradores necesarios.
     *
     * @param a ambulancia compartida entre asociados y operario
     */
    public GestionLlamados(Ambulancia a) {
        this.ambulancia = a;
        this.ope = new Operario(a);
        this.asociadoDao = new PersistenciaDatabase();
    }

    /**
     * Registra un nuevo asociado validando la inexistencia del dni.
     *
     * @param nombre nombre del asociado a crear
     * @param dni documento unico
     * @throws AsociadoExistenteException cuando ya existe un registro con el mismo dni
     */
    public void agregarAsociado(String nombre, String dni) throws AsociadoExistenteException {

        AsociadoDTO asociado = asociadoDao.getAsociadobyDNI(dni);
        if (asociado != null) {
            throw new AsociadoExistenteException("El dni ya existe", dni);
        }
        AsociadoDTO a = asociadoDao.createAsociado(new AsociadoDTO(dni, true, nombre));

    }

    /**
     * Obtiene los asociados dados de alta desde la base de datos.
     *
     * @return lista de asociados activos
     */
    public List<Asociado> getAsociados() {
        List<AsociadoDTO> asociadosDTO = asociadoDao.getAllAsociados();
        List<Asociado> asociados = Utils.fromDTOtoClass(asociadosDTO);
        List<Asociado> asociadoDeAlta = new ArrayList<>();
         for (Asociado asociado : asociados) {
            if (asociado.isAlta()) {
                asociadoDeAlta.add(asociado);
            }
        }
        return asociadoDeAlta;
    }

    /**
     * Marca un asociado como dado de baja en la base de datos.
     *
     * @param asociadoSeleccionado asociado elegido en la vista
     */
    public void eliminarAsociado(Asociado asociadoSeleccionado) {
        System.out.println("Eliminando asociado: " + asociadoSeleccionado.getId());
        asociadoDao.darDeBajaAsociado(asociadoSeleccionado.getId());
    }

    /**
     * Lanza la tarea del operario que solicitara mantenimiento a la ambulancia.
     */
    public void llamarOperario() {
        Thread hiloOperario = new Thread(this.ope);
        hiloOperario.start();
    }

    /**
     * Detiene la simulacion liberando a la ambulancia.
     */
    public void finalizarSimulacion() {
        this.ambulancia.setSimulacionActiva(false);
    }

    /**
     * Inicia la simulacion con una cantidad de asociados y las iteraciones.
     *
     * @param cantidadAsociados cantidad de asociados concurrentes
     * @param iteraciones numero de atenciones que ejecutara cada asociado
     */
    public void empezarSimulacion(int cantidadAsociados, int iteraciones) {
        ArrayList<Asociado> asociados = (ArrayList<Asociado>) getAsociados();
        this.ambulancia.setSimulacionActiva(true);
        for (int i=0;i<cantidadAsociados;i++) {
            Asociado asociado = asociados.get(i);
            if(asociado.isAlta()){
                asociado.setCantidadAtenciones(iteraciones);
                asociado.setAmbulancia(ambulancia);
                Thread hiloAsociado = new Thread(asociado);
                hiloAsociado.start();
            }
        }
    }
}
