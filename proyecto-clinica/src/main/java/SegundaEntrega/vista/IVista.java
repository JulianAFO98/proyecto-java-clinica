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
    void agregarALogAsociados(String s);
    void agregarALogSimulacion(String s);
    void actualizarListas(List<Asociado> asociados);
    static final String CREAR_ASOCIADO = "CREAR_ASOCIADO";
    static final String EMPEZAR_SIMULACION = "EMPEZAR_SIMULACION";
    static final String FINALIZAR_SIMULACION = "FINALIZAR_SIMULACION";
}
