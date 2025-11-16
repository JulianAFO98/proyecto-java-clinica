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
     * <br>Pre: a != null
     * <br>Post: this.ambulancia = a, se crea el operario y el DAO de persistencia.
     */
    public GestionLlamados(Ambulancia a) {
        // Precondicion
        assert a != null : "La ambulancia no puede ser nula.";

        this.ambulancia = a;
        this.ope = new Operario(a);
        this.asociadoDao = new PersistenciaDatabase();
        
        // Postcondicion
        assert this.ambulancia == a : "La ambulancia no se asigno correctamente.";
        assert this.ope != null : "El operario no se creo correctamente.";
        assert this.asociadoDao != null : "El DAO de asociado no se creo correctamente.";
    }

    /**
     * Registra un nuevo asociado validando la inexistencia del dni.
     *
     * @param nombre nombre del asociado a crear
     * <br>Pre: nombre != null y no vacio
     * <br>Post: se persiste el asociado en la base de datos.
     * @param dni documento unico
     * <br>Pre: dni != null y no vacio
     * <br>Post: se persiste el asociado en la base de datos.
     * @throws AsociadoExistenteException cuando ya existe un registro con el mismo dni
     */
    public void agregarAsociado(String nombre, String dni) throws AsociadoExistenteException {
        // Precondiciones
        assert nombre != null && !nombre.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        assert asociadoDao != null : "El DAO no debe ser nulo.";

        AsociadoDTO asociado = asociadoDao.getAsociadobyDNI(dni);
        if (asociado != null) {
            // Invariante: si existe, se lanza excepcion.
            throw new AsociadoExistenteException("El dni ya existe", dni);
        }
        
        // Postcondicion: el asociado se crea y persiste (solo se puede verificar si devuelve un objeto)
        AsociadoDTO a = asociadoDao.createAsociado(new AsociadoDTO(dni, true, nombre));
        assert a != null && a.getDni().equals(dni) : "El asociado no se creo o el DNI no coincide.";
    }

    /**
     * Obtiene los asociados dados de alta desde la base de datos.
     *
     * @return lista de asociados activos
     * <br>Pre: asociadoDao != null
     * <br>Post: La lista retornada solo contiene asociados con isAlta() = true.
     */
    public List<Asociado> getAsociados() {
        assert asociadoDao != null : "El DAO no debe ser nulo.";
        
        List<AsociadoDTO> asociadosDTO = asociadoDao.getAllAsociados();
        assert asociadosDTO != null : "La lista de DTOs no puede ser nula.";

        List<Asociado> asociados = Utils.fromDTOtoClass(asociadosDTO);
        List<Asociado> asociadoDeAlta = new ArrayList<>();
        
        for (Asociado asociado : asociados) {
            if (asociado.isAlta()) {
                asociadoDeAlta.add(asociado);
            }
        }
        
        // Postcondicion
        assert asociadoDeAlta != null : "La lista final de asociados no puede ser nula.";
        for (Asociado asociado : asociadoDeAlta) {
            assert asociado.isAlta() : "Todos los asociados en la lista de retorno deben estar de alta.";
        }
        
        return asociadoDeAlta;
    }

    /**
     * Marca un asociado como dado de baja en la base de datos.
     *
     * @param asociadoSeleccionado asociado elegido en la vista
     * <br>Pre: asociadoSeleccionado != null y asociadoSeleccionado.getId() > 0
     * <br>Post: El asociado queda marcado como inactivo en la base de datos.
     */
    public void eliminarAsociado(Asociado asociadoSeleccionado) {
        // Precondiciones
        assert asociadoSeleccionado != null : "El asociado seleccionado no puede ser nulo.";
        assert asociadoSeleccionado.getId() > 0 : "El ID del asociado debe ser positivo.";
        assert asociadoDao != null : "El DAO no debe ser nulo.";

        System.out.println("Eliminando asociado: " + asociadoSeleccionado.getId());
        asociadoDao.darDeBajaAsociado(asociadoSeleccionado.getId());
        
        // No hay postcondicion directa ya que no podemos consultar el estado del objeto en la DB.
    }

    /**
     * Lanza la tarea del operario que solicitara mantenimiento a la ambulancia.
     * <br>Pre: this.ope != null
     * <br>Post: Se inicia un nuevo hilo con la ejecucion del operario.
     */
    public void llamarOperario() {
        assert this.ope != null : "El operario no debe ser nulo para iniciar su hilo.";

        Thread hiloOperario = new Thread(this.ope);
        hiloOperario.start();
    }

    /**
     * Detiene la simulacion liberando a la ambulancia.
     * <br>Pre: this.ambulancia != null
     * <br>Post: ambulancia.isSimulacionActiva() es false.
     */
    public void finalizarSimulacion() {
        assert this.ambulancia != null : "La ambulancia no debe ser nula.";

        this.ambulancia.setSimulacionActiva(false);
        
        // Postcondicion
        assert !this.ambulancia.isSimulacionActiva() : "La simulacion debe estar inactiva.";
    }

    /**
     * Inicia la simulacion con una cantidad de asociados y las iteraciones.
     *
     * @param cantidadAsociados cantidad de asociados concurrentes
     * <br>Pre: cantidadAsociados > 0 y menor o igual a la cantidad de asociados de alta disponibles.
     * <br>Post: ambulancia.isSimulacionActiva() = true y se lanzan N hilos de asociados.
     * @param iteraciones numero de atenciones que ejecutara cada asociado
     * <br>Pre: iteraciones > 0
     * <br>Post: ambulancia.isSimulacionActiva() = true y se lanzan N hilos de asociados.
     */
    public void empezarSimulacion(int cantidadAsociados, int iteraciones) {
        // Precondiciones
        assert cantidadAsociados > 0 : "La cantidad de asociados debe ser positiva.";
        assert iteraciones > 0 : "La cantidad de iteraciones debe ser positiva.";
        assert this.ambulancia != null : "La ambulancia no debe ser nula.";
        
        List<Asociado> asociadosList = getAsociados();
        
        // Precondicion: la cantidad solicitada no excede los disponibles de alta.
        assert cantidadAsociados <= asociadosList.size() : "La cantidad solicitada excede los asociados dados de alta.";

        ArrayList<Asociado> asociados = (ArrayList<Asociado>) asociadosList;
        this.ambulancia.setSimulacionActiva(true);
        int hilosIniciados = 0;
        
        for (int i=0;i<cantidadAsociados;i++) {
            Asociado asociado = asociados.get(i);
            
            // Invariante de ciclo: asociado.isAlta() debe ser true ya que getAsociados solo devuelve de alta
            assert asociado != null : "El asociado no debe ser nulo.";
            assert asociado.isAlta() : "Solo se deben procesar asociados de alta.";

            if(asociado.isAlta()){
                asociado.setCantidadAtenciones(iteraciones);
                asociado.setAmbulancia(ambulancia);
                Thread hiloAsociado = new Thread(asociado);
                hiloAsociado.start();
                hilosIniciados++;
            }
        }
        
        // Postcondiciones
        assert this.ambulancia.isSimulacionActiva() : "La simulacion debe estar activa.";
        assert hilosIniciados == cantidadAsociados : "No se inicio la cantidad correcta de hilos.";
    }
}