package net.codejava.upload;

import org.bson.Document;

import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class Dedup {
	
	public static String Search1(String hash,MongoDatabase database)
	{
		String h=hash.substring(38,40);
		MongoCollection<Document> collect = database.getCollection(h);
		    
		Document find_query= new Document("Hash", hash);
		MongoCursor<Document> cursor = collect.find(find_query).iterator();
		 if(cursor.hasNext())
		 {
			 Document doc = cursor.next();
			 try
			 {
				 int count=doc.getInteger("Count");
				 collect.updateOne(find_query, new Document("$set",new Document("Count",++count)));
			 }
			 catch(Exception e)
			 {
				 collect.updateOne(find_query, new Document("$set",new Document("Count",0)));
			 }
			 return (String) doc.get("Hash");
		 }
		 else
			 return null;
			
	}	
	public static void update(String hash,MongoDatabase database)
	{
		
		String h=hash.substring(38,40);
		MongoCollection<Document> collect = database.getCollection(h);
		
		JsonObject json=new JsonObject();
		json.addProperty("Hash", hash);
		json.addProperty("Count",0);
		collect.insertOne(Document.parse(json.toString()));
		
}
	
}
