package proyecto.modelo;

/**
 * Clase que representa un domicilio con calle y número.
 * Proporciona métodos para obtener y modificar estos atributos.
 */
public class Domicilio {
	private String calle;
	private int numero;
	
	/**
	 * Constructor de la clase Domicilio.
	 * @param calle calle del domicilio
	 * @param numero número del domicilio
	 */
	public Domicilio(String calle, int numero)
	{
		this.calle = calle;
		this.numero = numero;
	}

	public void setCalle(String calle)
	{
		this.calle = calle;
	}
	
	public void setNumero(int numero)
	{
		this.numero = numero;
	}
	
	public String getCalle() {
		return calle;
	}

	public int getNumero() {
		return numero;
	}

}
