package catalogo;

import java.util.List;

import reportes.ElementoReportable;
import reportes.Entrada;

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
	
	public abstract List<Entrada> getListEntrada(int cantidadLineaPedido);
}
