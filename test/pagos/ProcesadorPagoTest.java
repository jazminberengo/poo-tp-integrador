package pagos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ProcesadorPagoTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	
	// Si se modifica ProcesadorPago y cambia el orden, el test falla inmediatamente
	// Template Method: La superclase define el algoritmo, las subclases solo 
	// implementan algunos pasos
	@Test
	void deberiaEjecutarLosPasosEnElOrdenCorrecto() {
		ProcesadorPagoDummy procesador = new ProcesadorPagoDummy();
		
		procesador.procesarPago();
		
		assertEquals(
				List.of("validar", "reservar", "ejecutar", "notificar"),
				procesador.getPasos()
		);
	}

	private static class ProcesadorPagoDummy extends ProcesadorPago{
		private List<String> pasos = new ArrayList<>();

		@Override
		protected void validarDatos() {
			pasos.add("validar");
		}

		@Override
		protected void reservarFondos() {
			pasos.add("reservar");
		}

		@Override
		protected void ejecutarTransaccion() {
			pasos.add("ejecutar");
		}
		
		@Override
		protected void notificarResultado() {
			pasos.add("notificar");
		}
		
		public List<String> getPasos() {
			return pasos;
		}
	}
	
}
