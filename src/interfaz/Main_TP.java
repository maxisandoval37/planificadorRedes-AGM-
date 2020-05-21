package interfaz;

import java.awt.EventQueue;

public class Main_TP {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMenuPrincipal inicio = new VistaMenuPrincipal();
					inicio.menuPP.setResizable(false);
					inicio.menuPP.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
