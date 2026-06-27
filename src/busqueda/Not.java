package busqueda;

import catalogo.ItemCatalogo;

public class Not implements CriterioBusqueda{
	
	private CriterioBusqueda criterio;
	
	public Not(CriterioBusqueda criterio) {
		this.criterio = criterio;
	}

	@Override
	public boolean cumple(ItemCatalogo item) {
		return !criterio.cumple(item);
	}
	
}
