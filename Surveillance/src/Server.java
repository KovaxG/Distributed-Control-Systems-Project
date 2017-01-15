import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseHandler db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
        super();
        
        String connectionString = "jdbc:mysql://localhost:3306/surveillance";
        String user = "root";
        String pass = "root";
        
        db = new DatabaseHandler(connectionString, user, pass);
    } // End of Constructor

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This part will handle the requests comming from the webpage.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.println("<div>");
		out.println("Megvan ez a szar!.");
		out.println("<div>");
		
		//db.saveLocation(0, "test 1", "test 2", Calendar.getInstance());
	} // End of doGet(HttpServletRequest, HttpServletResponse)

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This will handle connections comming from the andriod app.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Connection established!");
		BufferedReader br = request.getReader();
		String line = br.readLine();
		br.close();
		
		try {
			Gson gson = new GsonBuilder().create();
			Location loc = gson.fromJson(line, Location.class);
			
			System.out.println(loc.longitude);
		}
		catch (Exception e) {
			System.out.println("Gson failed.");
		}
		
		PrintWriter pw = response.getWriter();
		pw.println("Nessage Received. Big Brother is Watching. Is this even real?");

	} // End of doPost(HttpServletRequest, HttpServletResponse)

} // End of Class Server

