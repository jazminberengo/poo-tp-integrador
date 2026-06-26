package busqueda;

import catalogo.ItemCatalogo;

public class PrecioMaximo implements CriterioBusqueda{

	private float maximo;
	
	public PrecioMaximo(float maximo) {
		this.maximo = maximo;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.getPrecioBase() <= maximo;
	}

}
