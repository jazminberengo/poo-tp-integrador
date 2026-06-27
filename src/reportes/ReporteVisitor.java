package reportes;

import java.util.List;

public abstract class ReporteVisitor implements ItemVisitor{

	 protected int cantidadActual;
	 protected float precioCobradoActual;

	 public void setVentaActual(int cantidad, float precio) {
		 this.cantidadActual = cantidad;
		 this.precioCobradoActual = precio;
	 }

	 public abstract List<Entrada> getFinishedList();
	 
	 public abstract String titulo();
}
