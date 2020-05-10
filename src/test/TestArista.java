package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import negocio.Arista;
import negocio.Lugar;

class TestArista {
    Coordinate c1,c2,c3;
    Lugar a,b,c;
    Arista a1,a2,a3,a4;
    
///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() {

		c1 = new Coordinate(-34.541631195309726, -58.713340759277344);
		c2 = new Coordinate(-34.510517393776254, -58.695831298828125);
		c3 = new Coordinate(-34.48052400815731, -58.698577880859375);

		a = new Lugar("San Miguel", c1);
		b = new Lugar("Polvorines", c2);
		c = new Lugar("Pablo Nogues", c3);

		a1 = new Arista(a, b);
		a2 = new Arista(b, c);
		a3 = new Arista(c, a);
		a4 = new Arista(a, b);

	}


///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testContiene() {
		assertTrue(a1.contiene(a));
		assertTrue(a1.contiene(b));
		assertFalse(a1.contiene(c));
	}

///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testCompareTo() {
		assertTrue(a1.compareTo(a2) == 1);// 1 porque es mayor
		assertTrue(a2.compareTo(a3) == -1);// -1 porque no es mayor
		assertTrue(a1.compareTo(a1) == 0);// 0 porque es igual

	}

}
