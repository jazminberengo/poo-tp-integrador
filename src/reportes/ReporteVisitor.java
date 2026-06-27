package reportes;

import java.util.List;

import catalogo.Paquete;
import catalogo.Producto;

public abstract class ReporteVisitor implements ItemVisitor{

	 protected int cantidadActual;
	 protected float precioCobradoActual;

	 public void setVentaActual(int cantidad, float precio) {
		 this.cantidadActual = cantidad;
		 this.precioCobradoActual = precio;
	 }

	 public abstract void visit(Producto p);
	 public abstract void visit(Paquete pa);
	 
	 public abstract List<Entrada> getFinishedList();
	 
	 public abstract String titulo();
}
