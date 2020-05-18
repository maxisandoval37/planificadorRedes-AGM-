package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import negocio.Arista;
import negocio.Lugar;





class TestArista {
    Lugar a,b,c,d;
    Arista a1,a2,a3,a4;
    
///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() throws IOException {



		a=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
		b=new Lugar("Polvorines",-34.510517393776254,-58.695831298828125);
		c=new Lugar("Pablo Nogues",-34.48052400815731 ,-58.698577880859375);
		d=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);

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
