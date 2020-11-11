package net.codejava.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@WebServlet("/signUp")
public class signUp extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uname = request.getParameter("form-username");
		String name = request.getParameter("form-name");
		String email = request.getParameter("form-email");
		String pass = request.getParameter("form-password");
		
		MongoClientURI uri = new MongoClientURI("mongodb://Admin123:admin@cluster0-shard-00-00-bsnfe.mongodb.net:27017,cluster0-shard-00-01-bsnfe.mongodb.net:27017,cluster0-shard-00-02-bsnfe.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");
    	MongoClient mongoClient = new MongoClient(uri);
	    MongoDatabase database = mongoClient.getDatabase("user_data");
	    MongoCollection<Document> collect = database.getCollection("user");
	    
	    JsonObject json = new JsonObject();
	    json.addProperty("Username", uname);
	    json.addProperty("Password",String.valueOf(pass.hashCode()));
	    json.addProperty("Name", name);
	    json.addProperty("Email", email);
	    
	    collect.insertOne(Document.parse(json.toString()));
	    
	    response.sendRedirect("login.jsp");
	
	}

}
