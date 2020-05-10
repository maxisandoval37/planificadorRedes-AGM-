package negocio;

import java.util.*;
import org.openstreetmap.gui.jmapviewer.*;

public class Logica {

	private Grafo grafo;
	private List<Coordinate> coordLugares;
	public List<MapPolygonImpl> caminos;
	private LinkedList<MapPolygonImpl> caminosMinimos;
	private List<Arista> aristasPrim;
	
	public Logica() {
		setGrafo(new Grafo());
		LugaresJSON.abrirJSONyCopiar();
		setCoordLugares(new LinkedList<Coordinate>());
		caminos = new LinkedList<MapPolygonImpl>();
		caminosMinimos = new LinkedList<MapPolygonImpl>();
	}

	public LinkedList<MapPolygonImpl> arbolGeneradorMinimo() {
		caminosMinimos.clear();
		setAristasPrim(getGrafo().aristasConAGM());
		
		for (Arista arista : getAristasPrim()) {
			Coordinate c1 = arista.lugarA.getCoordenada();
			Coordinate c2 = arista.lugarB.getCoordenada();

			MapPolygonImpl camino = new MapPolygonImpl(c1, c2, c1);
			caminosMinimos.add(camino);
		}
		LugaresJSON.guardarLugaresEnJSON();
		return caminosMinimos;
	}

	public void altaLugar(String nombre, Coordinate c) {
		Lugar l = new Lugar(nombre, c);
		getCoordLugares().add(c);
		getGrafo().agregarLugar(l);
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
					caminos.add(caminoA);
					cargarVecino(i, j);
				}
			}
		}
	}

	private void cargarVecino(int indiceLugar, int indVecino) {
		getGrafo().listaLugares.get(indiceLugar).agregarVecino(indVecino, getGrafo().getLugares().get(indVecino));
	}

	private boolean caminoExistente(MapPolygonImpl camino) {
		for (MapPolygonImpl cam : caminos) {
			if (cam.equals(camino)) {
				return true;
			}
		}
		return false;
	}

	public double pesoTotalArbolPrim() {
		double sumaTotal = 0;
		for (Arista a : getAristasPrim()) {
			sumaTotal += a.Distancia();
		}
		return sumaTotal;
	}

	public static boolean esUnNumuero(char c) {
		return ((c < '0') || (c > '9')) && (c != '\b');
	}

	public Double calcularPrecioPorMetro(Double precio, Double metros) {
		return precio * metros;
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

	public void setCoordLugares(List<Coordinate> coordLugares) {
		this.coordLugares = coordLugares;
	}

	

}
