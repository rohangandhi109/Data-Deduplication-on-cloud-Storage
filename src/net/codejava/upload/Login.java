package net.codejava.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("form-username");
		String pass = request.getParameter("form-password");
		
		//String h_pass=Integer.toString(pass.hashCode());
		
		
		MongoClientURI uri = new MongoClientURI("mongodb://Admin123:admin@cluster0-shard-00-00-bsnfe.mongodb.net:27017,cluster0-shard-00-01-bsnfe.mongodb.net:27017,cluster0-shard-00-02-bsnfe.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");
    	MongoClient mongoClient = new MongoClient(uri);
	    MongoDatabase database = mongoClient.getDatabase("user_data");
	    MongoCollection<Document> collect = database.getCollection("user");
	    
	    if(uname.equals("admin")&& pass.equals("admin"))
	    {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("username", uname);
	   		session.setAttribute("uname",uname );
	    	response.sendRedirect("admin.html");
	    }
	    else
	    {
	    Document find_query= new Document("Username", uname);
	    MongoCursor<Document> cursor = collect.find(find_query).iterator();
	     if(cursor.hasNext())
		 {
	    	 Document doc = cursor.next();
	    	 String pa=String.valueOf(pass.hashCode());
	    	 System.out.println(pa+"\n"+doc.get("Password"));
		   	 if(pa.equals(doc.get("Password")))
		   	 {
			 	 HttpSession session = request.getSession();
		   		 session.setAttribute("username", uname);
		   		 session.setAttribute("uname",uname );
		   		 response.sendRedirect("upload.jsp");
			 }
		 	 else 
		 		 response.sendRedirect("login.jsp");
		 }
	}
	}
}
