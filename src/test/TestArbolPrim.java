package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import negocio.ArbolPrim;
import negocio.Arista;
import negocio.Lugar;

class TestArbolPrim {
    Coordinate c1,c2,c3;
    Lugar a,b,c;
    Arista a1,a2,a3;
    ArbolPrim arbol;
    LinkedList<Arista> aristas;

	@BeforeEach
	public void setUp() {

		aristas = new LinkedList<Arista>();

		c1 = new Coordinate(-34.541631195309726, -58.713340759277344);
		c2 = new Coordinate(-34.510517393776254, -58.695831298828125);
		c3 = new Coordinate(-34.48052400815731, -58.698577880859375);

		a = new Lugar("San Miguel", c1);
		b = new Lugar("Polvorines", c2);
		c = new Lugar("Pablo Nogues", c3);

		a1 = new Arista(a, b);
		a2 = new Arista(b, c);
		a3 = new Arista(c, a);

		aristas.add(a1);
		aristas.add(a2);
		aristas.add(a3);

		arbol = new ArbolPrim(aristas);
	}
	///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testpesoMinimoSinCiclos() {//cambiar nombre
		
	assertTrue(arbol.getRetAristas().contains(a1));
	assertTrue(arbol.getRetAristas().contains(a2));
	assertFalse(arbol.getRetAristas().contains(a3));
	
	assertTrue(arbol.getLugaresRecorridos().contains(a));//RECORRE LOS 3 lugares
	assertTrue(arbol.getLugaresRecorridos().contains(b));
	assertTrue(arbol.getLugaresRecorridos().contains(c));

	assertTrue(arbol.getAristasClonadas().isEmpty());//las aristas clonadas deben quedar vacias luego de contruir

	}

}
