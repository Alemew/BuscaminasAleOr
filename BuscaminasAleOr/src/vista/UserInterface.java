package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Densidad;
import Modelo.Dificultad;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class UserInterface extends JFrame {

	protected JPanel contentPane;
	protected JMenuItem mntmFacil;
	protected JMenuItem mntmMedio;
	protected JMenuItem mntmHardcore;
	protected JMenu mnJugar;
	protected JMenuBar menuBar;
	protected JPanel panel;
	protected JLabel lblFin;
	protected JPanel panelInformativo;
	protected JButton btnReiniciar;
	protected JLabel lblSonido;
	private JLabel lblDensidad;
	private JLabel lblDificultad;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	Dificultad dificultad = Dificultad.pequeno;
	Densidad densidad = Densidad.facil;
	private JPanel huecoBotonera;
	private JLabel WinLose;

	/**
	 * Create the frame.
	 */
	public UserInterface() {
		setTitle("Busca Minas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 721);
		setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnJugar = new JMenu("Dificultad");
		menuBar.add(mnJugar);

		mntmFacil = new JMenuItem("Facil");
		mntmFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaDificultad(Dificultad.pequeno);
			}
		});
		mnJugar.add(mntmFacil);

		mntmMedio = new JMenuItem("Medio");
		mntmMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaDificultad(Dificultad.medio);
			}
		});
		mnJugar.add(mntmMedio);

		mntmHardcore = new JMenuItem("Hardcore");
		mntmHardcore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaDificultad(Dificultad.grande);
			}
		});
		mnJugar.add(mntmHardcore);

		mnNewMenu = new JMenu("Densidad");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Facil");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaDensidad(Densidad.facil);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("Dificil");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizaDensidad(Densidad.dificil);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		lblSonido = new JLabel();
		menuBar.add(lblSonido);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setFocusable(false);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panelInformativo = new JPanel();
		panel.add(panelInformativo);
		
		WinLose = new JLabel("");
		panelInformativo.add(WinLose);
		WinLose.setSize(new Dimension(100, 100));

		JLabel lblNMinas = new JLabel("Densidad");
		panelInformativo.add(lblNMinas);

		lblFin = new JLabel("");
		panelInformativo.add(lblFin);
		lblFin.setForeground(Color.RED);
		lblFin.setFont(new Font("Viner Hand ITC", Font.BOLD, 24));

		lblDensidad = new JLabel(" ");
		panelInformativo.add(lblDensidad);

		JLabel lblDifucultad = new JLabel("Dificultad");
		panelInformativo.add(lblDifucultad);

		lblDificultad = new JLabel("");
		panelInformativo.add(lblDificultad);

		btnReiniciar = new JButton("Reiniciar");

		panelInformativo.add(btnReiniciar);

		huecoBotonera = new JPanel();
		huecoBotonera.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(huecoBotonera, BorderLayout.CENTER);
		huecoBotonera.setLayout(new BoxLayout(huecoBotonera, BoxLayout.X_AXIS));

		// Escribo la configuracion por defecto
		actualizaDensidad(getDensidad());
		actualizaDificultad(getDificultad());
	}

	public JLabel getWinLose() {
		return WinLose;
	}

	public JPanel getHuecoBotonera() {
		return huecoBotonera;
	}

	protected void actualizaDensidad(Densidad actual) {
		densidad = actual;
		lblDensidad.setText(String.valueOf(getDensidad().getPorcentaje()));
	}

	private void actualizaDificultad(Dificultad actual) {
		dificultad = actual;
		lblDificultad.setText(String.valueOf(getDificultad().getLado()));
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public Densidad getDensidad() {
		return densidad;
	}

}