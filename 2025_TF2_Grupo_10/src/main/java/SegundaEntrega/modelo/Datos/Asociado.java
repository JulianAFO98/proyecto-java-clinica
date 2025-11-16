package SegundaEntrega.modelo.Datos;

import java.util.Random;

/**
 * Representa a un asociado que interactua con la ambulancia durante la simulacion.
 */
public class Asociado implements Runnable {
    private int id;
    private String dni;
    private boolean alta;
    private String name;
    private Ambulancia ambulancia;
    private String estadoAsociado; // Nuevo atributo para el estado del asociado
    private int cantidadAtenciones;
    private final Random random = new Random();

    /**
     * Construye un asociado con datos de identificacion y referencia a la ambulancia.
     *
     * @param name nombre identificatorio del asociado
     * @param id codigo interno asignado
     * @param dni identificacion unica
     * @param alta indica si el asociado esta habilitado
     * @param ambulancia recurso que atendera sus solicitudes
     */
    public Asociado(String name, int id, String dni, boolean alta, Ambulancia ambulancia) {
        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.name = name;
        this.ambulancia = ambulancia;
        this.cantidadAtenciones = 0;
        this.estadoAsociado = "ATENCION_DOMICILIO";
    }

    /**
     * Ejecuta las solicitudes de atencion mientras la simulacion este activa.
     */
    @Override
    public void run() {
        while (ambulancia.isSimulacionActiva() && cantidadAtenciones > 0) {
            try {
                this.estadoAsociado = random.nextBoolean() ? "ATENCION_DOMICILIO" : "TRASLADO_CLINICA";
                ambulancia.ejecutarAmbulancia(this); // Ejemplo de llamada a un metodo de Ambulancia
                Thread.sleep(1500); // Simula trabajo con una pausa
                ambulancia.liberarAmbulancia(this);
                cantidadAtenciones--;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Devuelve el ultimo estado requerido por el asociado.
     *
     * @return etiqueta del estado actual
     */
    public String getEstadoAsoociado() {
        return estadoAsociado;
    }

    /**
     * Asigna la ambulancia que atendera al asociado.
     *
     * @param ambulancia referencia compartida con el sistema
     */
    public void setAmbulancia(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Obtiene el identificador interno.
     *
     * @return identificador numerico
     */
    public int getId() {
        return  id;
    }

    /**
     * Actualiza el identificador interno.
     *
     * @param id valor numerico a registrar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el documento del asociado.
     *
     * @return cadena con el dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el documento identificatorio.
     *
     * @param dni nuevo valor del documento
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Indica si el asociado tiene alta vigente.
     *
     * @return true si el asociado se encuentra habilitado
     */
    public boolean isAlta() {
        return alta;
    }

    /**
     * Actualiza la condicion de alta del asociado.
     *
     * @param alta true cuando se habilita el acceso
     */
    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    /**
     * Obtiene el nombre descriptivo del asociado.
     *
     * @return cadena con el nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre descriptivo.
     *
     * @param name valor textual a asignar
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Representacion textual resumida del asociado.
     *
     * @return formato legible con datos clave
     */
    @Override
    public String toString() {
        return getName() + "(id " + id + ") (dni: " + dni + ") alta: (" + alta + ")";
    }

    /**
     * Cantidad de atenciones pendientes.
     *
     * @return valor numerico restante
     */
    public int getCantidadAtenciones() {
        return cantidadAtenciones;
    }

    /**
     * Establece la cantidad de atenciones que debe procesar el asociado.
     *
     * @param cantidadAtenciones numero de rondas pendientes
     */
    public void setCantidadAtenciones(int cantidadAtenciones) {
        this.cantidadAtenciones = cantidadAtenciones;
    }

    
}
