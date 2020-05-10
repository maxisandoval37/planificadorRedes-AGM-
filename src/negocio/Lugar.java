package negocio;

import java.util.*;
import org.openstreetmap.gui.jmapviewer.*;

public class Lugar {

	private Coordinate coordenada;
	private String nombre;
	public Map<Integer,Lugar> listaVecinos;//POS, LUGAR

	
	public Lugar(String nomb, Coordinate coord) {
		this.nombre = nomb;
		coordenada = coord;
		listaVecinos = new HashMap<Integer,Lugar>();
	}
	
	public void agregarVecino(int indice,Lugar l) {
		listaVecinos.put(indice, l);
	}
	
	public double calcularDistanciaA_B(Lugar lugarB) {

		double radioTierra = 6371; // km

		Double lat1 = Math.toRadians(coordenada.getLat());
		Double lon1 = Math.toRadians(coordenada.getLon());
		Double lat2 = Math.toRadians(lugarB.coordenada.getLat());
		Double lon2 = Math.toRadians(lugarB.coordenada.getLon());

		double dlon = (lon2 - lon1);
		double dlat = (lat2 - lat1);

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
		double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

		double distanciaEnMts = radioTierra * c * 1000;

		return (int) distanciaEnMts;// retorna en metros
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getLatitud() {
		return coordenada.getLat();
	}

	public double getLongitud() {
		return coordenada.getLon();
	}

	public Coordinate getCoordenada() {
		return coordenada;
	}
}