package catalogo;

import java.util.ArrayList;
import java.util.Map;
import reportes.ReporteVisitor;

public class Producto extends ItemCatalogo{

	public String sku;
	public String marca;
	public String categoria;
	public Float  precio;
	public Float  descuento;
	public Map<String, Object> atributos;
	
	
	
	@Override
	public void aceptar(ReporteVisitor visitor) {
			
		visitor.visitarProducto( this );
	}



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
}




















