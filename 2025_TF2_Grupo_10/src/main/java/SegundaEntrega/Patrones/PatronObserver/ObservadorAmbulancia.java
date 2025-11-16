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
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia y se registra este observador en la ambulancia.
     * @param vista interfaz que mostrara las notificaciones
     * <br>Pre: vista != null
     * <br>Post: this.vista = vista
     */
    public ObservadorAmbulancia(Ambulancia ambulancia, IVista vista) {
        // Precondiciones
        assert ambulancia != null : "El sujeto observable (ambulancia) no puede ser nulo.";
        assert vista != null : "La vista (objetivo) no puede ser nula.";

        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
        this.vista = vista;
        
        // Postcondiciones
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
        assert this.vista == vista : "La vista no se asigno correctamente.";
    }

    /**
     * Recibe las notificaciones de la ambulancia y las envia a la vista.
     *
     * @param o ambulancia observada
     * <br>Pre: o != null y o == this.ambulancia (es la ambulancia registrada).
     * <br>Post: No se modifica el estado interno del objeto. Se envia el mensaje a la vista.
     * @param arg mensaje emitido por la ambulancia
     * <br>Pre: arg es de tipo String.
     * <br>Post: No se modifica el estado interno del objeto. Se envia el mensaje a la vista.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Precondiciones
        assert o != null : "El observable que notifica no puede ser nulo.";
        assert arg instanceof String : "El argumento de notificacion debe ser un String.";
        assert this.ambulancia != null : "El observable interno no debe ser nulo.";
        assert this.vista != null : "La vista interna no debe ser nula.";

        if (o != this.ambulancia) {
            // Se lanza una excepcion si la fuente no es la esperada (violacion de precondicion)
            throw new IllegalArgumentException();
        }
        
        // El cast debe ser seguro debido a la logica de la clase Ambulancia (que es la fuente)
        Ambulancia ambulancia = (Ambulancia) o; 
        String mensaje = (String) arg;
        
        this.vista.agregarALogSimulacion(mensaje);
        
        // Postcondicion implicita: se delega la tarea a la vista.
    }
}