package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Tablero;
import utiles.Utiles;
import vista.Botonera;
import vista.UserInterface;

public class ParaUI extends UserInterface {
	Tablero tablero;
	DesveladorController desveladorController;
	MarcadorController marcarCasilla;

	public ParaUI() {

		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarJuego();

			}
		});
	}
	
	private void cargarJuego() {
		// Tenemos que crear una nueva botonera
		// Entonces es un evento de UI
		// Tambien hay que crear un nuevo tablero?
		// Entonces es un evento de informacion
		// Por lo tanto este evento va en el paraUI
		// meterBotonera
		// La parte del UI

		// La parte de los datos
		int lado = getDificultad().getLado();
		tablero = new Tablero(lado, Utiles.calculaMinas(lado, getDensidad().getPorcentaje()));
		desveladorController = new DesveladorController(tablero);
		marcarCasilla = new MarcadorController(tablero);
		Botonera botonera = new Botonera(lado, desveladorController, marcarCasilla);

		getHuecoBotonera().removeAll();
		getHuecoBotonera().add(botonera);
		// Obliga al redibujado del ui cuando hay cambio de tamano de la ventana
		pack();}
}
	