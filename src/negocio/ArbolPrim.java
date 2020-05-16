package negocio;

import java.util.*;

public class ArbolPrim {
	
	private static List<Arista> retAristas;
	private List<Lugar> lugaresRecorridos;
	private List<Arista> aristasClonadas;

	@SuppressWarnings("unchecked")
	public ArbolPrim(LinkedList<Arista> aristas) {
		lugaresRecorridos = new LinkedList<Lugar>();
		retAristas = new LinkedList<Arista>();
		aristasClonadas = new LinkedList<Arista>();
		aristasClonadas = (LinkedList<Arista>) aristas.clone();
	
		resolverArbolConPrim(aristasClonadas);
	}
	
	private void resolverArbolConPrim(List<Arista> ariClonadas) {
		if (!ariClonadas.isEmpty()) {
			Lugar l = ariClonadas.get(0).lugarA;
			lugaresRecorridos.add(l);
		}

		while (!ariClonadas.isEmpty()) {
			Arista ari = pesoMinimoSinCiclos(lugaresRecorridos);

			if (ari != null) {
				retAristas.add(ari);

				if (lugaresRecorridos.contains(ari.lugarA))
					lugaresRecorridos.add(ari.lugarB);
				else
					lugaresRecorridos.add(ari.lugarA);
			} else
				ariClonadas.clear(); // LIMPIO ARISTAS INUTILIZABLES
		}
	}
	
	public Arista pesoMinimoSinCiclos(List<Lugar> lugaresRecorridos) {
		Arista min = null;
		int indice = 0;

		for (int i = 0; i < lugaresRecorridos.size(); i++) {
			for (int j = 0; j < aristasClonadas.size(); j++) {

				if (min == null && aristasClonadas.get(j).contiene(lugaresRecorridos.get(i))) {
					if (!generaCiclo(lugaresRecorridos, aristasClonadas.get(j)))
						min = aristasClonadas.get(j);
				} else {
					if (min != null) {
						if (min.compareTo(aristasClonadas.get(j)) == 1
								&& aristasClonadas.get(j).contiene(lugaresRecorridos.get(i))) {

							if (!generaCiclo(lugaresRecorridos, aristasClonadas.get(j))) {
								min = aristasClonadas.get(j);
								indice = j;
							}
						}
					}
				}
			}
		}
		if (min != null)// REMUEVO, PARA PODER SEGUIR COMPROBANDO LAS ARISTAS SIGUIENTES.
			aristasClonadas.remove(indice);

		return min;
	}
	

	private boolean generaCiclo(List<Lugar> l, Arista arista) {
		return l.contains(arista.lugarA) && l.contains(arista.lugarB);
	}
	
	public static double pesoTotalArbolPrim() {
		double sumaTotal = 0;
		for (Arista a : getRetAristas()) {
			sumaTotal += a.Distancia();
		}
		return sumaTotal;
	}
	
	public static List<Arista> getRetAristas() { 
		return retAristas;
	}

	public List<Lugar> getLugaresRecorridos() {
		return lugaresRecorridos;
	}

	public List<Arista> getAristasClonadas() {
		return aristasClonadas;
	}
	
}