package reportes;

import catalogo.Producto;
import envios.Sucursal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportadorCSVTest {

    @Test
    void exportarGeneraElCsvConTituloPeriodoEncabezadoYFilas() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Entrada entrada = new Entrada(producto, 5, 123.456f);

        ExportadorCSV exportador = new ExportadorCSV();
        String csv = exportador.exportar("Reporte X", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31), List.of(entrada));

        assertTrue(csv.startsWith("Reporte X\n"));
        assertTrue(csv.contains("Desde;2024-01-01;Hasta;2024-01-31\n\n"));
        assertTrue(csv.contains("Producto/Paquete;Cantidad Vendida;Precio Promedio Cobrado\n"));
        assertTrue(csv.contains("Mouse;5;123.46\n"));
    }

    @Test
    void exportarConListaVaciaSoloDevuelveTituloYEncabezados() {
        ExportadorCSV exportador = new ExportadorCSV();
        String csv = exportador.exportar("Reporte Vacio", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31), List.of());

        assertTrue(csv.contains("Reporte Vacio\n"));
        assertTrue(csv.contains("Producto/Paquete;Cantidad Vendida;Precio Promedio Cobrado\n"));
        assertTrue(csv.trim().endsWith("Cobrado"), "No deben agregarse filas de datos si la lista está vacía");
    }

    @Test
    void exportarConVariasEntradasIncluyeUnaFilaPorCadaUna() {
        Sucursal sucursal = new Sucursal();
        Producto p1 = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Producto p2 = new Producto("Teclado", "desc", sucursal, "SKU-2", 200f, 0.5f, "MarcaY");
        Entrada e1 = new Entrada(p1, 5, 100f);
        Entrada e2 = new Entrada(p2, 3, 200f);

        ExportadorCSV exportador = new ExportadorCSV();
        String csv = exportador.exportar("Reporte", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31), List.of(e1, e2));

        assertTrue(csv.contains("Mouse;5;100.00\n"));
        assertTrue(csv.contains("Teclado;3;200.00\n"));
    }
}
