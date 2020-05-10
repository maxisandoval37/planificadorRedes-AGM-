package test;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import negocio.Lugar;

class TestLugar {
	
    Coordinate c1,c2,c3,c4;
    Lugar a,b,c,d;
	
///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() {

		c1=new Coordinate(-34.541631195309726 ,-58.713340759277344);
	    c2=new Coordinate(-34.510517393776254,-58.695831298828125);
		c3=new Coordinate(-34.48052400815731 ,-58.698577880859375); 
		c4=new Coordinate(-34.541631195309726 ,-58.713340759277344);
	
		a=new Lugar("San Miguel",c1);
		b=new Lugar("Polvorines",c2);
		c=new Lugar("Pablo Nogues",c3);
		d=new Lugar("San Miguel",c4);
	}
	
///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testAgregarVecino() {
		a.agregarVecino(0, b);
		assertTrue(a.listaVecinos.containsKey(0));
	}

///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testCalcularDistanciaA_B() {
		assertTrue(a.calcularDistanciaA_B(b)==3813.0); //es la distancia entres esos 2 puntos (polvorines y san miguel aprox)
		assertTrue(b.calcularDistanciaA_B(c)==3344.0); //distancia entre polvorines y nogues arpox	
	}

	
}
