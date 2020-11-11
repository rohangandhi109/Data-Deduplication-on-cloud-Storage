package net.codejava.upload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;



@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties prop = new Properties();
		InputStream propstream = new FileInputStream(getServletContext().getRealPath("WEB-INF/s3.properties"));
		prop.load(propstream);
		AWSCredentials credentials = new BasicAWSCredentials(
				prop.getProperty("AWSAccessKeyId"),
				prop.getProperty("AWSSecretKey"));
		String bucketName="metadataa";
		String key=request.getParameter("knm");
		String final0="final0.txt";
		String final1="final1.txt";
		
		HttpSession session = request.getSession();
  		String uname=session.getAttribute("username").toString();
		System.out.println("username : "+uname);
		
		String kn=uname+"/"+key+".csv";
		System.out.println("Download_file : "+kn);
		
		@SuppressWarnings("deprecation")
		AmazonS3 s3client = new AmazonS3Client(credentials);

		
		S3Object getobj = s3client.getObject(new GetObjectRequest(bucketName,kn));
		
		BufferedReader reader=new BufferedReader(new InputStreamReader(getobj.getObjectContent()));
		String line1=null,line2=null;
		int i=0;		
		String k = "ABCDEFKLPOBEUWLM";
		String f12=System.getProperty("java.io.tmpdir");
				
		File f1=new File(f12+"/"+key);
		
		File f3=new File(f12+"/"+final1);
		PrintWriter pw=null;
		FileReader fr=null;
		PrintWriter pwfinal=new PrintWriter(f3);
		pw=new PrintWriter(new FileOutputStream(f1));
		
		while((line1=reader.readLine())!=null)
		{
			
			
			if(!line1.equals("Hash"))
			{
				
				File f2=new File(f12+"/"+final0);
				
				
				S3Object myobj=s3client.getObject(new GetObjectRequest("dhanraj",line1));
				BufferedReader reader1=new BufferedReader(new InputStreamReader(myobj.getObjectContent()));
				
				System.out.println("Hash---"+i+"---"+line1);
				
				while((line2=reader1.readLine())!=null)
				{
					//String decrypt=AES.decrypt(line2,k);
					pw.println(line2);
					
				}	
				
				
				
			}	
			i++;
			
		}
		pw.close();
		
		pwfinal.close();        
		File downloadFile = f1;
		
		
		FileInputStream inStream = new FileInputStream(downloadFile);
    
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename="+key, downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }      
       
        inStream.close();
        f3.delete();
        outStream.close();  
        
		System.out.println("Complete!!!!");
		
	}

}
