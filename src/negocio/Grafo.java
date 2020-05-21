package negocio;

import java.util.*;

public class Grafo {

	private List<Arista> listaAristas;//caminos
	public List<Lugar> listaLugares;
	static Set<String> provinciasSinRepetir;

	public Grafo() {
		listaLugares = new LinkedList<Lugar>();
		listaAristas = new LinkedList<Arista>();
		provinciasSinRepetir = new HashSet<String>();
	}

	public void agregarLugar(Lugar lugar) {
		if (!(listaLugares.contains(lugar))) {
			listaLugares.add(lugar);
			cargarAristas(lugar);
			provinciasSinRepetir.add(lugar.getProvincia());
		}
	}

	private void cargarAristas(Lugar l) {
		for (int i = 0; i < listaLugares.size()-1; i++) {
			Arista arista = new Arista(listaLugares.get(i), l);
			listaAristas.add(arista);
		}
	}

	
	@SuppressWarnings("static-access")
	public List<Arista> aristasConAGM() {
		ArbolPrim AP = new ArbolPrim((LinkedList<Arista>) listaAristas);
		return AP.getRetAristas();
	}

	public List<Arista> getListaAristas() {
		return listaAristas;
	}
	
	public LinkedList<Lugar> getLugares() {
		return (LinkedList<Lugar>) listaLugares;
	}
	
	public static Set<String> getSetProvinciasSinRepetir() {
		return provinciasSinRepetir;
	}

}
