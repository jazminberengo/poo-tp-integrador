package catalogo;

import reportes.ElementoReportable;

public abstract class ItemCatalogo implements ElementoReportable{

	public String nombre;
	
	public String descripcion;
	
	public abstract boolean validar();
	
	public abstract float getPrecioBase();
}
