package Modelo;

public class Coordenada {

	private int x;
	private int y;

	public Coordenada(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isContigua(Coordenada coordenadaActual) {
		int x = Math.abs(this.getX() - coordenadaActual.getX());
		int y = Math.abs(this.getY() - coordenadaActual.getY());
		int distancia = 1;
		return x == distancia || y == distancia;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof Coordenada))
			return false;

		Coordenada coordenada = (Coordenada) obj;
		return this.getX() == coordenada.getX() && this.getY() == coordenada.getY();
	}
	
	
//	@Override
//	public boolean equals(Object obj) {
//		boolean retorno=false;
//		if(obj!=null) {
//			retorno=this==obj;
//			if(!retorno && obj instanceof Coordenada) {
//				Coordenada coordenada=(Coordenada)obj;
//				retorno=this.getX()==coordenada.getX()&&this.getY()==coordenada.getY();
//			}
//		}
//		return retorno;
//	}
	
	
}
