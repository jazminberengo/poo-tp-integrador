package catalogo;

import java.util.ArrayList;
import reportes.ReporteVisitor;

public class Paquete extends ItemCatalogo{

	public Float descuento;
	public ArrayList<ItemCatalogo> items;

	public Paquete(	String nombre, String descripcion) {
		super(nombre, descripcion);
		items = new ArrayList<ItemCatalogo>();
	}
	
	public void setDescuento( Float descuento ) { this.descuento = descuento; }
	
	@Override
	public boolean validar() {
		if ( nombre == null ) {
			System.out.println("nombre es un atributo invalido");
			return false;
			}
		if ( descripcion == null ) {
			System.out.println("descripcion es un atributo invalido");
			return false; 
			}
		if ( descuento == null ) {
			System.out.println("descuento es un atributo invalido");
			return false;
		}
		
		return items.stream().allMatch(i -> i.validar());
	}

	@Override
	public float getPrecioBase() {
		
		double sumaItems = 	items.stream().
							mapToDouble( i -> i.getPrecioBase()).
							sum();
		
		return (float) (sumaItems * ( 1 - ( descuento.floatValue() / 100 ) ));
	}

	@Override
	public float getPeso() {
		
		return (float) items.stream().
						mapToDouble( i -> i.getPeso() ).
						sum();
	}

	//Visitor Pattern
	@Override
	public void accept( ReporteVisitor reporteVisitor ) {
		reporteVisitor.visit(this);
		
	}
		
}













