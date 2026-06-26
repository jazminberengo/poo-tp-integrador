package reportes;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import catalogo.ItemCatalogo;
import catalogo.Producto;
import pedido.LineaPedido;
import pedido.Pedido;

public class ReporteProductosMasVendidos {

	private LocalDate fechaDesde;
	
	private LocalDate fechaHasta;

	private Map<String, Entrada> entradas;
	
	public ReporteProductosMasVendidos( LocalDate fechaDesde , LocalDate fechaHasta ) {
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.entradas = new HashMap<String, Entrada>();
	}
	
	private List<LineaPedido> pedidosToLineaPedidos( List<Pedido> pedidos ){
		
		return pedidos.stream()
				.filter( p -> {
					LocalDate fecha = p.getFechaEntrega();
					return (  	!fecha.isBefore(fechaDesde) &&
								!fecha.isAfter(fechaHasta) 		);
				})
				.flatMap( p -> {
					return p.getLineaPedidos().stream();
				})
				.collect(Collectors.toList());
	}
	
	private List<Entrada> lineaPedidosToEntradas( List<LineaPedido> lineaPedidos ){
		
		  return lineaPedidos.stream()
			        .flatMap( l -> {
			            int cantidadLinea = l.getCantidad(); 
			            return l.getItem().getListEntrada(cantidadLinea).stream();       
			        })
	                .collect(Collectors.toList());
	}
	
	
	public void generar(List<Pedido> pedidos) {
	     
		List<LineaPedido> lineasTotales = this.pedidosToLineaPedidos(pedidos);
	    List<Entrada> todasLasEntradas = this.lineaPedidosToEntradas(lineasTotales);

	    todasLasEntradas.forEach(nuevaEntrada -> {
	        String clave = nuevaEntrada.producto.getNombre(); 
	        this.entradas.merge(clave, nuevaEntrada, (entradaExistente, entradaNueva) -> 
	            entradaExistente.AgregarDatos(entradaNueva)
	        );
	    });
	}
}


















