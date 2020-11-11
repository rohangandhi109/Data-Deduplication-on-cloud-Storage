package net.codejava.upload;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AWSUploadUtility {
	
public void uploadfile(AWSCredentials credentials,String bucketName, String keyName,InputStream is,ObjectMetadata metadata)
 {		
	@SuppressWarnings("deprecation")
	AmazonS3 s3client = new AmazonS3Client(credentials);
	System.out.println("Uploading a new object to S3 from a file\n");
	s3client.putObject(bucketName,keyName,is,metadata);
  }

public ArrayList<String> viewFile(AWSCredentials credentials,String bucketName,String uname)
{		
	try{
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
	    ArrayList<String> files=new ArrayList<>();
		ObjectListing objects = s3client.listObjects(bucketName);
		do {
		        for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
		            files.add(objectSummary.getKey());
		        objects = s3client.listNextBatchOfObjects(objects);
		} while (objects.isTruncated());
		
		for(int i=0;i<files.size();i++)
		{
			String f=files.get(i);
			System.out.println("()"+f);
		if(!f.startsWith(uname))
			{	
				System.out.println("///"+f);
				files.remove(files.get(i));	
				i--;
			}
			else
			{
				System.out.println("**"+f);
				f=before(f,".csv");
				System.out.println("**"+f);
				f=after(f,uname);
				System.out.println("**"+f);
				files.set(i, f);
			}
		}
		
		
	 return files; 
	}
	catch (Exception e)
	{
		return null;
	}
 }

public String before(String a, String b)
{
	int pos=a.indexOf(b);
	if(pos==-1)
		return null;
	return a.substring(0,pos);
}

public String after(String a, String b)
{
	String x=a.replace(b+"/", "");
	return x;
}
public ArrayList<String> viewFileAdmin(AWSCredentials credentials,String bucketName)
{		
	try{
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);
	    ArrayList<String> files=new ArrayList<>();
	    int i=0;
		ObjectListing objects = s3client.listObjects(bucketName);
		do {
		        for (S3ObjectSummary objectSummary : objects.getObjectSummaries())
		            files.add(objectSummary.getKey());
		        objects = s3client.listNextBatchOfObjects(objects);
		} while (objects.isTruncated());
		for(i=0;i<files.size();i++)
		{
			    String f=files.get(i);
			    if(f.contains("/"))
			    	f=before(f,"/");
				files.set(i,f);	
		}
		Set<String> hs = new HashSet<>();
		hs.addAll(files);
		files.clear();
		files.addAll(hs);
		return files; 
	}
	
	catch (Exception e)
	{
		return null;
	}
 }


}