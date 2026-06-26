package busqueda;

import catalogo.ItemCatalogo;
import java.util.List;

public class And implements CriterioBusqueda{

	private List<CriterioBusqueda> criterios;
	
	public And(List<CriterioBusqueda> criterios) {
		this.criterios = criterios;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		for (CriterioBusqueda criterio : this.criterios) {
			if (!criterio.cumple(item)) {
				return false;
			}
		}
		return true;
	}

}
