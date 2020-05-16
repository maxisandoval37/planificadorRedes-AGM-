package negocio;

public class Coordenada {

   private double lat;
    private double longi;
    
	public Coordenada(double latitud,double longitud){
        this.lat=latitud;
        this.longi=longitud;
    }

    public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLongi() {
		return longi;
	}

	public void setLongi(double longi) {
		this.longi = longi;
	}




}
