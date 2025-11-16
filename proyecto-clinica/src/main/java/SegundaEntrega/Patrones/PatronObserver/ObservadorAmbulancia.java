package SegundaEntrega.Patrones.PatronObserver;

import java.util.Observable;
import java.util.Observer;

import SegundaEntrega.modelo.Datos.Ambulancia;
import SegundaEntrega.vista.IVista;

/**
 * Implementa el observador que publica los cambios de la ambulancia en la vista.
 */
public class ObservadorAmbulancia implements Observer {
    private Observable ambulancia;
    private IVista vista;

    /**
     * Registra el observador sobre la ambulancia y guarda la vista objetivo.
     *
     * @param ambulancia sujeto observado
     * @param vista interfaz que mostrara las notificaciones
     */
    public ObservadorAmbulancia(Ambulancia ambulancia, IVista vista) {
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
        this.vista = vista;
    }

    /**
     * Recibe las notificaciones de la ambulancia y las envia a la vista.
     *
     * @param o ambulancia observada
     * @param arg mensaje emitido por la ambulancia
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o != this.ambulancia)
            throw new IllegalArgumentException();
        Ambulancia ambulancia = (Ambulancia) o;
        String mensaje = (String) arg;
        this.vista.agregarALogSimulacion(mensaje);
    }
}
