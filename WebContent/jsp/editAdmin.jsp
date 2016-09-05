
<%
	if ((session.getAttribute("chairId") == null) || (session.getAttribute("chairId") == "")) {
%>
<%@include file="validateLoginHeader.jsp"%>
<br />
<div class="container">
	<div class="panel panel-info">
		<div class="panel-heading">
			<span class="lead col-lg-offset-4">AUTHENTICATION REQUIRED!!!</span>
		</div>
		<center>
			<h3 style="color: red;">You Are Not Logged In!</h3>
			<h3>
				Click <a href="loginform.do">Here</a> To Login
			</h3>
		</center>
	</div>
</div>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<%@include file="footer.jsp"%>
<%
	} else {
%>
<%@include file="taglib_includes.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="keywords"
	content="dkutclubs,dkut,kuct,kit,dedan kimathi university,dedan kimathi university of technology,kimathi university college,kimathi university of technology" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="Dedan Kimathi University of Technology" />
<meta name="generator"
	content="Joomla! - Open Source Content Management" />
<title>Edit Admin | DeKUT Clubs Manager</title>
<!--Images icon-->
<link rel="shortcut icon" href="images/favcon.PNG" />
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/animate.css">
<link href="/templates/shaper_university/favicon.ico"
	rel="shortcut icon" type="image/vnd.microsoft.icon" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!--Reseting all fields in a form after submission-->
<!--Styling for  map-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap core CSS -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.2/css/bootstrap.css"
	rel="stylesheet" media="screen">

<%-- Rev Slider--%>
<script type="text/javascript" src="js/jquery-1.12.2.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<!-- jQuery REVOLUTION Slider  -->
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.revolution.min.js"></script>

<!-- REVOLUTION BANNER CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="rs-plugin/css/slider.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/dekutclubs.css">
<!--End stylemap-->
<SCRIPT type="text/javascript">
	function resetForm() {
		document.form.reset();
	}
</script>
</head>
<body data-spy="scroll" data-target="#my-navbar">
	<%@include file="top.jsp"%>
	<nav class="navbar navbar-default navbar-inverse" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle Navigation</span>
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp"><i class="fa fa-home" aria-hidden="true"></i>HOME</a></li>
					<li><a href="admindash.do">DASHBOARD</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do"><span
							class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<div class="container">
	<center><h3>
			<font style="color:red;">${successMess}</font></h3></center>
		<div class="panel panel-info">
			<div class="panel-heading">
				<span class="lead col-lg-offset-3">EDIT ADMINISTRATOR INFORMATION</span>
			</div>
			<form:form action="updateAdmin.do" method="post"
				commandName="editAdmin">
				<table class="table ">
					<tr>
						<td>
							<div class="form-group col-md-12">
								<div class="col-md-7">
									<label for="Admin Id">Admin Id</label>
									<form:input cssClass="form-control" path="adminid" readonly="true"/>
									<form:errors path="adminid" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>
						<td>
							<div class="form-group col-md-12">
								<div class="col-md-7">
									<label for="Registration Number">First Name</label>
									<form:input cssClass="form-control" path="first_name" />
									<form:errors path="first_name" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>

					</tr>
					<tr>
						<div class="row">
							<td>
								<div class="form-group col-md-12">
									<div class="col-md-7">
										<label for="First Name">Last Name</label>
										<form:input cssClass="form-control" path="lname" />
										<form:errors path="lname" cssStyle="color:red"></form:errors>
									</div>
								</div>
							</td>
						<td>
							<div class="form-group col-md-12">
								<div class="col-md-7">
									<label for="Email Address">Email Address</label>
									<div class="input-group margin-bottom-sm">
											<span class="input-group-addon"><i
												class="fa fa-envelope-o fa-fw"></i></span>
									<form:input cssClass="form-control" path="emailaddress" />
									</div>
									<form:errors path="emailaddress" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>

						</div>
					</tr>
					<tr>
						<div class="row">
							<td>
								<div class="form-group col-md-12">
									<div class="col-md-7">
										<label for="Cell Phone">Cell Phone</label>
										<form:input cssClass="form-control" path="cellphone" />
										<form:errors path="cellphone" cssStyle="color:red"></form:errors>
									</div>
								</div>
							</td>
						<td>
							<div class="form-group col-md-12 hidden">
								<div class="col-md-7">
									<label for="Password">Password</label>
									<form:input cssClass="form-control" path="password" />
									<form:errors path="password" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>

						</div>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="submit" name=""
							class="btn btn-danger" value="Save"> &nbsp;&nbsp;<input
							type="button" class="btn btn-info" value="Delete"
							onclick="javascript:deleteAdmin('deleteAdmin.do?adminid=${editAdmin.adminid}');">
							&nbsp;&nbsp; <input type="button" class="btn btn-warning"
							value="Back" onclick="javascript:go('viewAllAdmins.do');"></td>
					</tr>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<%}%>