
package Control;

import Modelo.Densidad;
import Modelo.Dificultad;
import Modelo.Tablero;
import utiles.Utiles;

public class IniciadorController {
	
	public Tablero iniciarJuego(Densidad densidad,Dificultad dificultad) {
		return new Tablero(dificultad.getLado(), Utiles.calculaMinas(dificultad.getLado(), densidad.getPorcentaje()));
	}

}
