package envios;

public class Direccion {
	
	private String calle;
	private String altura;
	private String ciudad;
	private String provincia;
	private String codigoPostal;
	private float distanciaKm; // Atributo clave para el cálculo del envío estándar

	public Direccion(String calle, String altura, String ciudad, String provincia, String codigoPostal, float distanciaKm) {
	    this.calle = calle;
	    this.altura = altura;
	    this.ciudad = ciudad;
	    this.provincia = provincia;
	    this.codigoPostal = codigoPostal;
	    this.distanciaKm = distanciaKm;
	}

	
	public float getDistanciaKm() {
	    return distanciaKm;
	}

	public String getCodigoPostal() {
	    return codigoPostal;
	}

}













