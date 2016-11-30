import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

public class Main {
	public static void main(String[] args) {

		boolean exitLoop = false;
		
		while (!exitLoop) {
			String latitude = "" + new Random().nextGaussian();
			String longitude = "" + new Random().nextGaussian();
			Location location = new Location (0, latitude, longitude, Calendar.getInstance());
			
			log(location.toString());
			sendCurrentLocation(location);
			
			try {Thread.sleep(3000);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	public static void sendCurrentLocation(Location loc) {
		HttpURLConnection connection = null;
		
		try {
			URL url = new URL("http://localhost:8080/Hello_World/Server"); // URL Goes here
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			connection.setDoOutput(true);
			
			// Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
			wr.writeBytes(loc.toString()); // Data goes here
			wr.close();
			
			// Get Response
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder response = new StringBuilder();
			String line;
			
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\n');
			}
			rd.close();
			
			System.out.println(response.toString());
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		finally {
			if (connection != null) connection.disconnect();
		}
	}
	
	private static void log(String line) {System.out.println(line);}
} // End of Class Main

class Location {
	private int ID;
	private String latitude;
	private String longitude;
	private Calendar timestamp;
	
	public Location(int id, String lat, String lon, Calendar ts) {
		ID = id;
		latitude = lat;
		longitude = lon;
		timestamp = ts;
	} // End of Location
	
	public String toString() {
		String json = "{";
		json += quotes("ID")        + ": " + ID + separator();
		json += quotes("latitude")  + ": " + quotes(latitude) + separator();
		json += quotes("longitude") + ": " + quotes(longitude) + separator();
		json += quotes("timestamp") + ": " + timestamp.getTime().getTime();
		json += "}";
		return json;
	} // End of toString
	
	private char separator() {return ',';}
	private String quotes(String s) {return '\"' + s + '\"';}
} // End of Class Location
