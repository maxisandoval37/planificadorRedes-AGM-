package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;




import negocio.Arista;
import negocio.Calculo;
import negocio.Grafo;
import negocio.Lugar;

class TestCalculos {

	    Coordinate c1,c2,c3;
	    Lugar a,b,c;
	    Arista a1,a2,a3,a4;
	    Grafo g1,g2;

	    List<Arista> arreglo;
	    
	///////////////////////////////////////////////////////////////////////////////////    
	@BeforeEach
	public void setUp() {


	}

	///////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testCalcularPrecioPorMetro() {
		
	}


	///////////////////////////////////////////////////////////////////////////////////
	@Test
    void testPesoTotalArbolPrim() {


    }
	///////////////////////////////////////////////////////////////////////////////////
    @Test
    void testCalcularPesoPorMetro() {

    }
   ////////////////////////////////////////////////////////////////////////////////
    @Test
    void testAltaLugar() {

    } 
    @Test
    void testEsUnNumero() {
        assertTrue(Calculo.noEsUnNumuero('e'));//devuelve true si no es un char
        assertFalse(Calculo.noEsUnNumuero('5'));
    }
    
      
}

