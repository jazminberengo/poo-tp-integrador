package busqueda;

import catalogo.ItemCatalogo;

public class NombreContiene implements CriterioBusqueda {

	private String texto;
	
	public NombreContiene(String texto) {
		this.texto = texto;
	}
	
	@Override
	public boolean cumple(ItemCatalogo item) {
		return item.getNombre().contains(texto);
	}

}
