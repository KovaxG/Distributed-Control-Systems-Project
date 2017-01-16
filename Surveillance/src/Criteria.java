/// This is sent by the javascript aplication
public class Criteria {
	private int deviceId;
	private String startDate;
	private String endDate;
	
	public Criteria(int id, String start, String end) {
		deviceId = id;
		startDate = start;
		endDate = end;
	} // End of Constructor
	
	public String toString() {
		return "(" + deviceId + ", " + startDate + ", " + endDate + ")";
	} // End of toString
} // End of Class Criteria