package reportes;

import java.time.LocalDate;
import java.util.List;

public class FormatoCSV implements FormatoExportador {

	@Override
	public String exportar(String titulo, LocalDate desde, LocalDate hasta, List<Entrada> entradas) {
		StringBuilder sb = new StringBuilder();

		sb.append(titulo + "\n");
        sb.append("Desde;" + desde + ";Hasta;" + hasta + "\n\n");
        
        sb.append("Producto/Paquete;Cantidad Vendida;Precio Promedio Cobrado\n");

        for (Entrada e : entradas) {
            sb.append(e.getItemCatalogo().getNombre() + ";")
              .append(e.getUnidadesVendidas() + ";")
              .append(String.format("%.2f", e.getPrecioPromedio()) + "\n");
        }
        return sb.toString();
    }

}













