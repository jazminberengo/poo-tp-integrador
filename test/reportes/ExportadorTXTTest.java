package reportes;

import catalogo.Producto;
import envios.Sucursal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportadorTXTTest {

    @Test
    void exportarGeneraTextoConTituloEnMayusculasPeriodoYFilaFormateada() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Monitor", "desc", sucursal, "SKU-3", 300f, 4f, "MarcaZ");
        Entrada entrada = new Entrada(producto, 12, 275.5f);

        ExportadorTXT exportador = new ExportadorTXT();
        String txt = exportador.exportar("reporte mensual", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 31), List.of(entrada));

        assertTrue(txt.startsWith("REPORTE MENSUAL\n"), "El título debe pasarse a mayúsculas");
        assertTrue(txt.contains("Periodo: 2024-03-01 al 2024-03-31\n"));
        assertTrue(txt.contains("Producto/Paquete"));
        assertTrue(txt.contains("Cant. Vendida"));
        assertTrue(txt.contains("Precio Promedio"));
        assertTrue(txt.contains("Monitor"));
        assertTrue(txt.contains("12"));
        assertTrue(txt.contains("275.50"));
    }

    @Test
    void exportarConListaVaciaNoAgregaFilasDeDatos() {
        ExportadorTXT exportador = new ExportadorTXT();
        String txt = exportador.exportar("vacio", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2), List.of());

        long lineasSeparadoras = txt.lines().filter(l -> l.startsWith("---")).count();
        assertEquals(2, lineasSeparadoras, "Debe haber solo las 2 líneas separadoras, sin filas de datos");
    }
}
