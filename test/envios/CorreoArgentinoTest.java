package envios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorreoArgentinoTest {

    @Test
    void estimarCostoEnvioCalculaCostoBaseMasPesoMasDistancia() {
        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 20f);

        float costo = CorreoArgentino.estimarCostoEnvio(10f, direccion);

        // 800 + (10 * 120.5) + (20 * 15) = 800 + 1205 + 300 = 2305
        assertEquals(2305f, costo, 0.001f);
    }

    @Test
    void estimarCostoEnvioConPesoYDistanciaCeroDevuelveSoloElCostoBase() {
        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 0f);

        float costo = CorreoArgentino.estimarCostoEnvio(0f, direccion);

        assertEquals(800f, costo, 0.001f);
    }

    @Test
    void estimarDiasEnvioDevuelveCincoParaDistanciaMenorA100() {
        Direccion direccion = new Direccion("C", "1", "CABA", "BsAs", "1000", 99.99f);

        assertEquals(5, CorreoArgentino.estimarDiasEnvio(direccion));
    }

    @Test
    void estimarDiasEnvioDevuelveSeisParaDistanciaEntre100Y300() {
        Direccion limiteInferior = new Direccion("C", "1", "CABA", "BsAs", "1000", 100f);
        Direccion limiteSuperior = new Direccion("C", "1", "CABA", "BsAs", "1000", 299.99f);

        assertEquals(6, CorreoArgentino.estimarDiasEnvio(limiteInferior));
        assertEquals(6, CorreoArgentino.estimarDiasEnvio(limiteSuperior));
    }

    @Test
    void estimarDiasEnvioDevuelveSieteParaDistanciaDe300OMas() {
        Direccion limite = new Direccion("C", "1", "CABA", "BsAs", "1000", 300f);
        Direccion lejos = new Direccion("C", "1", "CABA", "BsAs", "1000", 1000f);

        assertEquals(7, CorreoArgentino.estimarDiasEnvio(limite));
        assertEquals(7, CorreoArgentino.estimarDiasEnvio(lejos));
    }
}
