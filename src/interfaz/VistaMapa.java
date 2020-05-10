package interfaz;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import org.openstreetmap.gui.jmapviewer.*;


import negocio.Logica;
import negocio.LugaresJSON;

public class VistaMapa {

	public JFrame ventana;
	private JPanel panelMapa;
	private JPanel panelBotones;
	private JPanel panelLabels;
	private JMapViewer mapa;
	private Logica logica;
	private MenuPrincipal menuInicio;
	private JLabel lblpesoArbol;
	private JLabel lblPrecioTotalRed;
	private JLabel lblPrecioPorMTS;
	private JLabel lblFondo;
	private JComboBox<String> comboBox;
	private JButton btnEliminar;
	private JButton btnDibujarGrafo;
	private JButton btnAGM;
	private JButton btnReset;
	private JTextField tfAuxComboBox;
	ArrayList<String> lugaresComboBox;
	LinkedList<MapPolygonImpl> grafoActual;
	
	
	public VistaMapa() {
		logica = new Logica();
		menuInicio = new MenuPrincipal();
		grafoActual = new LinkedList<MapPolygonImpl>();
		
		crearVentana();
		crearPanelMapa();
		crearPanelBotones();
		crearPanelLabels();	
		inicializarBotonReset();
		asignarFondo();
	}

	private void crearVentana() {
		ventana = new JFrame();
		ventana.getContentPane().setBackground(Color.LIGHT_GRAY);
		ventana.setBounds(800, 600, 800, 600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
	}
	
	//			INICIO PANELES
	private void crearPanelMapa() {
		panelMapa = new JPanel();
		panelMapa.setBounds(0, 0, 450, 400);
		ventana.getContentPane().add(panelMapa);
		mapa = new JMapViewer();
		
		Coordinate coord = new Coordinate(-34.521, -58.719);

		mapa.setDisplayPosition(coord, 12);
		panelMapa.add(mapa);
	}
	
	private void crearPanelBotones() {
		panelBotones = new JPanel();
		panelBotones.setBounds(481, 0, 209, 400);
		panelBotones.setLayout(null);
		ventana.getContentPane().add(panelBotones);
		inicializarBotonDibujarGrafo();
		inicializarBotonEliminarGrafo();
		inicializarBotonAGM();
		detectarCoordenadasClick();
	}
	
	private void crearPanelLabels() {
		panelLabels = new JPanel();
		panelLabels.setBounds(10, 422, 577, 139);
		panelLabels.setLayout(null);
		ventana.getContentPane().add(panelLabels);
		
		inicializarLabelPesoArbol();
		inicializarLabelPrecioPorMTS();
		inicializarLabePrecioTotalRed();
		inicializarListaDesplegable();
	}
	//			FIN PANELES

	//				INICIO LABELS
	private void inicializarLabePrecioTotalRed() {
		lblPrecioTotalRed = new JLabel();
		lblPrecioTotalRed.setBounds(200, 10, 195, 23);
		panelLabels.add(lblPrecioTotalRed);
	}
	
	private void inicializarLabelPesoArbol() {
		lblpesoArbol = new JLabel();
		lblpesoArbol.setBounds(200, 50, 195, 23);
		panelLabels.add(lblpesoArbol);
	}
	
	private void inicializarLabelPrecioPorMTS() {
		lblPrecioPorMTS = new JLabel();
		lblPrecioPorMTS.setBounds(200, 90, 195, 23);
		panelLabels.add(lblPrecioPorMTS);
		
	}
	//				FIN LABELS

	//				INICIO COMBOBOX
	private void inicializarListaDesplegable(){
			comboBox = new JComboBox<String>();
			agregarDatosListaDesplegable();
			comboBox.setBounds(20, 15, 80, 25);
			accionComboBox();
		}

	private void agregarDatosListaDesplegable() {
		lugaresComboBox = new ArrayList<String>();
		if (!LugaresJSON.jsonConLugares.estaVacio()) {
			for (int i = LugaresJSON.jsonConLugares.tamaño()-1;i>=0;i--) {
				if (!lugaresComboBox.contains(LugaresJSON.jsonConLugares.getNombre(i))) {
					comboBox.addItem(LugaresJSON.jsonConLugares.getNombre(i));
					lugaresComboBox.add(LugaresJSON.jsonConLugares.getNombre(i));
				}	
			}
		}
	}
	//				FIN COMBOBOX
	
	//			INICIO BOTONES
	private void inicializarBotonDibujarGrafo() {
		btnDibujarGrafo = new JButton("Dibujar Grafo");
		btnDibujarGrafo.setBounds(29, 11, 150, 50);
		panelBotones.add(btnDibujarGrafo);
		
		accionBotonDibujarGrafo();
	}
	
	private void inicializarBotonEliminarGrafo() {
		btnEliminar = new JButton("Eliminar Grafo");
		btnEliminar.setBounds(29, 90, 150, 50);
		panelBotones.add(btnEliminar);
		
		accionBotonEliminar();
	}
	
	private void inicializarBotonAGM() {
		btnAGM = new JButton("Arbol Generador Minimo");
		btnAGM.setBounds(10, 295, 185, 50);
		panelBotones.add(btnAGM);
		
		accionBotonGenerarMin();
	}
	
	private void inicializarBotonReset() {
		btnReset = new JButton("REINICIAR");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.RED);
		btnReset.setBounds(597, 527, 93, 23);
		ventana.getContentPane().add(btnReset);
		
		
		accionBotonReset();
	}
	//			FIN BOTONES
	
	//		INICIO ACCIONES DE BOTONES
	private void accionBotonGenerarMin() {
		btnAGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarGrafos();
				grafoActual = logica.arbolGeneradorMinimo();
				for (MapPolygonImpl camino : grafoActual) {
					mapa.addMapPolygon(camino);
				}
				lblPrecioTotalRed.setText("GASTO TOTAL:  $" + logica.calcularPrecioPorMetro(MenuPrincipal.precioPormetro,logica.pesoTotalArbolPrim()));
				lblpesoArbol.setText("PESO ARBOL:  " + logica.pesoTotalArbolPrim()+" 'MTS'");
				lblPrecioPorMTS.setText("COSTO POR METRO:  $" + MenuPrincipal.precioPormetro);
				
			}
		});
	}

	private void accionBotonDibujarGrafo() {
		btnDibujarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarGrafos();
				grafoActual = (LinkedList<MapPolygonImpl>) logica.caminos;
				for (MapPolygonImpl camino : grafoActual)
					mapa.addMapPolygon(camino);
			}
		});
	}

	private void accionBotonEliminar() {
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarGrafos();
			}
		});
	}

	private void accionBotonReset() {
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.removeAll();//*
				
				ventana.setVisible(false);
				menuInicio.menuPP.setVisible(true);
				menuInicio.menuPP.setResizable(false);
				
			}
		});
	}
	
	private void accionComboBox() {
		tfAuxComboBox = new JTextField(20);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfAuxComboBox.setText(comboBox.getSelectedItem().toString());
				
				for (int i = LugaresJSON.jsonConLugares.tamaño()-1;i>=0;i--) {
					if (LugaresJSON.jsonConLugares.getNombre(i).equals(tfAuxComboBox.getText().toString())) {

						marcarCoordenadasConJSON(LugaresJSON.jsonConLugares.getNombre(i),
						LugaresJSON.jsonConLugares.getLatitud(i),
						LugaresJSON.jsonConLugares.getLongitud(i));
					}
					
				}
			}
		});
		panelLabels.add(comboBox);
	}
	

	private void marcarCoordenadasConJSON(String nombre, double latitud, double longitud) {
		Coordinate coord = new Coordinate(latitud,longitud);
		mapa.addMapMarker(new MapMarkerDot(nombre, coord));
		logica.altaLugar(nombre, coord);
	}
	//		FIN ACCIONES DE BOTONES
	
	private void asignarFondo(){
        lblFondo = new JLabel("");
        lblFondo.setBounds(0, 0, 800, 600);
        ventana.getContentPane().add(lblFondo);

        lblFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/interfaz/FONDO.png")));
    }
	
	private void quitarGrafos() {
		for (MapPolygonImpl c : grafoActual) {
			mapa.removeMapPolygon(c);
		}
	}

	private void detectarCoordenadasClick() {
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON1) {
					Coordinate coordenadaClick = (Coordinate) mapa.getPosition(e.getPoint());
					String nombre = JOptionPane.showInputDialog("NOMBRE: ");

					if ((nombre != null) && (nombre.length() > 0)) {

						if (!LugaresJSON.jsonConLugares.comprobarExistenciaNombre(nombre)) {
							MapMarkerDot marcador=new MapMarkerDot(nombre, coordenadaClick);
							marcador.getStyle().setBackColor(Color.red);
							mapa.addMapMarker(marcador);
							logica.altaLugar(nombre, coordenadaClick);
						}

						else {
							JOptionPane.showMessageDialog(null, "NO SE PUEDE INGRESAR UN NOMBRE REPETIDO");
						}

					}

					else {
						JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NOMBRE PARA CONTINUAR");
					}

				}
			}
		});
	}
}
