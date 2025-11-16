package proyecto.modelo;

/**
 * Clase que representa un domicilio con calle y numero.
 * Proporciona metodos para obtener y modificar estos atributos.
 */
public class Domicilio {
	private String calle;
	private int numero;
	
	/**
	 * Constructor de la clase Domicilio.
	 * @param calle calle del domicilio
	 * @param numero numero del domicilio
	 */
	public Domicilio(String calle, int numero)
	{
		assert calle != null && !calle.trim().isEmpty() : "La calle no puede ser nula o vacia.";
		assert numero > 0 : "El numero de domicilio debe ser un valor positivo.";
		
		this.calle = calle;
		this.numero = numero;
		
		assert this.calle.equals(calle) : "La calle no se asigno correctamente.";
		assert this.numero == numero : "El numero no se asigno correctamente.";
	}

	public void setCalle(String calle)
	{
		assert calle != null && !calle.trim().isEmpty() : "La calle a asignar no puede ser nula o vacia.";
		this.calle = calle;
	}
	
	public void setNumero(int numero)
	{
		assert numero > 0 : "El numero de domicilio debe ser un valor positivo.";
		this.numero = numero;
	}
	
	public String getCalle() {
		assert calle != null && !calle.trim().isEmpty() : "La calle no debe ser nula en ningun momento.";
		return calle;
	}

	public int getNumero() {
		assert numero > 0 : "El numero de domicilio no debe ser menor o igual a cero.";
		return numero;
	}

}