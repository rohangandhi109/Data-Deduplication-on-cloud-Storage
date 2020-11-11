<!DOCTYPE html>
<html>
<head>
	<title>Upload Page</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/material-kit.css" rel="stylesheet"/>
    <link href = "assets/css/style2.css" rel = "stylesheet"/>
    <script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    	$(document).ready(function(){
        	$('input[type="file"]').change(function(e){
            	var fileName = e.target.files[0].name;
            	alert('The file "' + fileName +  '" has been selected.');
        });
    });

</script>
</head>
<body style="background-color:#eaeded;">
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
					<a href = "#"><b>Upload</b></a>
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
<div class = "container" style="border: 0.5px solid; background-color:  #fdfefe; border-color:  #b2babb;">
	<form method="post" action="uploadFile" enctype="multipart/form-data">
		<div class = "wrapper">
			<div class = "container">
				<div class = "row">
					<div class = "col-lg-6 col-md-8">
						<h3>Upload your File</h3>
						<div class = "inner">
							<p>Drag and Drop your file here to upload</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class = "wrapper1">
			<div class = "container">
				<div class = "row">
					<div class = "col-lg-6 col-md-8">
						<label id = "browseFile">
							Browse File to Upload
								<input type = "file" name = "uploadFile" class = "inputFile" multiple="multiple" />
						</label>	
					</div>	
				</div>
			</div>
			<div class = "container" style="margin-left: 200px; margin-top: 30px; font-family: 'Varela Round',sans-serif;">
				<button type = "submit" class = "btn btn-info btn-round btn-lg" style="color: #273746; font-weight: bold;letter-spacing: 0.0625em;">Upload</button>
			</div>
		</div>
	</form>
</div>

<footer class = "footer">
	<div class = "container" style="text-align: center; font-family: 'Varela Round',sans-serif; letter-spacing: 0.0625em;">
		Developed by Ritwik, Chaitanya, Dhanraj, Rohan
	</div>
</footer>
</body>
</html>