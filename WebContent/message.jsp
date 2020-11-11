<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Result</title>
</head>
<body>
    <center>
        <h2>${message}</h2>
        <div>
        File Name : ${uname}
        </div>
    </center>
    
<h1><a href="upload.jsp">Upload New File</a></h1><br><br>
<form action="Viewprofile" method="post">
<input type="submit" value="View Profile">

	</form>
	
    
</body>
</html>