public class Location {
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
	
	public int getID() {return ID;}
	public String getLat() {return latitude;}
	public String getLon() {return longitude;}
	public long getTS() {return timestamp;}
	
	/// Actually to DIY JSON
	public String toString() {
		return "(" + ID + ", " + latitude + ", " + longitude + ", " + timestamp + ")";
	} // End of toString
} // End of Class Location

