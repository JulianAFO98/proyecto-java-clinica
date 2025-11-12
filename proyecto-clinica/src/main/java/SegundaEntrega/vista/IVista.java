package SegundaEntrega.vista;

import java.awt.event.ActionListener;
import java.util.List;

import SegundaEntrega.modelo.Datos.Asociado;

public interface IVista {
    void addActionListenerAsociado(ActionListener al);
    void addActionListenerSimulacion(ActionListener al);
    void mostrarMensaje(String s);
    String getDni();
    String getNombre();
    String getCantidad();
    void agregarALogAsociados(String s);
    void agregarALogSimulacion(String s);
    void cambiarEstadoInput(boolean estado);
    void actualizarListas(List<Asociado> asociados);
    void limpiarCamposAsociado();
    Asociado getAsociadoSeleccionado();
    static final String CREAR_ASOCIADO = "CREAR_ASOCIADO";
    static final String EMPEZAR_SIMULACION = "EMPEZAR_SIMULACION";
    static final String FINALIZAR_SIMULACION = "FINALIZAR_SIMULACION";
    static final String DAR_BAJA_ASOCIADO = "DAR_BAJA_ASOCIADO";
    static final String SELECCIONAR_ASOCIADO = "SELECCIONAR_ASOCIADO";
    static final String LLAMAR_OPERARIO = "LLAMAR_OPERARIO";
    
}
