package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public interface ItemVisitor {
	
	public void visit(Producto producto);
	
	public void visit(Paquete paquete);
}
