package reportes;

import java.time.LocalDate;
import java.util.List;

public interface FormatoExportador {

	public String exportar( String titulo, LocalDate desde, LocalDate hasta, List<Entrada> entradas );
}
