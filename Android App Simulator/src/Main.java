import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	
	
	/// Main
	/// Start an infinite loop, and send a random location
	/// as a http request at regular intervals
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Andriod Phone");
		frame.setSize(300, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel idLabel = new JLabel("ID");
		JLabel longitudeLabel = new JLabel("Longitude");
		JLabel latitudeLabel = new JLabel("Latitude");
		JTextField idField = new JTextField(3);
		JTextField longitudeField = new JTextField(10);
		JTextField latitudeField = new JTextField(10);
		JButton button = new JButton("Send Location now.");
		JTextArea logScreen = new JTextArea(20,15);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new random location
				String latitude = "" + new Random().nextGaussian();
				String longitude = "" + new Random().nextGaussian();
				Location location = new Location (0, latitude, longitude, Calendar.getInstance().getTimeInMillis());
				
				// Convert to a JSON format and send it
				logScreen.append("Sending Location.\n");
				sendCurrentLocation(location);
			}
		});
		
		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(idLabel);
		frame.getContentPane().add(idField);
		frame.getContentPane().add(longitudeLabel);
		frame.getContentPane().add(longitudeField);
		frame.getContentPane().add(latitudeLabel);
		frame.getContentPane().add(latitudeField);
		frame.getContentPane().add(button);
		frame.getContentPane().add(logScreen);
		frame.setVisible(true);

		boolean exitLoop = false;
		
		while (!exitLoop) {
			// Create a new random location
			String latitude = "" + new Random().nextGaussian();
			String longitude = "" + new Random().nextGaussian();
			Location location = new Location (0, latitude, longitude, Calendar.getInstance().getTimeInMillis());
			
			// Convert to a JSON format and send it
			logScreen.append("Sending Location.\n");
			sendCurrentLocation(location);
			
			try {Thread.sleep(5000);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	/// @param loc - This will be sent using a http POST request
	public static void sendCurrentLocation(Location loc) {
		HttpURLConnection connection = null;
		
		try {
			// Establish connection
			URL url = new URL("http://localhost:8080/Surveillance/Server"); // URL Goes here
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			// Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
			Gson gson = new GsonBuilder().create();
			wr.writeBytes(gson.toJson(loc)); // Data goes here
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
			
			// Print response
			System.out.println(response.toString());
		}
		catch (Exception e) {System.err.println(e.toString());}
		finally {if (connection != null) connection.disconnect();}
	} // End of sendCurrentLocation
} // End of Class Main

/// This class needs to be the same as in the server.
class Location {
	private int ID;
	private String latitude;
	private String longitude;
	private long timestamp;
	
	public Location(int id, String lat, String lon, long ts) {
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
