package envios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DireccionTest {

    @Test
    void constructorYGettersDevuelvenLosValoresCargados() {
        Direccion direccion = new Direccion("Av Siempreviva", "742", "Springfield", "BsAs", "1900", 42.5f);

        assertEquals(42.5f, direccion.getDistanciaKm());
        assertEquals("1900", direccion.getCodigoPostal());
    }
}
