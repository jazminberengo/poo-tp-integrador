package reportes;

import catalogo.Producto;

public class Entrada {
	
	public Producto producto;
	public int unidadesVendidas;
	public Float precioPromedio;
	
	public Entrada (Producto producto, int unidadesVendidas, Float precioPromedio) {
		this.producto = producto;
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
	
}
