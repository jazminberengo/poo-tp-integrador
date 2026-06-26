package catalogo;

import java.util.ArrayList;
import java.util.List;

import busqueda.CriterioBusqueda;

public class Catalogo {
	
	private List<ItemCatalogo> items;
	
	public Catalogo(){
		items = new ArrayList<ItemCatalogo>();
	}
	
	public void agregar( ItemCatalogo item ) {
		items.add(item);
	}
	
	public List<ItemCatalogo> buscar( CriterioBusqueda criterio ){
		
		return items.stream().
				filter( i -> criterio.cumple(i) ).
				toList();
	}
	
}
