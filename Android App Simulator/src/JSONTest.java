import com.google.gson.*;

/// For some reason this does not work in the servlett.
/// It does however work here. What do you know.
public class JSONTest {
	public static void main (String[] args) {
		Wat original = new Wat(0, "1", "2", 3);
		Gson gson = new GsonBuilder().create();
		String jsonFormat = gson.toJson(original);
		System.out.println(jsonFormat);
		
		Wat sent = gson.fromJson(jsonFormat, Wat.class);
		System.out.println(sent.toString());
	} // End of MAIN
} // End of JSONTest

/// This class needs to be the same as in the server.
class Wat {
	private int ID;
	private String latitude;
	private String longitude;
	private long timestamp;
	
	public Wat(int id, String lat, String lon, long ts) {
		ID = id;
		latitude = lat;
		longitude = lon;
		timestamp = ts;
	} // End of Location
	
	/// Actually to DIY JSON
	public String toString() {
		String json = "{";
		json += quotes("ID")        + ": " + ID + separator();
		json += quotes("latitude")  + ": " + quotes(latitude) + separator();
		json += quotes("longitude") + ": " + quotes(longitude) + separator();
		json += quotes("timestamp") + ": " + timestamp;
		json += "}";
		return json;
	} // End of toString
	
	private char separator() {return ',';}
	private String quotes(String s) {return '\"' + s + '\"';}
} // End of Class Location
