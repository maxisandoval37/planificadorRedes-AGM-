package interfaz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.*;

import negocio.Arista;
import negocio.Grafo;
import negocio.Lugar;

public class Conexion {

	public Conexion() {
		setGrafo(new Grafo());
		setCoordLugares(new ArrayList<Coordinate>());
		listaCaminos = new LinkedList<MapPolygonImpl>();
		caminosMinimos = new LinkedList<MapPolygonImpl>();
	}
	
	private Grafo grafo;
	private ArrayList<Coordinate> coordLugares;
	public List<MapPolygonImpl> listaCaminos;
	private LinkedList<MapPolygonImpl> caminosMinimos;
	private List<Arista> aristasPrim;
	
	public LinkedList<MapPolygonImpl> arbolGeneradorMinimo() {
		caminosMinimos.clear();
		setAristasPrim(getGrafo().aristasConAGM());
		
		for (Arista arista : getAristasPrim()) {
			Coordinate c1 = new Coordinate (arista.lugarA.getLatitud1(),arista.lugarA.getLongitud1());
			Coordinate c2 = new Coordinate (arista.lugarB.getLatitud1(),arista.lugarB.getLongitud1());

			MapPolygonImpl camino = new MapPolygonImpl(c1, c2, c1);
			
			
			caminosMinimos.add(camino);
		}
		return caminosMinimos;
	}

	public void altaLugar(String nombre, double latitud, double longitud)  {
		Lugar lugar = null;
		try {
			lugar = new Lugar(nombre,  latitud,  longitud);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Coordinate coord = new Coordinate(latitud,longitud);
		getCoordLugares().add(coord);
		getGrafo().agregarLugar(lugar);
		actualizarCaminos();
	}
	
	private void actualizarCaminos() {
		for (int i = 0; i < getCoordLugares().size(); i++) {
			for (int j = 0; j < getCoordLugares().size(); j++) {

				MapPolygonImpl caminoA = new MapPolygonImpl(getcoordLugares(i), getcoordLugares(j), getcoordLugares(i));
				MapPolygonImpl caminoB = new MapPolygonImpl(getcoordLugares(j), getcoordLugares(i), getcoordLugares(j));

				if (caminoExistente(caminoB)) {
					cargarVecino(i, j);
				} else {
					listaCaminos.add(caminoA);
					cargarVecino(i, j);
				}
			}
		}
	}

	private void cargarVecino(int indiceLugar, int indVecino) {
		getGrafo().listaLugares.get(indiceLugar).agregarVecino(indVecino, getGrafo().getLugares().get(indVecino));
	}

	private boolean caminoExistente(MapPolygonImpl camino) {
		for (MapPolygonImpl cam : listaCaminos) {
			if (cam.equals(camino)) {
				return true;
			}
		}
		return false;
	}

	public Coordinate getcoordLugares(int i) {
		return getCoordLugares().get(i);
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public List<Arista> getAristasPrim() {
		return aristasPrim;
	}

	public void setAristasPrim(List<Arista> aristasPrim) {
		this.aristasPrim = aristasPrim;
	}

	public List<Coordinate> getCoordLugares() {
		return coordLugares;
	}

	public void setCoordLugares(ArrayList<Coordinate> coordLugares) {
		this.coordLugares = coordLugares;
	}
	
}
