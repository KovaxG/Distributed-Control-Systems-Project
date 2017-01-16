import java.util.Calendar;

public class CalendarTest {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTimeInMillis());
		cal.setTimeInMillis(1);
		System.out.println(cal.getTime());
		
	}
}
