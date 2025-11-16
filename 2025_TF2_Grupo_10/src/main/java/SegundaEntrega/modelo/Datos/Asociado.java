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
     * <br>Pre: name != null y no vacio
     * <br>Post: this.name = name
     * @param id codigo interno asignado
     * <br>Pre: id >= 0
     * <br>Post: this.id = id
     * @param dni identificacion unica
     * <br>Pre: dni != null y no vacio
     * <br>Post: this.dni = dni
     * @param alta indica si el asociado esta habilitado
     * <br>Pre: No aplica
     * <br>Post: this.alta = alta
     * @param ambulancia recurso que atendera sus solicitudes
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia, cantidadAtenciones = 0, estadoAsociado = "ATENCION_DOMICILIO"
     */
    public Asociado(String name, int id, String dni, boolean alta, Ambulancia ambulancia) {
        // Precondiciones
        assert name != null && !name.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        assert ambulancia != null : "La ambulancia no puede ser nula.";
        assert id >= 0 : "El ID debe ser no negativo.";

        this.id = id;
        this.dni = dni;
        this.alta = alta;
        this.name = name;
        this.ambulancia = ambulancia;
        this.cantidadAtenciones = 0;
        this.estadoAsociado = "ATENCION_DOMICILIO";

        // Postcondiciones
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
        assert this.cantidadAtenciones == 0 : "La cantidad de atenciones debe inicializarse en 0.";
        assert this.estadoAsociado.equals("ATENCION_DOMICILIO") : "El estado inicial debe ser ATENCION_DOMICILIO.";
    }

    /**
     * Ejecuta las solicitudes de atencion mientras la simulacion este activa.
     * <br>Pre: ambulancia != null
     * <br>Post: Las atenciones se procesan mientras cantidadAtenciones > 0 y la simulacion este activa. cantidadAtenciones disminuye.
     */
    @Override
    public void run() {
        // Invariante
        assert this.ambulancia != null : "La ambulancia no debe ser nula durante la ejecucion.";

        while (ambulancia.isSimulacionActiva() && cantidadAtenciones > 0) {
            try {
                int atencionesAntes = cantidadAtenciones;
                
                // Determinacion del estado (postcondicion parcial)
                this.estadoAsociado = random.nextBoolean() ? "ATENCION_DOMICILIO" : "TRASLADO_CLINICA";
                assert this.estadoAsociado.equals("ATENCION_DOMICILIO") || this.estadoAsociado.equals("TRASLADO_CLINICA") : "El estado debe ser uno de los dos validos.";
                
                ambulancia.ejecutarAmbulancia(this); // Pre: this != null, Post: ambulancia marcada como en uso
                Thread.sleep(1500); // Simula trabajo con una pausa
                ambulancia.liberarAmbulancia(this); // Pre: ambulancia.ambulanciaEnUso = true, Post: ambulancia.ambulanciaEnUso = false
                
                cantidadAtenciones--;
                
                // Postcondicion de iteracion
                assert cantidadAtenciones == atencionesAntes - 1 : "La cantidad de atenciones debe disminuir en 1.";

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Postcondicion de interrupcion
                assert Thread.currentThread().isInterrupted() : "El thread deberia estar interrumpido despues de manejar la excepcion.";
            }
        }
    }

    /**
     * Devuelve el ultimo estado requerido por el asociado.
     *
     * @return etiqueta del estado actual
     * <br>Pre: No aplica
     * <br>Post: Retorna una cadena no nula.
     */
    public String getEstadoAsoociado() {
        assert estadoAsociado != null : "El estado del asociado no debe ser nulo.";
        return estadoAsociado;
    }

    /**
     * Asigna la ambulancia que atendera al asociado.
     *
     * @param ambulancia referencia compartida con el sistema
     * <br>Pre: ambulancia != null
     * <br>Post: this.ambulancia = ambulancia
     */
    public void setAmbulancia(Ambulancia ambulancia) {
        assert ambulancia != null : "La ambulancia no puede ser nula.";
        this.ambulancia = ambulancia;
        assert this.ambulancia == ambulancia : "La ambulancia no se asigno correctamente.";
    }

    /**
     * Obtiene el identificador interno.
     *
     * @return identificador numerico
     * <br>Pre: No aplica
     * <br>Post: Retorna un valor >= 0.
     */
    public int getId() {
        assert id >= 0 : "El ID debe ser no negativo.";
        return id;
    }

    /**
     * Actualiza el identificador interno.
     *
     * @param id valor numerico a registrar
     * <br>Pre: id >= 0
     * <br>Post: this.id = id
     */
    public void setId(int id) {
        assert id >= 0 : "El ID debe ser no negativo.";
        this.id = id;
        assert this.id == id : "El ID no se asigno correctamente.";
    }

    /**
     * Devuelve el documento del asociado.
     *
     * @return cadena con el dni
     * <br>Pre: No aplica
     * <br>Post: Retorna un DNI no nulo y no vacio.
     */
    public String getDni() {
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        return dni;
    }

    /**
     * Establece el documento identificatorio.
     *
     * @param dni nuevo valor del documento
     * <br>Pre: dni != null y no vacio
     * <br>Post: this.dni = dni
     */
    public void setDni(String dni) {
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        this.dni = dni;
        assert this.dni.equals(dni) : "El DNI no se asigno correctamente.";
    }

    /**
     * Indica si el asociado tiene alta vigente.
     *
     * @return true si el asociado se encuentra habilitado
     * <br>Pre: No aplica
     * <br>Post: No modifica el estado.
     */
    public boolean isAlta() {
        return alta;
    }

    /**
     * Actualiza la condicion de alta del asociado.
     *
     * @param alta true cuando se habilita el acceso
     * <br>Pre: No aplica
     * <br>Post: this.alta = alta
     */
    public void setAlta(boolean alta) {
        this.alta = alta;
        assert this.alta == alta : "El estado de alta no se asigno correctamente.";
    }

    /**
     * Obtiene el nombre descriptivo del asociado.
     *
     * @return cadena con el nombre
     * <br>Pre: No aplica
     * <br>Post: Retorna un nombre no nulo y no vacio.
     */
    public String getName() {
        assert name != null && !name.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        return name;
    }

    /**
     * Establece el nombre descriptivo.
     *
     * @param name valor textual a asignar
     * <br>Pre: name != null y no vacio
     * <br>Post: this.name = name
     */
    public void setName(String name) {
        assert name != null && !name.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        this.name = name;
        assert this.name.equals(name) : "El nombre no se asigno correctamente.";
    }

    /**
     * Representacion textual resumida del asociado.
     *
     * @return formato legible con datos clave
     * <br>Pre: Los atributos clave (name, id, dni) no deben ser nulos.
     * <br>Post: Retorna una cadena no nula.
     */
    @Override
    public String toString() {
        assert getName() != null : "El nombre no debe ser nulo.";
        assert getDni() != null : "El DNI no debe ser nulo.";
        return getName() + "(id " + id + ") (dni: " + dni + ") alta: (" + alta + ")";
    }

    /**
     * Cantidad de atenciones pendientes.
     *
     * @return valor numerico restante
     * <br>Pre: No aplica
     * <br>Post: Retorna un valor >= 0.
     */
    public int getCantidadAtenciones() {
        assert cantidadAtenciones >= 0 : "La cantidad de atenciones no debe ser negativa.";
        return cantidadAtenciones;
    }

    /**
     * Establece la cantidad de atenciones que debe procesar el asociado.
     *
     * @param cantidadAtenciones numero de rondas pendientes
     * <br>Pre: cantidadAtenciones >= 0
     * <br>Post: this.cantidadAtenciones = cantidadAtenciones
     */
    public void setCantidadAtenciones(int cantidadAtenciones) {
        assert cantidadAtenciones >= 0 : "La cantidad de atenciones no debe ser negativa.";
        this.cantidadAtenciones = cantidadAtenciones;
        assert this.cantidadAtenciones == cantidadAtenciones : "La cantidad de atenciones no se asigno correctamente.";
    }

    
}