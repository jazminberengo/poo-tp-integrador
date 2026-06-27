package catalogo;

import reportes.ElementoReportable;


public abstract class ItemCatalogo implements ElementoReportable {

	private String nombre;
	private String descripcion;
	
	public ItemCatalogo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	public abstract boolean validar();
	
	public abstract float getPrecioBase();

	public abstract float getPeso();
	
	public String getNombre() {	return nombre;	}
	public String getDescripcion() {	return descripcion;	}

}











