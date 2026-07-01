package reportes;

import catalogo.Paquete;
import catalogo.Producto;

import java.util.ArrayList;
import java.util.List;


public class ReporteVisitorSpy extends ReporteVisitor {

    public final List<Object> itemsVisitados = new ArrayList<>();
    public final List<Integer> cantidadesRegistradas = new ArrayList<>();
    public final List<Float> preciosRegistrados = new ArrayList<>();

    @Override
    public void visit(Producto producto) {
        itemsVisitados.add(producto);
        registrarVentaActual();
    }

    @Override
    public void visit(Paquete paquete) {
        itemsVisitados.add(paquete);
        registrarVentaActual();
    }

    private void registrarVentaActual() {
        cantidadesRegistradas.add(cantidadActual);
        preciosRegistrados.add(precioCobradoActual);
    }

    @Override
    public List<Entrada> getFinishedList() {
        return new ArrayList<>();
    }

    @Override
    public String titulo() {
        return "Reporte de prueba";
    }
}
