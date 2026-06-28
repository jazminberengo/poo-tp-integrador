package envios;

public class EnvioExpress {

	private static double procentajeFijo = 0.05;
	private static float  cargoBase = 100;
	
	public static float calcularCosto( float precio ) {
		return (float) ( (precio * procentajeFijo) + cargoBase );
	}
}
