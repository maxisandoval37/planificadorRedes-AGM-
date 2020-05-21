package negocio;

public class Calculo {

	
	public static boolean noEsUnNumuero(char c) {
		return ((c < '0') || (c > '9')) && (c != '\b');
	}

	private Double calcularPrecioPorMetro(Double precioPorMt, Double metrosTotales) {
		return precioPorMt * metrosTotales;
	}
	
	private Double agregarInteresSiSupero300Km(Double porcentajeAincrementar,Double costoActual, Double DistanciaTotal) {
		
		Double porce = (costoActual * porcentajeAincrementar)/100;
		Double costoConPorcentaje = porce+costoActual;
		if (DistanciaTotal>300000) { //(equivale 300km)
			return costoConPorcentaje;
		}
		else
			return costoActual;
	}
	
	public Double precioFinal (Double precioPorMetro,Double porcentajeInteres,Double ExtraPasarPorProvincia) {
		Double gastosSinAdicionales = calcularPrecioPorMetro(precioPorMetro,ArbolPrim.pesoTotalArbolPrim());
		
		Double precioFinal = agregarInteresSiSupero300Km (porcentajeInteres, gastosSinAdicionales,ArbolPrim.pesoTotalArbolPrim());
		precioFinal+=adicionalPorAtravesarProvincia(ExtraPasarPorProvincia);
		
		return precioFinal;
	}
	
	public static boolean intSePasadeLaLongitudDelString(Double tamano,String cad) {
		if (cad.length()>tamano)
			return true;
		return false;
	}
	
	private Double adicionalPorAtravesarProvincia(Double costoExtra) {
		return (Grafo.getSetProvinciasSinRepetir().size()-1) * costoExtra;
	}
	
	
}
