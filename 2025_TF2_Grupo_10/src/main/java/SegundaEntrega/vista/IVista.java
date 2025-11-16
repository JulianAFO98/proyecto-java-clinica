package SegundaEntrega.vista;

import java.awt.event.ActionListener;
import java.util.List;

import SegundaEntrega.modelo.Datos.Asociado;

/**
 * Define las operaciones que la vista debe exponer a los controladores.
 */
public interface IVista {
    /**
     * Registra un listener para las acciones de gestion de asociados.
     *
     * @param al listener a vincular
     */
    void addActionListenerAsociado(ActionListener al);

    /**
     * Registra un listener para los controles de simulacion.
     *
     * @param al listener a vincular
     */
    void addActionListenerSimulacion(ActionListener al);

    /**
     * Muestra un mensaje informativo al usuario.
     *
     * @param s texto a presentar
     */
    void mostrarMensaje(String s);

    /**
     * Obtiene el dni ingresado en la vista.
     *
     * @return valor textual del dni
     */
    String getDni();

    /**
     * Devuelve el nombre ingresado por el usuario.
     *
     * @return cadena con el nombre
     */
    String getNombre();

    /**
     * Recupera la cantidad de asociados solicitada para la simulacion.
     *
     * @return texto numerico de cantidad
     */
    String getCantidad();

    /**
     * Obtiene la cantidad de iteraciones configurada.
     *
     * @return texto numerico de iteraciones
     */
    String getIteracion();

    /**
     * Agrega una entrada al log de asociados.
     *
     * @param s mensaje a anexar
     */
    void agregarALogAsociados(String s);

    /**
     * Agrega una entrada al log de simulacion.
     *
     * @param s mensaje a anexar
     */
    void agregarALogSimulacion(String s);

    /**
     * Habilita o deshabilita los campos de entrada.
     *
     * @param estado true para habilitar
     */
    void cambiarEstadoInput(boolean estado);

    /**
     * Actualiza la lista visible de asociados.
     *
     * @param asociados coleccion proveniente del modelo
     */
    void actualizarListas(List<Asociado> asociados);

    /**
     * Limpia los campos de carga de asociados.
     */
    void limpiarCamposAsociado();

    /**
     * Obtiene el asociado seleccionado en la vista.
     *
     * @return asociado elegido o null
     */
    Asociado getAsociadoSeleccionado();
    static final String CREAR_ASOCIADO = "CREAR_ASOCIADO";
    static final String EMPEZAR_SIMULACION = "EMPEZAR_SIMULACION";
    static final String FINALIZAR_SIMULACION = "FINALIZAR_SIMULACION";
    static final String DAR_BAJA_ASOCIADO = "DAR_BAJA_ASOCIADO";
    static final String SELECCIONAR_ASOCIADO = "SELECCIONAR_ASOCIADO";
    static final String LLAMAR_OPERARIO = "LLAMAR_OPERARIO";
    
}
