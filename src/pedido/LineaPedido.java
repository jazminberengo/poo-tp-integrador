package pedido;

import catalogo.ItemCatalogo;

/*
   Representa una línea dentro de un pedido: un ítem con su cantidad
   y el precio unitario al momento de la compra.
  
   Se guarda el precio acá porque si el precio del producto cambia
   después, el pedido no se ve afectado.
 */
public class LineaPedido {

    private final ItemCatalogo item;
    private final int cantidad;
    private final float precioUnitario; // precio al momento de agregar

    public LineaPedido(ItemCatalogo item, int cantidad) {
        this.item = item;
        this.cantidad = cantidad;
        this.precioUnitario = item.getPrecioBase(); // snapshot
    }

    public ItemCatalogo getItem() {
        return item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public float getSubtotal() {
        return precioUnitario * cantidad;
    }
}
