package envios;

import catalogo.ItemCatalogo;
import pedido.LineaPedido;

public class Registro {

	private int cantidad;
	private ItemCatalogo itemCatalogo;
	
	public void setItem( ItemCatalogo itemCatalogo ) { this.itemCatalogo = itemCatalogo;  }
	
	public void setCantidad( int cantidad ) { this.cantidad = cantidad; }
	
	public int getCantidad() { return cantidad; }
	
	public ItemCatalogo getItem() { return itemCatalogo; }
	
	public boolean cumplePara( LineaPedido lineaPedido) {
		return lineaPedido.getCantidad() <= this.getCantidad();
	}
}
