package negocio;

public class Calculo {
	
	public Double costosPrim= (double)0;
	
	public static boolean noEsUnNumuero(char c) {
		return ((c < '0') || (c > '9')) && (c != '\b');
	}

	public Double calcularPrecioPorMetro(Double precioPorMt, Double metrosTotales) {
		return precioPorMt * metrosTotales;
	}
	
	public Double agregarInteresSiSupero300Km(Double porcentajeAincrementar,Double costoActual) {
		
		Double porce = (costoActual * porcentajeAincrementar)/100;
		Double costoConPorcentaje = porce+costoActual;
		
		if (costoActual>300000) { //(equivale 300km)
			return costoConPorcentaje;
		}
		else
			return costoActual;
	}
	
	public Double precioFinal (Double precioPorMetro) {
		Double gastosSinAdicionales = calcularPrecioPorMetro(precioPorMetro,ArbolPrim.pesoTotalArbolPrim());
		
		return  agregarInteresSiSupero300Km ((double)30, gastosSinAdicionales);//PONER EL 30% COMO INPUT DEL USER!!
	}
	
	public static boolean intSePasadeLaLongitudDelString(int tamano,String cad) {
		if (cad.length()>tamano)
			return true;
		return false;
	}

	
	
	
	
}
