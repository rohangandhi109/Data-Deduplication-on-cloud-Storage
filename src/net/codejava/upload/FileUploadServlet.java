package net.codejava.upload;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // checks if the request actually contains upload file
    	 HttpSession session = request.getSession();
   		 String uname=session.getAttribute("username").toString();
		System.out.println("before if !!!!!");
        if(ServletFileUpload.isMultipartContent(request))
        {
        		FileItemFactory factory = new DiskFileItemFactory();
        		ServletFileUpload upload = new ServletFileUpload(factory);
        		Iterator<FileItem> iterator = null;
        		InputStream fileIS=null;
        		int i=0;
        		long size=0;
        		String filename=null,fname=null;
        		try {
        		iterator = upload.parseRequest(request).iterator();
        		} catch (FileUploadException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        		}

        		while (iterator.hasNext())
        		{
        			FileItem item = iterator.next();
        			if (!item.isFormField())
        			{
        		      
        				size=item.getSize();
        				filename = new File(item.getName()).getName();
        				fileIS=item.getInputStream();
        				fname=item.getName();
        		
        			}
        		}
        		Properties prop = new Properties();
    			InputStream propstream = new FileInputStream(getServletContext().getRealPath("WEB-INF/s3.properties"));
    			prop.load(propstream);
    			AWSCredentials credentials = new BasicAWSCredentials(
    			prop.getProperty("AWSAccessKeyId"),
    			prop.getProperty("AWSSecretKey"));
    			System.out.println("Chunking!!!!!!");
    			
    			try {
					Chunking.chunk(filename,credentials,fname,uname,fileIS,size);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (CryptoException e) {
					e.printStackTrace();
				}
    			AWSUploadUtility s3client=new AWSUploadUtility();
    			ArrayList<String> files=s3client.viewFile(credentials,"metadataa",session.getAttribute("uname").toString());
    			
    			
    			/*
    			if(files.contains(fname))
    			{
    				int fs=files.size();
    				for(i=0;i<fs;i++)
    				{		
    						request.setAttribute("files"+(i+1),files.get(i));
    						System.out.println(files.get(i));
    						request.getRequestDispatcher("/output.jsp");				
    				}	
    					request.setAttribute("filesize",fs);
    					request.setAttribute("error","FILE ALREADY PRESENT");
    					request.getRequestDispatcher("/output.jsp").forward(request,response);
    					request.setAttribute("message", files);
    		
    			}
    			else
    			{
    				Chunking.chunk(filename,credentials,fname,uname,fileIS,size);
    				request.setAttribute("message","else");
    			}*/
        		
			
        }
        else 
        	request.setAttribute("message", "Sorrry Servlet");
        
        request.setAttribute("uname", uname);
        request.getRequestDispatcher("Viewprofile").forward(request, response);
    }
}