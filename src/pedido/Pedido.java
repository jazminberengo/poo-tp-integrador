package pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
   Pedido es la clase que coordina el ciclo de vida del pedido.
  
   Aplica dos patrones:
     
   STATE: Pedido delega todas las operaciones (confirmar, cancelar, etc.)
   en el objeto 'estado' actual.
  
   OBSERVER: Pedido mantiene una lista de observadores y los notifica
   cada vez que cambia de estado, sin conocer qué hace cada uno.
*/

public class Pedido {

    private final String id;
    private final Cliente cliente;

    private final List<LineaPedido> items = new ArrayList<>();
    private final List<NotaCredito> notasCredito = new ArrayList<>();
    private final List<Observador> observadores = new ArrayList<>();

    private EstadoPedido estado;
    private MetodoEnvio metodoEnvio;
    private ProcesadorPago procesadorPago;

    public Pedido(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = new EstadoBorrador(); // todo pedido arranca en borrador
    }

    // Cada método delega en el estado actual.
    // Si la operación no es válida en ese estado, el estado lanza la excepción.

    public void agregarItem(ItemCatalogo item, int cantidad) {
        estado.agregarItem(this);           // valida que sea posible
        items.add(new LineaPedido(item, cantidad));
    }

    public void quitarItem(ItemCatalogo item) {
        estado.quitarItem(this);            // valida que sea posible
        items.removeIf(l -> l.getItem().equals(item));
    }

    public void confirmar() {
        estado.confirmar(this);
    }

    public void preparar() {
        estado.preparar(this);
    }

    public void enviar() {
        estado.enviar(this);
    }

    public void entregar() {
        estado.entregar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }
}