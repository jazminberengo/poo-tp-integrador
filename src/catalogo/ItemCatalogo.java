package catalogo;

import envios.Sucursal;
import reportes.ElementoReportable;


public abstract class ItemCatalogo implements ElementoReportable {

	private String nombre;
	private String descripcion;
	private Sucursal sucursal;
	
	public ItemCatalogo(String nombre, String descripcion, Sucursal sucursal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sucursal = sucursal;
    }
	
	public abstract boolean validar();
	
	public abstract float getPrecioBase();

	public abstract float getPeso();
	
	public abstract boolean tieneCategoria( String categoria );
	
	public String getNombre() {	return nombre;	}
	public String getDescripcion() {	return descripcion;	}

	public void decrementarStock( int cantidad ) {
		sucursal.decrementarStock(this, cantidad);
	}
	
	public void incrementarStock(int cantidad ) {
		sucursal.incrementarStock(this, cantidad);
	}
	
	public boolean tieneStockDisponible() {
		return sucursal.tieneStock(this);
	}
}











