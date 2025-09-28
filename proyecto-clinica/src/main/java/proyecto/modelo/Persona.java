package proyecto.modelo;
import proyecto.modelo.interfaces.IPersona;

public abstract class Persona implements IPersona{
    private String dni;
    private String nombre;
    private String apellido;
    private Domicilio domicilio;
    private String ciudad;
    private String telefono;
    
    public Persona(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio)
    {
    	this.dni = dni;
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.domicilio = domicilio;
    	this.ciudad = ciudad;
    	this.telefono = telefono;
    }

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getTelefono() {
		return telefono;
	}
    
    
}
