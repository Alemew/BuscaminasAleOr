package Modelo;

public class Tablero {

	private Casilla[][] casillas;
	
	public Tablero(Casilla[][] casillas) {
		super();
		this.casillas = casillas;
	}
	
	public Tablero(int dimension, int numeroBombas) {
		super();
		
		generarTablero(dimension);
		colocarBombas(dimension, numeroBombas);
		asignarBomAlr();
		
	}
	
	private void generarTablero(int dimension) {
		casillas = new Casilla[dimension][dimension];
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				casillas[i][j] = new Casilla();
			}
		}
	}
	
	private void colocarBombas(int dimension, int numeroBombas) {
		for (int i = 0; i < numeroBombas; i++) {
			int aleatorioX = (int) (Math.random() * dimension);
			int aleatorioY = (int) (Math.random() * dimension);
			if (casillas[aleatorioX][aleatorioY].isBomba()) {
				i--;
			}else {
				casillas[aleatorioX][aleatorioY].setBomba(true);
				casillas[aleatorioX][aleatorioY].setMinasAlrededor(10);
				
			}
		}

	}
	
	private void asignarBomAlr() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				if (!casillas[i][j].isBomba()) {
					casillas[i][j].setMinasAlrededor(compruebaBomAlr(new Coordenada(i, j)));
				}
			}
		}

	}
	
	private int compruebaBomAlr(Coordenada actual) {
		int numeroBomAlr = 0;
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				Coordenada posibleContigua = new Coordenada(i, j);
				if (IsCasillaContinua(actual, posibleContigua)) {
					if (casillas[i][j].isBomba()) {
						numeroBomAlr++;
					}
				}
			}
		}
		return numeroBomAlr;
	}
	
	private boolean IsCasillaContinua(Coordenada actual, Coordenada posibleContigua) {
		boolean continua = false;

		if (posibleContigua.getX() < actual.getX() + 2 && posibleContigua.getX() > actual.getX() - 2) {
			if (posibleContigua.getY() < actual.getY() + 2 && posibleContigua.getY() > actual.getY() - 2) {
				if (actual.isContigua(posibleContigua)) {
					continua = true;
				}
			}
		}
		return continua;
	}
	
	public void marcarCasilla(Coordenada coord) {
		this.casillas[coord.getX()][coord.getY()].marcar();
	}
	
	public boolean desvelaCasillas(Coordenada actual) {
		int iniX = actual.getX();
		int iniY = actual.getY();
		boolean bombasOcultas = true;
		if (!casillas[iniX][iniY].isMarcada()) {

			if (casillas[iniX][iniY].getMinasAlrededor() != 0) {
				bombasOcultas = desvelaCasillaUnica(actual);
				} 
			if (casillas[iniX][iniY].getMinasAlrededor() == 0) {
				desvelarVaciasyProximasRecursivo(iniX, iniY);
			}
		} else {
			if (contarMarcadasAlrededor(actual, casillas.length) >= casillas[iniX][iniY].getMinasAlrededor()) {
				bombasOcultas = desvelarCasillasContiguas(actual);
			}
		}
		return bombasOcultas;
	}

	private boolean desvelarCasillasContiguas(Coordenada inicial) {
		int iniX = inicial.getX();
		int iniY = inicial.getY();
		boolean bombasOcultas = true;

		for (int i = iniX - 1; i <= iniX + 1; i++) {
			for (int j = iniY - 1; j <= iniY + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				if (!alrededor.equals(inicial)) {
					if (isDentroLimites(alrededor, casillas.length) && !casillas[i][j].isMarcada()) {
						casillas[i][j].setVelada(false);
						if (casillas[i][j].isBomba()) {
							desvelaBombas();
							bombasOcultas = false;
						}

					}
				}
			}
		}
		return bombasOcultas;
	}

	private void desvelarVaciasyProximasRecursivo(int inicialX, int inicialY) {
		for (int i = inicialX - 1; i <= inicialX + 1; i++) {
			for (int j = inicialY - 1; j <= inicialY + 1; j++) {
				Coordenada posibleContinua = new Coordenada(i, j);
				if (isDentroLimites(posibleContinua, casillas.length) && casillas[i][j].isVelada()) {
					casillas[inicialX][inicialY].setVelada(false);
					desvelaCasillas(posibleContinua);
				}
			}
		}
	}

	private boolean desvelaCasillaUnica(Coordenada actual) {
		int iniX = actual.getX();
		int iniY = actual.getY();
		boolean bombasOcultas = true;

		casillas[iniX][iniY].setVelada(false);
		if (casillas[iniX][iniY].isBomba()) {
			desvelaBombas();
			bombasOcultas = false;
		}

		return bombasOcultas;
	}

	private void desvelaBombas() {
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas.length; j++) {
				if (casillas[i][j].isBomba() && !casillas[i][j].isMarcada()) {
					casillas[i][j].setVelada(false);
				}
			}
		}
	}

	private int contarMarcadasAlrededor(Coordenada posicion, int lado) {
		int marcadasAlrededor = 0;
		int x = posicion.getX();
		int y = posicion.getY();
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				Coordenada alrededor = new Coordenada(i, j);
				if (!alrededor.equals(posicion)) {
					if (isDentroLimites(alrededor, lado) && casillas[i][j].isMarcada()) {
						marcadasAlrededor++;
					}
				}
			}
		}
		return marcadasAlrededor;
	}

	public boolean tableroWin(Tablero tablero) {
		int desveladas = 0;
		int bombas = 0;
		int lado = getCasillas().length;
		int total = lado * lado;
		for (int i = 0; i < getCasillas().length; i++) {
			for (int j = 0; j < getCasillas().length; j++) {
				if (getCasillas()[i][j].isBomba()) {
					bombas++;
				} else {
					if (!getCasillas()[i][j].isVelada()) {

						desveladas++;
					}
				}
			}
		}
		total = total - bombas;
		return (desveladas == total);
	}
	
	
//	public Casilla getCasilla(Coordenada posicion) {
//		return casillas[posicion.getX()][posicion.getY()];
//	}
//	
//	public void desvelarCasilla(Coordenada coordenada) {
//		 getCasilla(coordenada).setVelada(false);
//	}
	
	private boolean isDentroLimites(Coordenada alrededor, int lado) {
		return alrededor.getX() >= 0 && alrededor.getX() < lado && alrededor.getY() >= 0 && alrededor.getY() < lado;
	}
	
	public Casilla[][] getCasillas() {
		return casillas;
	}

//	casillas[iniX][iniY].isVelada() &&
	
}
