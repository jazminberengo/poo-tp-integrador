package catalogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import reportes.ItemVisitor;

public class Producto extends ItemCatalogo{

	public String sku;
	public String marca;
	public String categoria;
	private Float  precio;
	public Float  descuentoPromo;
	private Float  peso;
	private Map<String, Object> atributos;
	
	public Producto(String nombre, String descripcion, 
					String sku, Float precio, Float peso,
					String marca) {
        super(nombre, descripcion);
        this.sku = sku;
        this.precio = precio;
        this.peso = peso;
        this.marca = marca;
        this.atributos = new HashMap<>(); 
    }
	
	public void setCategoria( String categoria ) { this.categoria = categoria; }
	public void setDescuento( Float descuento ) { this.descuentoPromo = descuento; }
	
	@Override
	public boolean validar() {	
		ArrayList<String> atributosInvalidos = new ArrayList<String>();
		
		if ( this.getNombre() == null ) 		{	atributosInvalidos.add("nombre"); }
		if ( this.getNombre() == null ) 	{ 	atributosInvalidos.add("descripcion"); }
		if ( sku == null )			{	atributosInvalidos.add("sku"); }
		if ( marca == null ) 		{	atributosInvalidos.add("marca"); }
		if ( precio == null )		{ 	atributosInvalidos.add("precio"); }
		if ( descuentoPromo == null )	{ 	atributosInvalidos.add("descuento"); }
		
		atributosInvalidos.addAll( atributos.entrySet().stream()
				.filter(e -> e.getValue() == null)
				.map(Map.Entry::getKey)
				.toList() );
		
		if( atributosInvalidos.isEmpty()) {
			System.out.println("No hay atributos inválidos");
			return true;
		}
		
		System.out.println("Los atributos inválidos son:");
		atributosInvalidos.forEach( v -> System.out.println(v) );
		return false;
	}

	@Override
	public float getPrecioBase() {
		if ( precio == null ) {
			throw new IllegalStateException("El precio no está definido para: " + this.getNombre());
		}
		return precio.floatValue();
	}
	
	public float getPrecioFinal() {
		if ( descuentoPromo == null ) {
			throw new IllegalStateException("El descuento no está definido para: " + this.getNombre());
		}
		return this.getPrecioBase() * ( 1 - ( descuentoPromo.floatValue() / 100 ) );
	}
	
	@Override
	public float getPeso() {
		
		if ( peso == null ) {
			throw new IllegalStateException("El peso no está definido para: " + this.getNombre());
		}
		return peso.floatValue();
	}

	public void addAtributoDinamico( String key, Object atributo ) {
		if (this.atributos.containsKey(key)) {
	        System.out.println("ya existe un atributo dinámico con ese nombre");
	    } else {
	    	this.atributos.put(key, atributo);
	    }
	}
	
	//Visitor Pattern
	@Override
	public void accept( ItemVisitor itemVisitor ) {
		itemVisitor.visit(this);
	}	
	
}




















