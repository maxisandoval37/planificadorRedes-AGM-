package interfaz;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import org.openstreetmap.gui.jmapviewer.*;
import negocio.*;

import interfaz.Conexion;


public class VistaMapa {

	public JFrame ventana;
	private JPanel panelMapa;
	private JPanel panelBotones;
	private JPanel panelLabels;
	private JMapViewer mapa;
	private Calculo calculos;
	private Conexion caminos;
	private VistaMenuPrincipal menuInicio;
	private JLabel lblpesoArbol;
	private JLabel lblPrecioTotalRed;
	private JLabel lblPrecioPorMTS;
	private JLabel lblFondo;
	private JLabel lblAdicional300km;
	private JLabel lblAdicionalPasarProv;
	private JComboBox<String> comboBox;
	private JButton btnEliminar;
	private JButton btnDibujarGrafo;
	private JButton btnAGM;
	private JButton btnReset;
	private JTextField tfAuxComboBox;
	private LugaresJSON jsonConLugares;
	ArrayList<String> lugaresComboBox;
	LinkedList<MapPolygonImpl> grafoActual;

	public VistaMapa() {
		jsonConLugares = new LugaresJSON();
		jsonConLugares.abrirJSONyCopiar();
		calculos = new Calculo();
		caminos = new Conexion();
		menuInicio = new VistaMenuPrincipal();
		
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
		ventana.setBounds(800, 600, 1000, 800);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
	}
	
	//			INICIO PANELES
	private void crearPanelMapa() {
		panelMapa = new JPanel();
		panelMapa.setBounds(150, 20, 450, 400);
		ventana.getContentPane().add(panelMapa);
		mapa = new JMapViewer();
		
		Coordinate coord = new Coordinate(-34.521, -58.719);
		
		mapa.setBounds(0, 0, 10000, 1000000);
		mapa.setDisplayPosition(coord, 5);
		panelMapa.add(mapa);
	}
	
	private void crearPanelBotones() {
		panelBotones = new JPanel();
		panelBotones.setBounds(760, 20, 209, 400);
		panelBotones.setLayout(null);
		ventana.getContentPane().add(panelBotones);
		inicializarBotonDibujarGrafo();
		inicializarBotonEliminarGrafo();
		inicializarBotonAGM();
		detectarCoordenadasClick();
	}
	
	private void crearPanelLabels() {
		panelLabels = new JPanel();
		panelLabels.setBounds(150, 500, 450, 250);
		panelLabels.setLayout(null);
		ventana.getContentPane().add(panelLabels);
		
		inicializarLabelAdicionalPasarProvincia();
		inicializarLabelAdicional300km();
		inicializarLabelPesoArbol();
		inicializarLabelPrecioPorMTS();
		inicializarLabelPrecioTotal();
		inicializarListaDesplegable();
	}
	//			FIN PANELES
	
	//				INICIO LABELS
	private void inicializarLabelPrecioPorMTS() {
		lblPrecioPorMTS = new JLabel();
		lblPrecioPorMTS.setBounds(55, 35, 195, 23); //150,90
		panelLabels.add(lblPrecioPorMTS);
		
	}
	
	private void inicializarLabelAdicional300km() {
		lblAdicional300km = new JLabel();
		lblAdicional300km.setBounds(55, 75, 395, 23);
		panelLabels.add(lblAdicional300km);
	}
	
	private void inicializarLabelAdicionalPasarProvincia() {
		lblAdicionalPasarProv = new JLabel();
		lblAdicionalPasarProv.setBounds(55, 115, 395, 23);
		panelLabels.add(lblAdicionalPasarProv);
	}
	
	private void inicializarLabelPesoArbol() {
		lblpesoArbol = new JLabel();
		lblpesoArbol.setBounds(55, 155, 395, 23);
		panelLabels.add(lblpesoArbol);
	}
	
	private void inicializarLabelPrecioTotal() {
		lblPrecioTotalRed = new JLabel();
		lblPrecioTotalRed.setBounds(55, 195, 395, 23);
		panelLabels.add(lblPrecioTotalRed);
	}
	//				FIN LABELS

	//				INICIO COMBOBOX
	private void inicializarListaDesplegable(){
			comboBox = new JComboBox<String>();
			agregarDatosListaDesplegable();
			comboBox.setBounds(45, 290, jsonConLugares.JSONsizeNombreMasLargo()*10, 25);
			accionComboBox();
		}

	private void agregarDatosListaDesplegable() {
		lugaresComboBox = new ArrayList<String>();
		if (!jsonConLugares.estaVacio()) {
			for (int i = jsonConLugares.tamaño()-1;i>=0;i--) {
				if (!lugaresComboBox.contains(jsonConLugares.getNombre(i))) {
					comboBox.addItem(jsonConLugares.getNombre(i));
					lugaresComboBox.add(jsonConLugares.getNombre(i));
				}	
			}
		}
	}
	//				FIN COMBOBOX
	
	//			INICIO BOTONES
	private void inicializarBotonDibujarGrafo() {
		btnDibujarGrafo = new JButton("Dibujar Grafo");
		btnDibujarGrafo.setBounds(29, 10, 150, 50);
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
		btnAGM.setBounds(10, 170, 185, 50);
		panelBotones.add(btnAGM);

		accionBotonGenerarMin();
	}
	
	private void inicializarBotonReset() {
		btnReset = new JButton("REINICIAR");
		btnReset.setForeground(Color.WHITE);
		btnReset.setBackground(Color.RED);
		btnReset.setBounds(800, 670, 150, 80);
		ventana.getContentPane().add(btnReset);
		
		
		accionBotonReset();
	}
	//			FIN BOTONES
	
	//		INICIO ACCIONES DE BOTONES
	private void accionBotonGenerarMin() {
		btnAGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jsonConLugares.guardarLugaresEnJSON();
				quitarGrafos();
				grafoActual = caminos.arbolGeneradorMinimo();
				for (MapPolygonImpl camino : grafoActual) {
					mapa.addMapPolygon(camino);
				}
				lblPrecioPorMTS.setText("COSTO POR METRO:  $" + VistaMenuPrincipal.precioPormetro);
				
				lblAdicional300km.setText("ADICIONAL POR SUPERAR 300KM:  $"+VistaMenuPrincipal.porcentajeExtra300km);
				
				lblAdicionalPasarProv.setText("ADICIONAL POR ATRAVESAR PROVINCIA:  $"+VistaMenuPrincipal.costoPorPasarProvincia);
				
				lblpesoArbol.setText("METROS TOTALES(PESO ARBOL):  " + ArbolPrim.pesoTotalArbolPrim()+" 'MTS'");
				
				lblPrecioTotalRed.setText("GASTO TOTAL:  $" + 
				calculos.precioFinal(VistaMenuPrincipal.precioPormetro,VistaMenuPrincipal.porcentajeExtra300km,
				VistaMenuPrincipal.costoPorPasarProvincia));
			}
		});
	}

	private void accionBotonDibujarGrafo() {
		btnDibujarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitarGrafos();
				grafoActual = (LinkedList<MapPolygonImpl>) caminos.listaCaminos;
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
				
				comboBox.removeAll();
				VistaMenuPrincipal.costoPorPasarProvincia=(double)0;
				VistaMenuPrincipal.porcentajeExtra300km=(double)0;
				VistaMenuPrincipal.precioPormetro=(double)0;
				
				
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
				
				for (int i = jsonConLugares.tamaño()-1;i>=0;i--) {
					if (jsonConLugares.getNombre(i).equals(tfAuxComboBox.getText().toString())) {
						try {
							marcarCoordenadasConJSON(jsonConLugares.getNombre(i),
							jsonConLugares.getLatitud(i),
							jsonConLugares.getLongitud(i));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
		});
		panelBotones.add(comboBox);
	}
	
	private void marcarCoordenadasConJSON(String nombre, double latitud, double longitud) throws IOException {
		Coordinate coord = new Coordinate(latitud,longitud);
		mapa.addMapMarker(new MapMarkerDot(nombre, coord));
		caminos.altaLugar(nombre, latitud,longitud);
	}
	//		FIN ACCIONES DE BOTONES
	
	private void asignarFondo(){
        lblFondo = new JLabel("");
        lblFondo.setBounds(0, 0, 1000, 800);
        ventana.getContentPane().add(lblFondo);

        lblFondo.setIcon(new ImageIcon(VistaMenuPrincipal.class.getResource("/interfaz/FONDO.png")));
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
						
					if (nombre != null && nombre.length() > 0) {

						if (!jsonConLugares.comprobarExistenciaNombre(nombre)) {
							if (!Calculo.intSePasadeLaLongitudDelString((double)10, nombre)) {
								
								MapMarkerDot marcador=new MapMarkerDot(nombre, coordenadaClick);
								marcador.getStyle().setBackColor(Color.red);
								mapa.addMapMarker(marcador);
								caminos.altaLugar(nombre, coordenadaClick.getLat(),coordenadaClick.getLon());
								Lugar lugarNuevo = null;
									try {
										lugarNuevo = new Lugar (nombre,coordenadaClick.getLat(),coordenadaClick.getLon());
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								jsonConLugares.agregarLugar(jsonConLugares.transformarEnJson(lugarNuevo));
							}
							
							else {
								JOptionPane.showMessageDialog(null, "EL NOMBRE DEBE TENER MENOS DE 10 CARACTERES");
							}
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