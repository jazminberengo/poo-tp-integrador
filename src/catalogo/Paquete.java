package catalogo;

import java.util.List;

import java.util.ArrayList;
import reportes.ItemVisitor;

public class Paquete extends ItemCatalogo{

	public Float descuento;
	public List<ItemCatalogo> items;

	public Paquete(	String nombre, String descripcion) {
		super(nombre, descripcion);
		items = new ArrayList<ItemCatalogo>();
	}
	
	public void setDescuento( Float descuento ) { this.descuento = descuento; }
	
	@Override
	public boolean validar() {
		if ( this.getNombre() == null ) {
			System.out.println("nombre es un atributo invalido");
			return false;
			}
		if ( this.getDescripcion() == null ) {
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
	
	public void agregar( ItemCatalogo ic ){ items.add( ic ); }

	public void remover(ItemCatalogo ic){ 
		String nombreABuscar = ic.getNombre();
		items.removeIf(item -> item.getNombre().equals(nombreABuscar));
	}
	
	//Visitor Pattern
	@Override
	public void accept( ItemVisitor itemVisitor) {
		itemVisitor.visit(this);
		
	}
		
}













