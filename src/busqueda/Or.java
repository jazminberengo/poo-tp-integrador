package busqueda;

import catalogo.ItemCatalogo;
import java.util.List;

public class Or implements CriterioBusqueda{

	private List<CriterioBusqueda> criterios;
	
	public Or(List<CriterioBusqueda> criterios) {
		this.criterios = criterios;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		for(CriterioBusqueda criterio : this.criterios) {
			if(criterio.cumple(item)) {
				return true;
			}
		}
		return false;
	}

}
