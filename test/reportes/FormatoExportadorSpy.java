package reportes;

import java.time.LocalDate;
import java.util.List;

/**
 * Test double de FormatoExportador. Guarda los argumentos recibidos
 * en exportar() para poder verificarlos en los tests.
 */
public class FormatoExportadorSpy implements FormatoExportador {

    public String tituloRecibido;
    public LocalDate desdeRecibido;
    public LocalDate hastaRecibido;
    public List<Entrada> entradasRecibidas;

    @Override
    public String exportar(String titulo, LocalDate desde, LocalDate hasta, List<Entrada> entradas) {
        this.tituloRecibido = titulo;
        this.desdeRecibido = desde;
        this.hastaRecibido = hasta;
        this.entradasRecibidas = entradas;
        return "REPORTE_GENERADO";
    }
}
