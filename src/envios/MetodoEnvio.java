package envios;

import pedido.Pedido;

/*
 * Aplica patrón STRATEGY, MetodoEnvio es una interfaz común para todos 
 * los métodos de envío.
 *
 * Pedido no sabe qué método de envío tiene; cada estrategia concreta 
 * encapsula su propia lógica.
 */
public interface MetodoEnvio {

    float calcularCosto(Pedido pedido); // Calcula el costo de envío para el pedido dado.
    int estimarDias(Pedido pedido); // Estima los días hábiles hasta la entrega
}
