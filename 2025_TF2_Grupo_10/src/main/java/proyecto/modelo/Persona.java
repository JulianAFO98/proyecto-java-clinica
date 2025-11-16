package proyecto.modelo;
import proyecto.modelo.interfaces.IPersona;

/**
 * Clase abstracta que define los atributos correspondientes a una persona 
 * */

public abstract class Persona implements IPersona{
    private String dni;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String ciudad;
    private String telefono;
    
    public Persona(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio)
    {
        assert dni != null && !dni.trim().isEmpty() : "El DNI no puede ser nulo o vacio.";
        assert nombre != null && !nombre.trim().isEmpty() : "El nombre no puede ser nulo o vacio.";
        assert apellido != null && !apellido.trim().isEmpty() : "El apellido no puede ser nulo o vacio.";
        assert ciudad != null && !ciudad.trim().isEmpty() : "La ciudad no puede ser nula o vacia.";
        assert telefono != null && !telefono.trim().isEmpty() : "El telefono no puede ser nulo o vacio.";
        assert domicilio != null : "El domicilio no puede ser nulo.";

    	this.dni = dni;
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.domicilio = domicilio;
    	this.ciudad = ciudad;
    	this.telefono = telefono;
        
        assert this.dni.equals(dni) : "El DNI no se asigno correctamente.";
        assert this.domicilio.equals(domicilio) : "El domicilio no se asigno correctamente.";
    }

	public String getDni() {
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
		return dni;
	}

	public String getNombre() {
        assert nombre != null && !nombre.trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
		return nombre;
	}

	public String getApellido() {
        assert apellido != null && !apellido.trim().isEmpty() : "El apellido no debe ser nulo o vacio.";
		return apellido;
	}

	public Domicilio getDomicilio() {
        assert domicilio != null : "El domicilio no debe ser nulo.";
		return domicilio;
	}

	public String getCiudad() {
        assert ciudad != null && !ciudad.trim().isEmpty() : "La ciudad no debe ser nula o vacia.";
		return ciudad;
	}

	public String getTelefono() {
        assert telefono != null && !telefono.trim().isEmpty() : "El telefono no debe ser nulo o vacio.";
		return telefono;
	}
}