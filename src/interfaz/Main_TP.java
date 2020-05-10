package interfaz;

import java.awt.EventQueue;

public class Main_TP {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal inicio = new MenuPrincipal();
					inicio.menuPP.setResizable(false);
					inicio.menuPP.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
