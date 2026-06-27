package reportes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import pedido.LineaPedido;
import pedido.Pedido;

public class GeneradorReportes {

	
	public String generarReporte( 	List<Pedido> pedidos, 
									LocalDate fechaDesde, 	
									LocalDate fechaHasta,
									ReporteVisitor reporteVisitor,
									FormatoExportador formatoExportador	 ) {
		
		List<LineaPedido> lineaPedidos = this.pedidosToLineaPedidos(pedidos, fechaDesde, fechaHasta);
		
		lineaPedidos.stream()
					.forEach( l -> {
						reporteVisitor.setVentaActual( l.getCantidad(), l.getPrecioUnitario());
						l.getItem().accept( reporteVisitor );
					});
		
		return formatoExportador.exportar( 	reporteVisitor.titulo(),
											fechaDesde, fechaHasta,
											reporteVisitor.getFinishedList() );
	}
	
	private List<LineaPedido> pedidosToLineaPedidos(	List<Pedido> pedidos, 
														LocalDate fechaDesde, 
														LocalDate fechaHasta ){
		// Devuelve la lista LineaPedido de todos los pedidos dados
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
}



















