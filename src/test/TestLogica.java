package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;



import negocio.Logica;
import negocio.Arista;
import negocio.Grafo;
import negocio.Lugar;

class TestLogica {

	    Coordinate c1,c2,c3;
	    Lugar a,b,c;
	    Arista a1,a2,a3,a4;
	    Grafo g1,g2;
	    Logica lo;
	    List<Arista> arreglo;
	    
	///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() {

		lo = new Logica();

		g1 = new Grafo();
		g2 = new Grafo();

		c1 = new Coordinate(-34.541631195309726, -58.713340759277344);
		c2 = new Coordinate(-34.510517393776254, -58.695831298828125);
		c3 = new Coordinate(-34.48052400815731, -58.698577880859375);

		a = new Lugar("San Miguel", c1);
		b = new Lugar("Polvorines", c2);
		c = new Lugar("Pablo Nogues", c3);

		g1.agregarLugar(a);
		g1.agregarLugar(b);

		lo.setGrafo(g1);
	}

	///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testArbolGeneradorMinimo() {
		assertTrue(lo.arbolGeneradorMinimo().size()==1);//El tamaño es 1 ya que un agm de 2 lugares solo tiene una arista
		g1.agregarLugar(c);
		assertTrue(lo.arbolGeneradorMinimo().size()==2);//luego de agregar un 3er lugar el agm pasa a tener 2 aristas yq ue hay 3 lugares
	}


	///////////////////////////////////////////////////////////////////////////////////
	@Test
    void testPesoTotalArbolPrim() {

        a1=new Arista(a,b);
        a2=new Arista(b,c);

        List<Arista> arreglo = new ArrayList<Arista>();
        arreglo.add(a1);
        arreglo.add(a2);

        lo.setAristasPrim(arreglo);

        assertTrue(lo.pesoTotalArbolPrim()==7157.0);//es la suma entre la distancia entre ambas aristas

    }
	///////////////////////////////////////////////////////////////////////////////////
    @Test
    void testCalcularPesoPorMetro() {

        double a = 2;
        double b = 4;

        assertEquals(8, lo.calcularPrecioPorMetro(a, b));
    }
   ////////////////////////////////////////////////////////////////////////////////
    @Test
    void testAltaLugar() {
    	lo.altaLugar("Pablo Nogues", c3);
    	assertTrue(lo.getCoordLugares().contains(c3));//es la coordenada de nogues
    } 
    @Test
    void testEsUnNumero() {
        assertTrue(Logica.esUnNumuero('e'));//devuelve true si no es un char
        assertFalse(Logica.esUnNumuero('5'));
    }
    
      
}

