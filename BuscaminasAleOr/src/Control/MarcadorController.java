package Control;

import Modelo.Coordenada;
import Modelo.Tablero;
import vista.Botonera;

public class MarcadorController {

	private Tablero tablero;

	public MarcadorController(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public void marcarCasilla(String name) {
		Coordenada obtenCoordenada = Botonera.obtenCoordenada(name);
		tablero.marcarCasilla(obtenCoordenada);
	}
}