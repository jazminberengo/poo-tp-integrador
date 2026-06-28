package busqueda;

import catalogo.ItemCatalogo;

public class PorCategoria implements CriterioBusqueda {

	private String categoria;
	
	public PorCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.tieneCategoria(categoria);
	}

}
