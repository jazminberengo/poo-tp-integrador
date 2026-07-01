package reportes;

import catalogo.Producto;
import envios.Sucursal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntradaTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        Sucursal sucursal = new Sucursal();
        producto = new Producto("Teclado", "Teclado mecanico", sucursal,
                "SKU-002", 5000f, 0.9f, "Redragon");
    }

    @Test
    void constructorYGettersDevuelvenLosValoresCargados() {
        Entrada entrada = new Entrada(producto, 10, 100f);

        assertEquals(producto, entrada.getItemCatalogo());
        assertEquals(10, entrada.getUnidadesVendidas());
        assertEquals(100f, entrada.getPrecioPromedio());
    }

    @Test
    void setUnidadesVendidasActualizaElValor() {
        Entrada entrada = new Entrada(producto, 10, 100f);

        entrada.setUnidadesVendidas(25);

        assertEquals(25, entrada.getUnidadesVendidas());
    }

    @Test
    void agregarDatosCalculaElPromedioPonderadoYSumaUnidades() {
        Entrada entradaA = new Entrada(producto, 10, 100f); // 10 u a $100
        Entrada entradaB = new Entrada(producto, 5, 130f);  // 5 u a $130

        Entrada resultado = entradaA.AgregarDatos(entradaB);

        // (10*100 + 5*130) / 15 = 1650/15 = 110
        assertEquals(15, resultado.getUnidadesVendidas());
        assertEquals(110f, resultado.getPrecioPromedio(), 0.0001f);
    }

    @Test
    void agregarDatosDevuelveLaMismaInstanciaThisParaUsarseConMerge() {
        Entrada entradaA = new Entrada(producto, 10, 100f);
        Entrada entradaB = new Entrada(producto, 5, 130f);

        Entrada resultado = entradaA.AgregarDatos(entradaB);

        assertSame(entradaA, resultado);
    }

    @Test
    void agregarDatosConTotalCeroNoRecalculaElPrecioPromedio() {
        Entrada entradaA = new Entrada(producto, 0, 50f);
        Entrada entradaB = new Entrada(producto, 0, 999f);

        Entrada resultado = entradaA.AgregarDatos(entradaB);

        assertEquals(0, resultado.getUnidadesVendidas());
        // Como totalUnidades no es > 0, el precioPromedio original queda igual
        assertEquals(50f, resultado.getPrecioPromedio(), 0.0001f);
    }
}
