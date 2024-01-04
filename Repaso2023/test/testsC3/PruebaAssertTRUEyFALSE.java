package testsC3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PruebaAssertTRUEyFALSE {
	/*
	 * Vamos a probar los métodos de assertTrue y assertFalse
	 * mediante una función muy sencilla, la de comprobar
	 * si un número es par o no, devolviendo true o false.
	 */
	private Calculator calculadora = new Calculator();
	
	@Test
	public void testEsNumeroPar() {
		// Devolver true si es verdad que el número metido (el 2) es par
		assertTrue(calculadora.esNumeroPar(2)); // Tendría que devolver TRUE
		// Devolver true si es verdad que el núméro metido (el 1) no es par
		assertFalse(calculadora.esNumeroPar(1)); // Tendría que devolver FALSE
	}
}

class Calculator {
	public boolean esNumeroPar(int numero) {
		return numero % 2 == 0;
	}
}