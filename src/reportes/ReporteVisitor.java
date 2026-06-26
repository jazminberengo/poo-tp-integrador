package reportes;

import catalogo.Paquete;
import catalogo.Producto;

public interface ReporteVisitor {

	public void visitarProducto(Producto producto);
	
	public void visitarPaquete(Paquete paquete);
}
