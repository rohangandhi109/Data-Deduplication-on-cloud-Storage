package net.codejava.upload;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


@WebServlet("/Delete")
public class Delete extends HttpServlet  {

    private static String bucketName = "dhanraj";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	MongoClientURI uri = new MongoClientURI("mongodb://Admin123:admin@cluster0-shard-00-00-bsnfe.mongodb.net:27017,cluster0-shard-00-01-bsnfe.mongodb.net:27017,cluster0-shard-00-02-bsnfe.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");
    	MongoClient mongoClient = new MongoClient(uri);
	    MongoDatabase database = mongoClient.getDatabase("metadata"); 

    	//AWSCredentials credentials = new BasicAWSCredentials("##","####");
        AmazonS3 s3Client = new AmazonS3Client(credentials);
        HttpSession session = request.getSession();
  		String kn=session.getAttribute("username").toString()+"/"+request.getParameter("knm")+".csv";
        
        System.out.println(kn);
    		
        S3Object getobj = s3Client.getObject(new GetObjectRequest("metadataa",kn));
    		
    	BufferedReader reader=new BufferedReader(new InputStreamReader(getobj.getObjectContent()));
    	String line1=null;
    		
    	while((line1=reader.readLine())!=null)
    	{
    		if(!line1.equals("Hash"))
    		{
    			System.out.println("length"+line1.length());
    			System.out.println(line1);
    			String h=line1.substring(38,40);
    			System.out.println(h);
    			MongoCollection<Document> collect = database.getCollection(h);
    		
    			Document find_query= new Document("Hash", line1);
    			MongoCursor<Document> cursor = collect.find(find_query).iterator();
    			if(cursor.hasNext())
    			{
    				Document doc = cursor.next();
    				int count=doc.getInteger("Count");
    				if(count==0)
    				{
    					s3Client.deleteObject(new DeleteObjectRequest(bucketName, line1));
    					collect.deleteOne(find_query);
    				}
    				else
    					collect.updateOne(find_query, new Document("$set",new Document("Count",--count)));
    			}
    		}
    	}	
    	s3Client.deleteObject(new DeleteObjectRequest("metadataa",kn));
    	System.out.println("COMPLETE!!!!!!!");
    	
    	request.setAttribute("uname", session.getAttribute("username").toString());
        request.getRequestDispatcher("Viewprofile").forward(request, response);
    }
}