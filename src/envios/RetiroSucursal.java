package envios;

import pedido.Pedido;
import pedido.LineaPedido;

public class RetiroSucursal implements MetodoEnvio {

    private final Sucursal sucursal;

    private static final int DIAS_CON_STOCK    = 0;
    private static final int DIAS_SIN_STOCK    = 3;

    public RetiroSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public float calcularCosto(Pedido pedido) {
        return 0f; // El costo de envío siempre es $0 para retiro en sucursal.
    }

    @Override
    public int estimarDias(Pedido pedido) {
        boolean todoConStock = pedido.getItems().stream()
                .map(LineaPedido::getItem)
                .allMatch(sucursal::tieneStock);

        return todoConStock ? DIAS_CON_STOCK : DIAS_SIN_STOCK; // Si hay stock, el envío es inmediato, sino son 3 días
    }

    public Sucursal getSucursal() { return sucursal; }
}
