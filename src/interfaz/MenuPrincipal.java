package interfaz;

import javax.swing.JFrame;
import javax.swing.JTextField;

import negocio.Calculos;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuPrincipal {

	public JFrame menuPP;
	private JTextField textFieldIngresarPrecio;
	private JButton btnEnviar;
	private JLabel lblFondo;
	VistaMapa vistaMapa;
	static Double precioPormetro;
	
	public MenuPrincipal() {
		initialize();
	}
	
	private void initialize() {
		menuPP = new JFrame();
		menuPP.setBounds(800, 600, 800, 600);
		menuPP.setLocationRelativeTo(null);
		menuPP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPP.getContentPane().setLayout(null);

		inicializarTF_IngresarPrecio();
		inicializarBotonEnviar();
		limitarInputUsuario();
		asignarFondo();
	}
	
	private void inicializarTF_IngresarPrecio() {
		textFieldIngresarPrecio = new JTextField();
		textFieldIngresarPrecio.setBounds(317, 355, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarPrecio);
		textFieldIngresarPrecio.setColumns(10);
	}
	
	private void inicializarBotonEnviar() {
		btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldIngresarPrecio.getText().length()>0) {
					precioPormetro=Double.parseDouble(textFieldIngresarPrecio.getText() );
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
	private void limitarInputUsuario() {
		textFieldIngresarPrecio.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				// Verificar si la tecla pulsada no es un digito
				if (Calculos.noEsUnNumuero(e.getKeyChar())) {
					e.consume(); // ignorar el evento de teclado
				} else {
					if (textFieldIngresarPrecio.getText().length() >= 7)
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
		
		lblFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/interfaz/HOME.png")));
	}

	
}
