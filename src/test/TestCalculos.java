package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import negocio.ArbolPrim;
import negocio.Arista;
import negocio.Calculo;
import negocio.Grafo;
import negocio.Lugar;

class TestCalculos {

    Lugar a,b,c,d;
    Arista a1,a2,a3,a4;
    ArbolPrim arbol;
    LinkedList<Arista> aristas;
    Calculo calculo;
    Grafo g1;

	@BeforeEach
	public void setUp() throws IOException {
		g1 = new Grafo();
		
		calculo=new Calculo();

		aristas = new LinkedList<Arista>();

		a=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
		b=new Lugar("Polvorines",-34.510517393776254,-58.695831298828125);
		c=new Lugar("Rio Negro",-41.50857729743933 ,-68.8623046875);
		d=new Lugar("Santa Cruz",-47.69497434186282,-68.4228515625);

		a1 = new Arista(a, b);
		a2 = new Arista(b, c);
		a3 = new Arista(c, a);
		a4 = new Arista(a,d);

		aristas.add(a1);
		aristas.add(a2);
		aristas.add(a3);
		aristas.add(a4);
		
		g1.agregarLugar(a);
		g1.agregarLugar(b);
		g1.agregarLugar(c);
		g1.agregarLugar(d);

		arbol = new ArbolPrim(aristas);
	}

	///////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("static-access")
	@Test
	public void testPrecioFinal() {
		assertTrue(arbol.pesoTotalArbolPrim()==2851930.3055313313);
		assertTrue(calculo.precioFinal(0.5, 5.0, 10000.0)==1517263.410403949);
		
		
		//metros totales*precio por metro
		//2851930.3055313313*0.5=1425965.1527656657
		
		//precio sin adicional+ aumento por suprar los 300km(5% del precio sin adicional)+10k por cada provincia que se pase(son 2 en este caso)
		// es igual al precio final
		
		//1425965.1527656657+71298.25763828328+10000*2=1517263.410403949
		
		
		

	}


	///////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("static-access")
	@Test
    void testIntSePasadeLaLongitudDelString() {
		assertTrue(calculo.intSePasadeLaLongitudDelString(10.0, "123456789aaaa"));
		assertFalse(calculo.intSePasadeLaLongitudDelString(10.0, "123"));
    }
	///////////////////////////////////////////////////////////////////////////////////

    @Test
    void testEsUnNumero() {
        assertTrue(Calculo.noEsUnNumuero('e'));//devuelve true si no es un char
        assertFalse(Calculo.noEsUnNumuero('5'));
    }
    
      
}

