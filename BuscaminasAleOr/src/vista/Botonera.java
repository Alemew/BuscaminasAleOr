package vista;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Control.DesveladorController;
import Control.MarcadorController;
import Modelo.Coordenada;

public class Botonera extends JPanel {
	private DesveladorController desveladorController;
	private MarcadorController marcarCasilla;

	private int lado;
	
	MouseAdapter miMouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			JButton boton = ((JButton) e.getSource());
			if (SwingUtilities.isLeftMouseButton(e) && boton.isEnabled()) {
				if (desveladorController.desvelarCasilla(boton.getName())) {
					if (desveladorController.comprobarFin()) {
						// TODO Informamos de la victoria
					desactivarBotones(desveladorController.getEntornoGrafico());
					}
				} else {
					// TODO informamos de perder
					desactivarBotones(desveladorController.getEntornoGrafico());

				}
			}
			if (SwingUtilities.isRightMouseButton(e) && boton.isEnabled()) {
				marcarCasilla.marcarCasilla(boton.getName());
			}
			actualizaBotonera(desveladorController.getEntornoGrafico());
		}
	};

	public Botonera(int lado, DesveladorController desvelador, MarcadorController marcarCasilla) {
		this.marcarCasilla = marcarCasilla;
		this.desveladorController = desvelador;
		this.lado = lado;
		setLayout(new GridLayout(lado, lado, 0, 0));
		for (int filas = 0; filas < lado; filas++) {
			for (int columnas = 0; columnas < lado; columnas++) {
				JButton boton = new JButton();
				String nombre = Integer.toString(filas) + ":" + Integer.toString(columnas);
				boton.setName(nombre);
				boton.setFont(new Font("Tahoma", Font.PLAIN, 25));
				add(boton);
				boton.addMouseListener(miMouseAdapter);
			}
		}
	}

	private void desactivarBotones(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			Coordenada coordenada = obtenCoordenada(boton.getName());
			ElementoGrafico elementoGrafico = elementos[coordenada.getX()][coordenada.getY()];
			boton.setEnabled(false);
		}
	}

	private void actualizaBotonera(ElementoGrafico[][] elementos) {
		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			JButton boton = (JButton) components[i];
			Coordenada coordenada = obtenCoordenada(boton.getName());
			ElementoGrafico elementoGrafico = elementos[coordenada.getX()][coordenada.getY()];
			if (!elementoGrafico.isOcultado()) {
				boton.setText(String.valueOf(elementoGrafico.getValor()));
			} else if (elementoGrafico.isSenalada()) {
				boton.setText("X");
			} else {
				boton.setText("");
			}
		}
	}

	public static Coordenada obtenCoordenada(String name) {
		int pos = name.indexOf(":");
		return new Coordenada(Integer.valueOf(name.substring(0, pos)),
				Integer.valueOf(name.substring(pos + 1, name.length())));
	}

}
