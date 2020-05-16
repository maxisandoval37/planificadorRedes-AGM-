package negocio;

import java.util.*;


public class Lugar {

	private double longitud1;
	private double latitud1;
	private String nombre;
	private Coordenada coord;
	public Map<Integer,Lugar> listaVecinos;//POS, LUGAR

	
	public Lugar(String nomb, double latitud, double longitud) {
		this.nombre = nomb;
		longitud1=longitud;
		latitud1=latitud;
		coord=new Coordenada(latitud,longitud);
		listaVecinos = new HashMap<Integer,Lugar>();
	}
	
	public Coordenada getCoordenate() {
		return coord;
	}

	public void agregarVecino(int indice,Lugar lugar) {
		listaVecinos.put(indice, lugar);
	}
	
	public double calcularDistanciaA_B(Lugar lugarB) {

		double radioTierra = 6371; // km

		Double lat1 = Math.toRadians(this.latitud1);
		Double lon1 = Math.toRadians(this.longitud1);
		Double lat2 = Math.toRadians(lugarB.getLatitud1());
		Double lon2 = Math.toRadians(lugarB.getLongitud1());

		double dlon = (lon2 - lon1);
		double dlat = (lat2 - lat1);

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
		double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

		double distanciaEnMts = radioTierra * c * 1000;

		return (int) distanciaEnMts;// retorna en metros
	}
	
	public double getLongitud1() {
		return longitud1;
	}

	public double getLatitud1() {
		return latitud1;
	}

	public String getNombre() {
		return nombre;
	}
	

}