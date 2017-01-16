import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DatabaseHandler db;
	
	// Database stuff
	private String connectionString = "jdbc:mysql://localhost:3306/surveillance";
	private String user = "root";
	private String pass = "root";
       
    /**
     * @see HttpServlet#HttpServlet()
     * Create a DatabaseHandler object that will be used in the doGet and doPost.
     */
    public Server() {
        super();
        db = new DatabaseHandler(connectionString, user, pass);
    } // End of Constructor

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * This part will handle the requests comming from the webpage.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getContentType(); // EZ VALAMIERT NULL
		BufferedReader br = request.getReader();
		String line = br.readLine();
		br.close();
		
		System.out.println(line);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(line);
		
		ArrayList<Location> list = db.getLocationsForUserId(0);
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
		}
		
		//out.println("<div>");
		//Gson gson = new GsonBuilder().create();
		//out.println(gson.toJson(list));
		//out.println("<div>");
		
		//out.println("<p>Nothing to see here.</p>");
	} // End of doGet(HttpServletRequest, HttpServletResponse)

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * This will handle connections comming from the andriod app.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String line = br.readLine();
		br.close();
		
		try {
			Gson gson = new GsonBuilder().create();
			Location loc = gson.fromJson(line, Location.class);
			
			System.out.println(loc);
			db.saveLocation(loc);
			
			PrintWriter pw = response.getWriter();
			pw.println("Message Received. Big Brother is Watching.");
		}
		catch (Exception e) {
			System.out.println("Gson failed.");
			PrintWriter pw = response.getWriter();
			pw.println("Message Not Received.");
		}
	} // End of doPost(HttpServletRequest, HttpServletResponse)
} // End of Class Server

