<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Login Page</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

	<!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/material-kit.css" rel="stylesheet"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="assets/js/jquery.min.js"></script>
    <script>  
	function validateform(){  
		var name=document.myLogin.form-username.value;  
		var password=document.myLogin.form-password.value;  
  
		if (name==null || name==""){  
  		alert("Name can't be blank");  
  		return false;  
	}
	else if(password.length == ""){  
  		alert("Password must be at least 6 characters long.");  
  		return false;  
  	}  
}  
</script>  
</head>
	
<body class = "signup-page">
<!-- Navbar will come here -->
	<nav class = "navbar navbar-transparent navbar-absolute" role = "navigation">
		<div class = "container">
		<div class = "navbar-header"> 
			<button type = "button" class = "navbar-toggle" data-toggle = "collapse">
			<span class = "sr-only">Toggle navigation</span>
			<span class = "icon-bar"></span>
			<span class = "icon-bar"></span>
			<span class = "icon-bar"></span>
			</button>	
			<a class = "navbar-brand" href = "#" style="font-family: 'Old Standard TT',serif; font-size: 48px;"><b>CloudBoxPro</b></a>
		</div>		

		<div class = "collapse navbar-collapse">
			<ul class = "nav navbar-nav navbar-right" style="font-family: 'Varela Round', sans-serif; letter-spacing: 0.0625em;">
				<li class = "active"><a data-toggle = "tab" href = "#login"><b>Login</b></a></li>
				<li><a data-toggle = "tab" href = "#signup"><b>Sign Up</b></a></li>	
				</ul>
			</ul>
		</div>
		<hr>
		</div>

	</nav>
<!-- end navbar -->

	<div class="wrapper">
		<div class="header header filter" style="background-image: url('assets/img/4.jpg.jpg'); background-size: cover; background-position: top center;">
			<div class = "container">
				<div class = "row">	
					<div class = "tab-content">
						<div class = "col-sm-5 tab-pane fade" id = "signup" style = "padding-top: 50px;">
							<div class = "card card-signup" style="margin-left: 300px; padding-bottom: 30px;">
								<form action = "signUp" method = "post">
									<div class = "header header-info text-center">
										<h4>Sign Up</h4>
									</div>
									<div class = "content">
										<div class = "input-group">
											<span class = "input-group-addon">
												<i class = "material-icons">tag_faces</i>
											</span>
											<div class = "form-group label-floating">
												<label class = "control-label">Name</label>
												<input type="text" class = "form-control" name="form-name">
											</div>
										</div>

										<div class = "input-group">
											<span class = "input-group-addon">
												<i class = "material-icons">person</i>
											</span>
											<div class = "form-group label-floating">
												<label class = "control-label">Username</label>
												<input type="text" class = "form-control" name="form-username">
											</div>
										</div>

										<div class = "input-group">
											<span class = "input-group-addon">
												<i class = "material-icons">email</i>
											</span>	
											<div class = "form-group label-floating">
												<label class = "control-label">Email</label>
												<input type="text" class = "form-control" name="form-email">
											</div>	
										</div>


										<div class = "input-group">
											<span class = "input-group-addon">
												<i class = "material-icons">lock</i>
											</span>
											<div class = "form-group label-floating">
												<label class = "control-label">Password</label>	
												<input type="password" class = "form-control" name="form-password">
											</div>
										</div>
									</div>	
									<div class = "footer text-center">
										<button type = "submit" class = "btn btn-info btn-round btn-lg"><b>Sign In</b></a>
									</div>
								</form>
							</div>
						</div>

						<div class = "col-sm-5 tab-pane fade in active" id = "login" style = "padding-top: 50px;">
							<div class = "card card-signup" style="margin-left: 300px; padding-bottom: 30px;">
								<form action = "login" method = "post" name = "myLogin" onsubmit="return validateform();">
									<div class = "header header-info text-center">
										<h4>Login</h4>
									</div>
									<div class = "content">
										<div class = "input-group">
											<span class = "input-group-addon">
												<i class = "material-icons">person</i>
											</span>
											<div class = "form-group label-floating">
												<label class = "control-label" style = "font-family: 'Varela Round', sans-serif; letter-spacing: 0.0625em;">Username</label>
												<input type="text" class = "form-control" name = "form-username">
											</div>
										</div>

										<div class = "input-group">
											<span class = "input-group-addon">
												<i class = "material-icons">lock</i>
											</span>
											<div class = "form-group label-floating">
												<label class = "control-label" style = "font-family: 'Varela Round', sans-serif; letter-spacing: 0.0625em;">Password</label>	
												<input type="password" class = "form-control" name = "form-password">
											</div>
										</div>
									</div>	
									<div class = "footer text-center">
										<button type = "submit" class = "btn btn-info btn-round btn-lg"><b>Login</b></button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>	
			<footer class = "footer">
				<div class = "container" style="text-align: right; font-family: 'Varela Round',sans-serif; margin-top: 100px; color:  #fdfefe; letter-spacing: 0.0625em;">
					Developed by Ritwik, Chaitanya, Dhanraj, Rohan
				</div>
			</footer>
		</div>
	<!-- you can use the class main-raised if you want the main area to be as a page with shadows -->
	</div>
</body>

	<!--   Core JS Files   -->
	<script src="assets/js/jquery.min.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/js/material.min.js"></script>

	<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
	<script src="assets/js/nouislider.min.js" type="text/javascript"></script>

	<!--  Plugin for the Datepicker, full documentation here: http://www.eyecon.ro/bootstrap-datepicker/ -->
	<script src="assets/js/bootstrap-datepicker.js" type="text/javascript"></script>

	<!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
	<script src="assets/js/material-kit.js" type="text/javascript"></script>

</html>
