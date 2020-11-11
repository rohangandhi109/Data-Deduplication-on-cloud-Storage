package net.codejava.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> files=null;
		String ch=request.getParameter("chunks");
		
		Properties prop = new Properties();
		InputStream propstream = new FileInputStream(getServletContext().getRealPath("WEB-INF/s3.properties"));
		prop.load(propstream);
		AWSUploadUtility s3client = new AWSUploadUtility();
		AWSCredentials credentials = new BasicAWSCredentials(
				prop.getProperty("AWSAccessKeyId"),
				prop.getProperty("AWSSecretKey"));
		HttpSession session=request.getSession();
		String uname=session.getAttribute("uname").toString();
		if(!ch.equals("D"))
		{
			files=s3client.viewFileAdmin(credentials,"metadataa");
			request.setAttribute("ch","Total Number Of Users are");
		} 
		else
		{		
			files=s3client.viewFileAdmin(credentials,"dhanraj");
		    request.setAttribute("ch","Total Number Of Chunks are");
		}	
		int fs=files.size();
		for(int i=0;i<fs;i++)
		{		
				request.setAttribute("files"+(i+1),files.get(i));
				System.out.println(files.get(i));
				request.getRequestDispatcher("/outputadmin.jsp");				
		}	
			request.setAttribute("filesize",fs);
			request.setAttribute("username", uname);
			request.getRequestDispatcher("/outputadmin.jsp").forward(request,response);
	}

}
