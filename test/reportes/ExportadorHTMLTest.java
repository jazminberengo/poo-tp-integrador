package reportes;

import catalogo.Producto;
import envios.Sucursal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportadorHTMLTest {

    @Test
    void exportarGeneraHtmlValidoConTituloTablaYFilas() {
        Sucursal sucursal = new Sucursal();
        Producto producto = new Producto("Teclado", "desc", sucursal, "SKU-2", 200f, 0.5f, "MarcaY");
        Entrada entrada = new Entrada(producto, 8, 199.999f);

        ExportadorHTML exportador = new ExportadorHTML();
        String html = exportador.exportar("Reporte HTML", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 28), List.of(entrada));

        assertTrue(html.startsWith("<html><body>"));
        assertTrue(html.endsWith("</body></html>"));
        assertTrue(html.contains("<h1>Reporte HTML</h1>"));
        assertTrue(html.contains("<p><b>Periodo:</b> 2024-02-01 a 2024-02-28</p>"));
        assertTrue(html.contains("<th>Producto/Paquete</th><th>Cant. Vendida</th><th>Precio Promedio</th>"));
        assertTrue(html.contains("<td>Teclado</td>"));
        assertTrue(html.contains("<td>8</td>"));
        assertTrue(html.contains("<td>$200.00</td>"));
    }

    @Test
    void exportarConListaVaciaGeneraTablaSinFilas() {
        ExportadorHTML exportador = new ExportadorHTML();
        String html = exportador.exportar("Vacio", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2), List.of());

        assertTrue(html.contains("<tbody></tbody>"));
    }

    @Test
    void exportarConVariasEntradasIncluyeUnaFilaPorCadaUna() {
        Sucursal sucursal = new Sucursal();
        Producto p1 = new Producto("Mouse", "desc", sucursal, "SKU-1", 100f, 0.2f, "MarcaX");
        Producto p2 = new Producto("Monitor", "desc", sucursal, "SKU-3", 300f, 4f, "MarcaZ");
        Entrada e1 = new Entrada(p1, 5, 100f);
        Entrada e2 = new Entrada(p2, 2, 300f);

        ExportadorHTML exportador = new ExportadorHTML();
        String html = exportador.exportar("Reporte", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 31), List.of(e1, e2));

        assertTrue(html.contains("<td>Mouse</td>"));
        assertTrue(html.contains("<td>Monitor</td>"));
    }
}
