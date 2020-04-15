package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.Casilla;
import Modelo.Coordenada;
import Modelo.Tablero;
import utiles.Utiles;

public class ColocaBomAlrTest {

	private int lado;
	private int porcentaje;
	private int minas;
	private Tablero tablero;

	@BeforeEach
	void setUp() {
		lado = 4;
		porcentaje = 10;
		minas = Utiles.calculaMinas(lado, porcentaje);
		tablero = new Tablero(lado, minas);
	}

	@Test
	void test() {
		for (int i = 0; i < lado; i++) {
			for (int j = 0; j < lado; j++) {
				Coordenada posicion = new Coordenada(i, j);
				Casilla actual = tablero.getCasillas()[i][j];
				int BA = actual.getMinasAlrededor();
				assertEquals(BA, contarMinasAlrededor(tablero, posicion));
			}
		}
	}

	// Deberia probar este codigo
	private byte contarMinasAlrededor(Tablero tablero, Coordenada posicion) {
		byte bombasAlrededor = 0;
		int x = posicion.getX();
		int y = posicion.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				// No tengo en cuenta la posicion que estoy comprobando
				if (!alrededor.equals(posicion)) {
					if (isDentroLimites(alrededor, lado) && tablero.getCasillas()[i][j].isBomba()) {
						bombasAlrededor++;
					}
				}
			}
		}
		return bombasAlrededor;
	}

	private boolean isDentroLimites(Coordenada alrededor, int lado) {
		return alrededor.getX() >= 0 && alrededor.getX() < lado && alrededor.getY() >= 0 && alrededor.getY() < lado;
	}
	
}
