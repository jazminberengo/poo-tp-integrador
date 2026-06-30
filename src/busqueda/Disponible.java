package busqueda;

import catalogo.ItemCatalogo;

public class Disponible implements CriterioBusqueda{

	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.tieneStockDisponible();
	}

}
