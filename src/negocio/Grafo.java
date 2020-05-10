package negocio;

import java.util.*;

public class Grafo {

	private List<Arista> listaAristas;//caminos
	public List<Lugar> listaLugares;
	
	public Grafo() {
		listaLugares = new LinkedList<Lugar>();
		listaAristas = new LinkedList<Arista>();
	}

	public void agregarLugar(Lugar l) {
		if (!(listaLugares.contains(l))) {
			listaLugares.add(l);
			cargarAristas(l);
			LugaresJSON.agregarLugaresAJSON(LugaresJSON.transformarEnJson(l));
		}
	}

	private void cargarAristas(Lugar l) {
		for (int i = 0; i < listaLugares.size()-1; i++) {
			Arista arista = new Arista(listaLugares.get(i), l);
			listaAristas.add(arista);
		}
	}

	public List<Arista> aristasConAGM() {
		ArbolPrim AP = new ArbolPrim((LinkedList<Arista>) listaAristas);
		return AP.getAristas();
	}

	public List<Arista> getListaAristas() {
		return listaAristas;
	}
	
	public LinkedList<Lugar> getLugares() {
		return (LinkedList<Lugar>) listaLugares;
	}

}
