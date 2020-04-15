package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Control.MarcadorController;
import Modelo.Casilla;
import Modelo.Coordenada;
import Modelo.Tablero;


class hola {

	@Test
	void marcarCasillaTest() {
		// Creamos el objetivo a cumplir
		// una casilla marcada
		Coordenada bases = new Coordenada(0, 0);
		Casilla base = new Casilla();
		base.marcar();

		byte medidas = 5;
		byte nBombas = 10;
		// tablero a modificar
		Tablero prueba = new Tablero(medidas, nBombas);
		// Futura función "generaTablero"
		for (int i = 0; i < prueba.getCasillas().length; i++) {
			for (int j = 0; j < prueba.getCasillas().length; j++) {
				Coordenada colocacion = new Coordenada(i, j);
				prueba.getCasillas()[i][j] = new Casilla();
			}
		}
		MarcadorController test = new MarcadorController(prueba);
		// marcamos con nuestro metodo cualquier casilla del tablero
		
//		Coordenada prueba1 = new Coordenada(0, 0);
//		test.marcarCasilla(prueba1);
//		Coordenada prueba2 = new Coordenada(4, 4);
//		test.marcarCasilla(prueba2);
//		Coordenada prueba3 = new Coordenada(2, 3);
//		test.marcarCasilla(prueba3);
		
		// comprobamos si las dos estan marcadas correctamente
		assertEquals(base.isMarcada(), prueba.getCasillas()[0][0].isMarcada());
		assertEquals(base.isMarcada(), prueba.getCasillas()[4][4].isMarcada());
		assertEquals(base.isMarcada(), prueba.getCasillas()[2][3].isMarcada());
		// si ponemos una que no esta marcada...
//		assertEquals(base.isMarcada(), prueba.getTablero()[3][3].isMarcada());

	}
}
