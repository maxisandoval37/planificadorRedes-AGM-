package negocio;

public class Arista implements Comparable<Arista>, Cloneable {

	public Lugar lugarA;
	public Lugar lugarB;
	private double distancia;

	public Arista(Lugar a, Lugar b) {
		lugarA = a;
		lugarB = b;
		distancia = a.calcularDistanciaA_B(b);
	}
    
	public double Distancia() {
		return distancia;
	}

	public boolean contiene(Lugar a) {
		return lugarA.equals(a) || lugarB.equals(a);
	}
	
	public void setDistancia(double dist) {
		distancia = dist;
	}

	@Override
	public int compareTo(Arista ari) {
		return distancia > ari.Distancia() ? 1 : distancia == ari.Distancia() ? 0 : -1;
	}
    @Override
    protected Object clone() throws CloneNotSupportedException {
        final Arista newObj = new Arista(lugarA,lugarB);
        newObj.setDistancia(this.distancia);
        return newObj;
    }
    

}