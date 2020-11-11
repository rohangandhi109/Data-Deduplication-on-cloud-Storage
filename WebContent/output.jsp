<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CloudBox</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/material-kit.css" rel="stylesheet"/>
    <link href = "assets/css/style2.css" rel = "stylesheet"/>
    <script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
</head>
<body style="background-color:  #f2f3f4;">
<nav class = "navbar navbar-info " role = "navigation">
	<div class = "container">
		<div class = "navbar-header" style="color: #212f3d; font-weight: bolder">
			<a class = "navbar-brand" href = "#"><b>CloudBoxPro</b></a>
		</div>	

		<div class = "collapse navbar-collapse">
			<ul class = "nav navbar-nav navbar-right" style="color: #212f3d;">
				<li class = "active">
					<form action = "Viewprofile" method = "post">
						<button class = "btn btn-info" type = "submit" style="color: #273746; font-weight: bold;letter-spacing: 0.0625em; margin-top: 4px;">My Files</button> 
						
					</form>
				</li>
				<li>
					<a href = "upload.jsp"><b>Upload</b></a>
				</li>
				<li class="dropdown">
        			<a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>WELCOME, ${uname}</b><b class="caret"></b></a>
        			<ul class="dropdown-menu">
					  <li><a href="login.jsp"><b>Logout</b></a></li>
        			</ul>
        		</li>
			</ul>
		</div>
	</div>	
</nav>
<hr>

<br>
<%
	int fs =(int) request.getAttribute("filesize");
	if(fs==0)
		out.println("<h1>No Files Exists</h1>");

    int i=0;
    Object f=null;
    Object fs1=fs;
%>
<div class = "container" style="border: 0.5px solid; background-color:  #fdfefe; border-color:  #b2babb;">
	<div class = "row" style="border: 1px solid; border-radius: 10px; height: 50px; width: 96%; margin-top: 10px; margin-left: 22px;border-color:  #b2babb;">
		<div class = "col-sm-6" style = "width: 48%;">
			<input type = "hidden" class = "hiddenArea" value = "<%=fs1%>">
				<label class = "fileName" style = "margin-left: 30px; margin-top: 10px;font-family: 'Varela Round',sans-serif; letter-spacing: 0.0625em;font-weight: bold; font-size: 20px;">No of. Files are : <%=fs1%></label>
		</div>			
	</div>
<%     
	for(i=1;i<fs+1;i++)
	{%>
		<%f = request.getAttribute("files"+i);%>		
		<form action = "Download" method = "post">
				<div class = "row" style="border: 1px solid; border-radius: 10px; height: 50px; width: 96%; margin-top: 10px; margin-left: 22px;border-color:  #b2babb;">
					<div class = "col-sm-6" style = "width: 48%;">
						<input type = "hidden" name = "knm" class = "hiddenArea" value = "<%=f%>">
						<label class = "fileName" style = "margin-left: 30px; margin-top: 10px;font-family: 'Varela Round',sans-serif; letter-spacing: 0.0625em"><%=f%></label>
					</div>
					<div class = "col-sm-6" style = "width: 48%;">
						<button type = "submit" class = "btn btn-success btn-round btn-sm" style="color: #273746; font-family: 'Varela Round',sans-serif; font-weight: bold; letter-spacing: 0.0625em; margin-left: 150px;">
							Download
						</button>
					</div>
				</div>
		</form>
		<form action = "Delete" method = "post">
			<input type = "hidden" name = "knm" class = "hiddenArea" value = "<%=f%>">
			<button type="submit" class = "btn btn-success btn-round btn-sm" style="color: #273746; font-family: 'Varela Round',sans-serif; font-weight: bold; letter-spacing: 0.0625em; margin-left: 900px; margin-top: -35px;" type = "submit">
				Delete
			</button>
		</form>
	    <%out.print("<br>");%>
	<%}
%>
</div>

</body>
</html>