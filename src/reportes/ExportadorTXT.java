package reportes;

import java.time.LocalDate;
import java.util.List;

public class ExportadorTXT implements FormatoExportador{

	@Override
	public String exportar(String titulo, LocalDate desde, LocalDate hasta, List<Entrada> entradas) {

		StringBuilder sb = new StringBuilder();
        sb.append(titulo.toUpperCase() + "\n");
        sb.append("Periodo: " + desde + " al " + hasta + "\n");
        sb.append("------------------------------------------------------------\n");
        sb.append(String.format("%-25s %-15s %-15s\n", "Producto/Paquete", "Cant. Vendida", "Precio Promedio"));
        sb.append("------------------------------------------------------------\n");

        for (Entrada e : entradas) {
            sb.append(String.format("%-25s %-15d $%-15.2f\n", 
                e.getItemCatalogo().getNombre(), e.getUnidadesVendidas(), e.getPrecioPromedio()));
        }
        return sb.toString();
	}

}












