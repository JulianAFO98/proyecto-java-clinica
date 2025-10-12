package proyecto.modelo.interfaces;

import proyecto.modelo.Domicilio;

/**
 * Interfaz que define los métodos comunes para las personas en el sistema.
 */
public interface IPersona {
    String getNombre();
    String getApellido();
    Domicilio getDomicilio();
    String getCiudad();
    String getTelefono();
}
