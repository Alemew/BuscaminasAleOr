package Modelo;

public class Casilla {

	private boolean velada = true;
	private boolean mina = false;
	private boolean marcada = false;
	private int minasAlrededor=0;
	              
	public Casilla() {
		super();
	}
	
	public boolean marcar() {
		if (marcada==false) {
			marcada = true;
		}else {
			marcada = false;
		}
		return marcada;
		
	}
	
	public void setVelada(boolean velada) {
		this.velada = velada;
	}
	public void setBomba(boolean bomba) {
		this.mina = bomba;
	}
	public boolean isMarcada() {
		return marcada;
	}
	public void setMarcada(boolean marcada) {
		this.marcada = marcada;
	}
	public boolean isVelada() {
		return velada;
	}
	public boolean isBomba() {
		return mina;
	}
	
	public int getMinasAlrededor() {
		return minasAlrededor;
	}
	public void setMinasAlrededor(int minasAlrededor) {
		this.minasAlrededor = minasAlrededor;
	}
	
//	@Override
//	public String toString() {
//		return String.valueOf(mina);
//	}

}
