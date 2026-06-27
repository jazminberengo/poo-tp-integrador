package reportes;

import catalogo.ItemCatalogo;

public class Entrada {
	
	private ItemCatalogo itemCatalogo;
	private int unidadesVendidas;
	private float precioPromedio;
	
	public Entrada (ItemCatalogo itemCatalogo, int unidadesVendidas, float precioPromedio) {
		this.itemCatalogo = itemCatalogo;
		this.unidadesVendidas = unidadesVendidas;
		this.precioPromedio = precioPromedio;
	}
	
	public void setUnidadesVendidas( int unidadesVendidas ) {
		this.unidadesVendidas = unidadesVendidas;
	}
	
	public Entrada AgregarDatos(Entrada otra) {
	    int totalUnidades = this.unidadesVendidas + otra.unidadesVendidas;
	    if (totalUnidades > 0) {
	        this.precioPromedio = ((this.unidadesVendidas * this.precioPromedio) + 
	                               (otra.unidadesVendidas * otra.precioPromedio)) / totalUnidades;
	    }
	    this.unidadesVendidas = totalUnidades;
	    return this; // "this" para que funcione con  merge 
	}
	
	public int getUnidadesVendidas() {	return unidadesVendidas;	}
	public ItemCatalogo getItemCatalogo() {	return itemCatalogo;	}
	public float getPrecioPromedio() {	return precioPromedio;	}
}
