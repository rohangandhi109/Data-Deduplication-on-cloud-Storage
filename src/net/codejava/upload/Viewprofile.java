package net.codejava.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

@WebServlet("/Viewprofile")
public class Viewprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> files=null;
		Properties prop = new Properties();
		InputStream propstream = new FileInputStream(getServletContext().getRealPath("WEB-INF/s3.properties"));
		prop.load(propstream);
		AWSUploadUtility s3client = new AWSUploadUtility();
		AWSCredentials credentials = new BasicAWSCredentials(
				prop.getProperty("AWSAccessKeyId"),
				prop.getProperty("AWSSecretKey"));
		HttpSession session=request.getSession();
		String uname=session.getAttribute("uname").toString();
		files=s3client.viewFile(credentials,"metadataa",session.getAttribute("uname").toString());
		int fs=files.size();
		for(int i=0;i<fs;i++)
		{		
				request.setAttribute("files"+(i+1),files.get(i));
				System.out.println(files.get(i));
				request.getRequestDispatcher("/output.jsp");				
		}	
			request.setAttribute("filesize",fs);
			request.setAttribute("username", uname);
			request.getRequestDispatcher("/output.jsp").forward(request,response);
		
	}	

}