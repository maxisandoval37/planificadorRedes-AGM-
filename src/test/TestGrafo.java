package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import negocio.Grafo;
import negocio.Lugar;

class TestGrafo {

    Lugar l,j,m;		
    Coordinate temporal,temporal2,temporal3;
    Grafo a,b;
	
///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() {

		a = new Grafo();
		b = new Grafo();

		temporal = new Coordinate(-34.541631195309726, -58.713340759277344);
		temporal2 = new Coordinate(-34.510517393776254, -58.695831298828125);
		temporal3 = new Coordinate(-34.48052400815731, -58.698577880859375);

		l = new Lugar("San Miguel", temporal);
		j = new Lugar("Polvorines", temporal2);
		m = new Lugar("Pablo Nogues", temporal3);

		a.agregarLugar(l);
		a.agregarLugar(j);

	}
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testAgregarLugar() {
		assertTrue(a.getLugares().contains(l) && a.getLugares().contains(j));
		a.agregarLugar(m);
		assertTrue(a.getLugares().contains(m));

	}
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testCargarAristas() {
		assertTrue(a.getListaAristas().size() == 1);
		a.agregarLugar(m);
		assertTrue(a.getListaAristas().size() == 3);

	}
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testGeneradorMinimo() {// falta test
		assertTrue(a.getListaAristas().equals(a.aristasConAGM()));// es igual porque el agm de 2 lugares solo tiene 1
																	// arista, al igual que el grafo con 2 lugares
		a.agregarLugar(m);
		assertFalse(a.getListaAristas().equals(a.aristasConAGM()));// es dif porque el agm de 3 lugares no es igual a un
																	// grafo con 3 lugares

	}


}
