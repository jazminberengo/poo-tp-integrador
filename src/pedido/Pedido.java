package pedido;

import java.util.ArrayList;
import catalogo.ItemCatalogo;
import envio.MetodoEnvio;
import notificaciones.Observador;
import pagos.ProcesadorPago;
import pedido.EstadoBorrador;
import pedido.EstadoPedido;

import java.time.LocalDate;
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
    
    private final LocalDate fechaCreacion;
    private LocalDate fechaConfirmacion;
    private LocalDate fechaEntrega;
    private LocalDate fechaCancelacion;

    public Pedido(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = new EstadoBorrador(); // todo pedido arranca en borrador
        this.fechaCreacion = LocalDate.now();
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
    
    // Los estados concretos llaman a los siguientes métodos para ejecutar la lógica
    // de dominio (stock, reembolso) antes de cambiar de estado.

    public void cambiarEstado(EstadoPedido nuevoEstado) { // único método donde el estado cambia
        EstadoPedido anterior = this.estado;
        this.estado = nuevoEstado;
        notificar(anterior);
    }

    // Decrementa el stock de todos los ítems del pedido.
    public void decrementarStock() {
        // El stock real vive en el catálogo/depósito.
        // Acá se delega al ítem para que maneje su propio stock.
        for (LineaPedido linea : items) {
            linea.getItem().decrementarStock(linea.getCantidad());
        }
    }

    // Repone el stock de todos los ítems (usado al cancelar desde CONFIRMADO).
    public void reponerStock() {
        for (LineaPedido linea : items) {
            linea.getItem().incrementarStock(linea.getCantidad());
        }
    }

    // Genera y registra una nota de crédito por el monto indicado.
    public void generarNotaCredito(float monto) {
        notasCredito.add(new NotaCredito(monto, "Cancelación de pedido " + id));
    }

    // Cálculos de totales

    // Suma de todos los ítems sin costo de envío. 
    public float getSubtotalProductos() {
        return (float) items.stream()
                .mapToDouble(LineaPedido::getSubtotal)
                .sum();
    }

    // Total completo: productos + envío.
    public float getTotal() {
        return getSubtotalProductos() + getCostoEnvio();
    }

    // Peso total para el cálculo de envío estándar.
    public float getPesoTotal() {
        return (float) items.stream()
                .mapToDouble(l -> {
                    Object peso = l.getItem().getPeso();
                    return (peso instanceof Number) ? ((Number) peso).floatValue() * l.getCantidad() : 0;
                })
                .sum();
    }

    // Observer: suscripción y notificación

    public void suscribir(Observador observador) {
        observadores.add(observador);
    }

    public void desuscribir(Observador observador) {
        observadores.remove(observador);
    }

    /*
       Notifica a todos los observadores del cambio de estado.
       Pedido no sabe qué hace cada observador, solo les avisa.
     */
    public void notificar(EstadoPedido estadoAnterior) {
        for (Observador o : observadores) {
            o.actualizar(this, estadoAnterior, this.estado);
        }
    }
    
    // Setters de fechas (llamados por los estados)
    
    public void registrarFechaConfirmacion() { this.fechaConfirmacion = LocalDate.now(); }
    public void registrarFechaEntrega()      { this.fechaEntrega = LocalDate.now(); }
    public void registrarFechaCancelacion()  { this.fechaCancelacion = LocalDate.now(); }
    
    // Getters

    public String getId() { return id; }

    public Cliente getCliente() { return cliente; }

    public EstadoPedido getEstado() { return estado; }

    public List<LineaPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public List<NotaCredito> getNotasCredito() {
        return Collections.unmodifiableList(notasCredito);
    }

    public MetodoEnvio getMetodoEnvio() { return metodoEnvio; }
    
    public LocalDate getFechaEntrega() { return fechaEntrega; }

    public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    public ProcesadorPago getProcesadorPago() { return procesadorPago; }

    public void setProcesadorPago(ProcesadorPago procesadorPago) {
        this.procesadorPago = procesadorPago;
    }
}
