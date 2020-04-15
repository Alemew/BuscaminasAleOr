package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Casilla;
import Modelo.Coordenada;
import Modelo.Tablero;

class DesvelaRecursivo {

	@Test
	void test() {
		Casilla uno = new Casilla();
		uno.setMinasAlrededor( 1);
		Casilla cero = new Casilla();
		cero.setMinasAlrededor(0);;
		Casilla[][] base = {{cero,cero,cero,cero,cero},
							{uno,uno,cero,  uno,   uno},
							{cero,uno,cero, uno,  cero},
							{uno,uno,cero,  uno,   uno},
							{cero,cero,cero,cero,cero},
							
		};
		Casilla unoD = new Casilla();
		unoD.setMinasAlrededor( 1);
		unoD.setVelada(false);;
		Casilla ceroD = new Casilla();
		ceroD.setMinasAlrededor(0);
		ceroD.setVelada(false);;
		Casilla[][] resultado = {{ceroD,ceroD,ceroD,ceroD,ceroD},
								{unoD,unoD,ceroD,  unoD,   unoD},
								{cero,unoD,ceroD, unoD,  cero},
								{unoD,unoD,ceroD,  unoD,   unoD},
								{ceroD,ceroD,ceroD,ceroD,ceroD},
								
};
		Tablero base1 = new Tablero(base);
		Tablero resulta = new Tablero(resultado);
		
		base1.desvelaCasillas(new Coordenada(2, 2));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				assertTrue(base1.getCasillas()[i][j].isVelada()==resulta.getCasillas()[i][j].isVelada());
			}
		}
	}

}
