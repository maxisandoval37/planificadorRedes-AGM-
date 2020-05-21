package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import negocio.Grafo;
import negocio.Lugar;

class TestGrafo {

    Lugar a,b,c,d;		
    Grafo g1,g2;
	
///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() throws IOException {

		g1 = new Grafo();
		g2 = new Grafo();

		a=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
		b=new Lugar("Polvorines",-34.510517393776254,-58.695831298828125);
		c=new Lugar("Pablo Nogues",-34.48052400815731 ,-58.698577880859375);
		d=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
		
		g1.agregarLugar(a);
		g1.agregarLugar(b);

	}
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testAgregarLugar() {
		assertTrue(g1.getLugares().contains(a) && g1.getLugares().contains(b));
		g1.agregarLugar(c);
		assertTrue(g1.getLugares().contains(c));

	}
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testCargarAristas() {
		assertTrue(g1.getListaAristas().size() == 1);
		g1.agregarLugar(c);
		assertTrue(g1.getListaAristas().size() == 3);

	}
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testAristasConAGM() {
		assertTrue(g1.getListaAristas().equals(g1.aristasConAGM()));// es igual porque el agm de 2 lugares solo tiene 1
																	// arista, al igual que el grafo con 2 lugares
		g1.agregarLugar(c);
		assertFalse(g1.getListaAristas().equals(g1.aristasConAGM()));// es dif porque el agm de 3 lugares no es igual a un
																	// grafo con 3 lugares

	}
	
	


}
