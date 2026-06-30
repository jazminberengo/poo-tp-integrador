package envios;

public class CorreoArgentino {
	
	/**
    * Método estático requerido por el sistema para el cálculo de envío estándar.
    * @param peso El peso total de los ítems del pedido en kilogramos.
    * @param direccionEnvio La dirección de destino del pedido.
    * @return El costo estimado del envío.
    */
    public static float estimarCostoEnvio(float peso, Direccion direccionEnvio) {
        // Lógica de simulación basada en los criterios del dominio:
        // Se considera un costo base, un plus por peso y un plus por distancia.
        float costoBase = 800.0f;
        float factorPeso = 120.5f;
        float factorDistancia = 15.0f;

        float costoFinal = costoBase + (peso * factorPeso) + (direccionEnvio.getDistanciaKm() * factorDistancia);

        return costoFinal;
    }
 
    public static int estimarDiasEnvio(Direccion direccion) {
        // Obtenemos la distancia de la dirección de destino
        float distancia = direccion.getDistanciaKm();

        // Lógica para variar entre el rango de 5 a 7 días
        if (distancia < 100) {
            return 5; // Cerca: mínimo de días
        } else if (distancia < 300) {
            return 6; // Distancia media
        } else {
            return 7; // Lejos: máximo de días
        }
    }
}
