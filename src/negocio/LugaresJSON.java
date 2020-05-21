package negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class LugaresJSON {
	private JsonArray listaLugares;
	private String rutaDeJSON = "src/negocio/datosLugares.JSON";

	public LugaresJSON() {
		listaLugares = new JsonArray();
	}

	public int tamaño() {
		return listaLugares.size();
	}

	public boolean estaVacio() {
		return tamaño() == 0;
	}

	public String generarJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
	}

	public void escribirJSON(String jsonParaGuardar, String archivoDestino) {

		try {
			FileWriter writer = new FileWriter(archivoDestino);
			BufferedWriter writer2 = new BufferedWriter(writer);
			writer2.write(jsonParaGuardar);
			writer2.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static LugaresJSON leerJSON(String archivo) {
		Gson gson = new Gson();
		LugaresJSON ret = null;
		try {
			FileReader archivoJSON = new FileReader(archivo);
			if (archivoJSON != null) {
				BufferedReader br = new BufferedReader(archivoJSON);
				ret = gson.fromJson(br, LugaresJSON.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public void agregarLugar(JsonObject c) {
		if (!contieneObjectoJSON(c))
			listaLugares.add(c);
	}

	private boolean contieneObjectoJSON(JsonObject c) {
		for (int i = 0; i < listaLugares.size(); i++) {
			if (((JsonObject) getLugar(i)).get("Lugar ").equals(c.get("Lugar ")))
				return true;
		}
		return false;
	}

	public void abrirJSONyCopiar() {
		LugaresJSON nuevoJSON = LugaresJSON.leerJSON(rutaDeJSON);
		if (nuevoJSON != null) {
			for (JsonElement l : nuevoJSON.getListaLugares()) {
				agregarLugar((JsonObject) l);
			}
		}
	}

	public void guardarLugaresEnJSON() {
		String nuevoDatosJSON = generarJSON();
		escribirJSON(nuevoDatosJSON, rutaDeJSON);
	}

	public JsonObject transformarEnJson(Lugar l) {
		JsonObject nuevoJSON = new JsonObject();
		nuevoJSON.addProperty("Lugar ", l.getNombre());
		nuevoJSON.addProperty("latitud ", l.getLatitud1());
		nuevoJSON.addProperty("longitud ", l.getLongitud1());
		nuevoJSON.addProperty("provincia ", l.getProvincia());
		return nuevoJSON;
	}

	public boolean comprobarExistenciaNombre(String nombre) {
		for (int i = 0; i < getListaLugares().size(); i++) {
			if (((JsonObject) getListaLugares().get(i)).get("Lugar ").toString().equals(nombre))
				return true;
			if (((JsonObject) getListaLugares().get(i)).get("Lugar ").toString().equals((char) 34 + nombre + (char) 34))
				return true;
		}
		return false;
	}
	
	public JsonElement getLugar(int i) {
		return listaLugares.get(i);
	}

	public String getNombre(int i) {
		String nombre = ((JsonObject) getLugar(i)).get("Lugar ").toString();
		String nombSinComillas = ((JsonObject) getLugar(i)).get("Lugar ").toString().substring(1, nombre.length() - 1);

		return nombSinComillas;
	}

	public Double getLatitud(int i) {
		String latitud = ((JsonObject) getLugar(i)).get("latitud ").toString();
		String latSinComillas = ((JsonObject) getLugar(i)).get("latitud ").toString().substring(0,
				latitud.length() - 1);

		return Double.parseDouble(latSinComillas);
	}

	public Double getLongitud(int i) {
		String longitud = ((JsonObject) getLugar(i)).get("longitud ").toString();
		String longSinComillas = ((JsonObject) getLugar(i)).get("longitud ").toString().substring(0,
				longitud.length() - 1);

		return Double.parseDouble(longSinComillas);
	}
	
	public JsonArray getListaLugares() {
		return listaLugares;
	}
	
	public int JSONsizeNombreMasLargo() {
		int maxLongitud=0;
		
		for (int i = 0; i < listaLugares.size(); i++) {
			if ( ((JsonObject) getLugar(i)).get("Lugar ").toString().length() > maxLongitud ) {
				maxLongitud = ((JsonObject) getLugar(i)).get("Lugar ").toString().length();
			}	
		}
		return maxLongitud;
		
	}

}