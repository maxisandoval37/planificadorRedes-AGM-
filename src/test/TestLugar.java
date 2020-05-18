package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import negocio.Lugar;

class TestLugar {
	
    Lugar a,b,c,d;
	
///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() throws IOException {


	
		a=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
		b=new Lugar("Polvorines",-34.510517393776254,-58.695831298828125);
		c=new Lugar("Pablo Nogues",-34.48052400815731 ,-58.698577880859375);
		d=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
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

///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testBuscarProvincia() throws JsonIOException, JsonSyntaxException, IOException {
		assertTrue(a.buscarProvincia(-34.541631195309726,-58.713340759277344 ).equals("Buenos Aires")); 
		assertFalse(a.buscarProvincia(-34.510517393776254,-58.695831298828125).equals("Cordoba")); 	
	}
}
