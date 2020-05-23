package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import negocio.ArbolPrim;
import negocio.Arista;
import negocio.Lugar;

class TestArbolPrim {

    Lugar a,b,c,d;
    Arista a1,a2,a3;
    ArbolPrim arbol;
    LinkedList<Arista> aristas;

	@BeforeEach
	public void setUp() throws IOException {

		aristas = new LinkedList<Arista>();

		a=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);
		b=new Lugar("Polvorines",-34.510517393776254,-58.695831298828125);
		c=new Lugar("Pablo Nogues",-34.48052400815731 ,-58.698577880859375);
		d=new Lugar("San Miguel",-34.541631195309726 ,-58.713340759277344);

		a1 = new Arista(a, b);
		a2 = new Arista(b, c);
		a3 = new Arista(c, a);

		aristas.add(a1);
		aristas.add(a2);
		aristas.add(a3);

		arbol = new ArbolPrim(aristas);
	}
	///////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("static-access")
	@Test
	public void testpesoMinimoSinCiclos() {
		
	assertTrue(arbol.getRetAristas().contains(a1));//COMPRUEBA SI TIENE LAS ARISTAS CORRECTAS  DEL AGM
	assertTrue(arbol.getRetAristas().contains(a2));
	assertFalse(arbol.getRetAristas().contains(a3));
	
	assertTrue(arbol.getLugaresRecorridos().contains(a));//COMPRUEBA SI RECORRE LOS 3 lugares
	assertTrue(arbol.getLugaresRecorridos().contains(b));
	assertTrue(arbol.getLugaresRecorridos().contains(c));

	}
///////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("static-access")
	@Test
    void testPesoTotalArbolPrim() {

        a1=new Arista(a,b);
        a2=new Arista(b,c);


        List<Arista> arreglo = new ArrayList<Arista>();
        arreglo.add(a1);
        arreglo.add(a2);
        assertTrue(arbol.pesoTotalArbolPrim()==7157.0);//ES LA DISTANCIA DE LA SUMA DE AMBAS ARISTAS

    }

}
