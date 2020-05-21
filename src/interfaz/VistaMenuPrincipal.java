package interfaz;

import javax.swing.JFrame;
import javax.swing.JTextField;

import negocio.Calculo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VistaMenuPrincipal {

	public JFrame menuPP;
	private JTextField textFieldIngresarPrecioPorMts;
	private JTextField textFieldIngresarPorcenjeAumento;
	private JTextField textFieldIngresarAumentoProvincia;
	private JButton btnEnviar;
	private JLabel lblFondo;
	VistaMapa vistaMapa;
	static Double precioPormetro;
	static Double porcentajeExtra;
	static Double costoPorPasarProvincia;
	
	public VistaMenuPrincipal() {
		initialize();
	}
	
	private void initialize() {
		menuPP = new JFrame();
		menuPP.setBounds(800, 600, 800, 600);
		menuPP.setLocationRelativeTo(null);
		menuPP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPP.getContentPane().setLayout(null);

		inicializarTF_IngresarPrecioPorMetro();
		inicializarTF_IngresarPorcentaje();
		inicializarTF_IngresarIncrementoPorProvincia();
		inicializarBotonEnviar();
		limitarInputUsuario(textFieldIngresarPrecioPorMts);
		limitarInputUsuario(textFieldIngresarPorcenjeAumento);
		limitarInputUsuario(textFieldIngresarAumentoProvincia);
		
		asignarFondo();
	}
	
	private void inicializarTF_IngresarPrecioPorMetro() {
		textFieldIngresarPrecioPorMts = new JTextField();
		textFieldIngresarPrecioPorMts.setBounds(70,355, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarPrecioPorMts);
		textFieldIngresarPrecioPorMts.setColumns(10);
	}
	
	private void inicializarTF_IngresarPorcentaje() {
		textFieldIngresarPorcenjeAumento = new JTextField();
		textFieldIngresarPorcenjeAumento.setBounds(315, 355, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarPorcenjeAumento);
		textFieldIngresarPorcenjeAumento.setColumns(10);
	}
	
	private void inicializarTF_IngresarIncrementoPorProvincia() {
		textFieldIngresarAumentoProvincia = new JTextField();
		textFieldIngresarAumentoProvincia.setBounds(550, 355, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarAumentoProvincia);
		textFieldIngresarAumentoProvincia.setColumns(10);
	}

	private boolean estanLos3TexfieldActivados() {
		if (!textFieldIngresarPrecioPorMts.getText().isEmpty() && !textFieldIngresarPorcenjeAumento.getText().isEmpty()
				&& !textFieldIngresarAumentoProvincia.getText().isEmpty()) {
			return true;
		}
			return false;
	}
	
	private void inicializarBotonEnviar() {
		btnEnviar = new JButton("ENVIAR");

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estanLos3TexfieldActivados()) {
					precioPormetro=Double.parseDouble(textFieldIngresarPrecioPorMts.getText() );
					costoPorPasarProvincia=Double.parseDouble(textFieldIngresarAumentoProvincia.getText() );
					porcentajeExtra=Double.parseDouble(textFieldIngresarPorcenjeAumento.getText() );
					
					cambiarDeVentana();
				}
				else {
					JOptionPane.showMessageDialog(null, "INGRESE UN PRECIO PARA CONTINUAR");
				}
			}
		});
		btnEnviar.setBounds(355, 470, 105, 30);
		menuPP.getContentPane().add(btnEnviar);
	}
	private void limitarInputUsuario(JTextField tf) {
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				if (Calculo.noEsUnNumuero(e.getKeyChar())) {
					e.consume(); // ignorar el evento de teclado
				} else {
					btnEnviar.setEnabled(true);
					if (tf.getText().length() >= 7)
						e.consume();
				}
			}
		});
	}
	
	private void cambiarDeVentana(){
		vistaMapa = new VistaMapa();
		menuPP.setVisible(false);
		vistaMapa.ventana.setVisible(true);
		vistaMapa.ventana.setResizable(false);
	}
	
	private void asignarFondo(){
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 800, 600);
		menuPP.getContentPane().add(lblFondo);
		
		lblFondo.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/interfaz/HOME.png")));
	}

	
}
