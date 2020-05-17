package negocio;


public class Calculo {
	
	public Double costosPrim= (double)0;
	
	public static boolean noEsUnNumuero(char c) {
		return ((c < '0') || (c > '9')) && (c != '\b');
	}

	public Double calcularPrecioPorMetro(Double precioPorMt, Double metrosTotales) {
		return precioPorMt * metrosTotales;
	}
	
	private Double aumentoExcedente(Double costo, Double porcentaje) {
		Double porc = (costo * porcentaje)/100;
		return porc+costo;
	}
	
	public Double superoKm(Double porcentaje,Double kilometros) {
		
		if (kilometros>300000) { //en mts (equivale 300km)
		
			
			return aumentoExcedente(kilometros,porcentaje);
			
		}
		else
			return kilometros;
	}
	
	public Double precioFinal (Double precioPorMetro) {
		Double prePorMetro = calcularPrecioPorMetro(precioPorMetro,ArbolPrim.pesoTotalArbolPrim());
		
		return  superoKm ((double)30, prePorMetro);
	}

	
	
	
	
}
