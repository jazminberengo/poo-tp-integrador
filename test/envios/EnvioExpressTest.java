package envios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvioExpressTest {

    @Test
    void calcularCostoAplicaPorcentajeFijoMasCargoBase() {
        float costo = EnvioExpress.calcularCosto(1000f);

        // (1000 * 0.05) + 100 = 50 + 100 = 150
        assertEquals(150f, costo, 0.001f);
    }

    @Test
    void calcularCostoConPrecioCeroDevuelveSoloElCargoBase() {
        float costo = EnvioExpress.calcularCosto(0f);

        assertEquals(100f, costo, 0.001f);
    }
}
