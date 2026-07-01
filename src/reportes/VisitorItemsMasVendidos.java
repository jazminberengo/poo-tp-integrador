package reportes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import catalogo.Paquete;
import catalogo.Producto;

public class VisitorItemsMasVendidos extends ReporteVisitor {

	private Map<String, Entrada> entradas = new HashMap<String, Entrada>();
	
	@Override
	public void visit(Producto producto) {
		String clave = producto.getNombre(); 
		
		Entrada nuevaEntrada = new Entrada(producto, cantidadActual, precioCobradoActual);
		
        this.entradas.merge(clave, nuevaEntrada, 
        					(entradaExistente, entradaNueva) -> 
            				entradaExistente.AgregarDatos(entradaNueva)	);
	}

	@Override
	public void visit(Paquete paquete) {
		String clave = paquete.getNombre(); 
		
		Entrada nuevaEntrada = new Entrada(paquete, cantidadActual, precioCobradoActual);
		
        this.entradas.merge(clave, nuevaEntrada, 
        					(entradaExistente, entradaNueva) -> 
            				entradaExistente.AgregarDatos(entradaNueva)	);		
	}
	
	public List<Entrada> getFinishedList() {
	    return this.entradas.values().stream()
            // Comparamos por cantidad de mayor a menor
            .sorted((e1, e2) -> Integer.compare(e2.getUnidadesVendidas(), e1.getUnidadesVendidas()))
            .collect(Collectors.toList()) ;
	}

	public String titulo() {
		return "Reporte de Productos Más Vendidos";
	}
}










