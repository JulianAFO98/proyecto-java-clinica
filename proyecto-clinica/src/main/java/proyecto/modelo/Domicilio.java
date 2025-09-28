package proyecto.modelo;

public class Domicilio {
	private String calle;
	private int numero;
	
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
