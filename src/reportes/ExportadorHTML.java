package reportes;

import java.time.LocalDate;
import java.util.List;

public class ExportadorHTML implements FormatoExportador {

	@Override
	public String exportar(String titulo, LocalDate desde, LocalDate hasta, List<Entrada> entradas) {
		
		StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h1>" + titulo + "</h1>");
        sb.append("<p><b>Periodo:</b> " + desde + " a " + hasta + "</p>");
        sb.append("<table border='1'><thead><tr>");
        sb.append("<th>Producto/Paquete</th><th>Cant. Vendida</th><th>Precio Promedio</th>");
        sb.append("</tr></thead><tbody>");

        for (Entrada e : entradas) {
            sb.append("<tr>")
              .append("<td>" + e.getItemCatalogo().getNombre() + "</td>")
              .append("<td>" + e.getUnidadesVendidas() + "</td>")
              .append("<td>$" + String.format("%.2f", e.getPrecioPromedio()) + "</td>")
              .append("</tr>");
        }

        sb.append("</tbody></table></body></html>");
        return sb.toString();
	}

}












