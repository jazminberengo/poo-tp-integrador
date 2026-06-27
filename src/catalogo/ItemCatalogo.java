package catalogo;

import reportes.ElementoReportable;


public abstract class ItemCatalogo implements ElementoReportable{

	public String nombre;
	public String descripcion;
	
	public ItemCatalogo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
	
	public abstract boolean validar();
	
	public abstract float getPrecioBase();

	public abstract float getPeso();
	
	public String getNombre() {	return nombre;	}
}
