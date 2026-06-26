package catalogo;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import reportes.Entrada;
import reportes.ReporteVisitor;

public class Producto extends ItemCatalogo{

	public String sku;
	public String marca;
	public String categoria;
	public Float  precio;
	public Float  descuento;
	public Float  peso;
	public Map<String, Object> atributos;
	
	public Producto(String nombre, String descripcion, 
					String sku, Float precio, Float peso ) {
        super(nombre, descripcion);
        this.sku = sku;
        this.precio = precio;
        this.peso = peso;
        this.atributos = new HashMap<>(); 
    }
	
	public void setMarca( String marca ) {	this.marca = marca; }
	public void setCategoria( String categoria ) { this.categoria = categoria; }
	public void setDescuento( Float descuento ) { this.descuento = descuento; }
	
	@Override
	public boolean validar() {	
		ArrayList<String> atributosInvalidos = new ArrayList<String>();
		
		if ( nombre == null ) 		{	atributosInvalidos.add("nombre"); }
		if ( descripcion == null ) 	{ 	atributosInvalidos.add("descripcion"); }
		if ( sku == null )			{	atributosInvalidos.add("sku"); }
		if ( marca == null ) 		{	atributosInvalidos.add("marca"); }
		if ( precio == null )		{ 	atributosInvalidos.add("precio"); }
		if ( descuento == null )	{ 	atributosInvalidos.add("descuento"); }
		
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
			throw new IllegalStateException("El precio no está definido para: " + nombre);
		}
		return precio.floatValue();
	}
	
	public float getPrecioFinal() {
		if ( descuento == null ) {
			throw new IllegalStateException("El descuento no está definido para: " + nombre);
		}
		return this.getPrecioBase() * ( 1 - ( descuento.floatValue() / 100 ) );
	}
	
	@Override
	public float getPeso() {
		
		if ( peso == null ) {
			throw new IllegalStateException("El peso no está definido para: " + nombre);
		}
		return peso.floatValue();
	}
	
	public List<Entrada> getListEntrada(int cantidadLineaPedido) {
		
		List<Entrada> entrada =  new ArrayList<Entrada>();
		entrada.add(new Entrada(this, cantidadLineaPedido, getPrecioFinal()));
		return entrada;
	}
	
	public String getNombre() {
		if ( nombre == null ) {
			throw new IllegalStateException("El nombre no está definido");
		}
		return nombre;
	}
	
	//Visitor Pattern
	@Override
	public void aceptar(ReporteVisitor visitor) {
		visitor.visitarProducto( this );
	}
}




















