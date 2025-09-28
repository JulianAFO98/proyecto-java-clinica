package proyecto.modelo.interfaces;

import proyecto.modelo.Domicilio;

public interface IPersona {
    String getNombre();
    String getApellido();
    Domicilio getDomicilio();
    String getCiudad();
    String getTelefono();
}
