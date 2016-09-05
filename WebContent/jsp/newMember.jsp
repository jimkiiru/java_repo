
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
<br />
<br />
<br />
<br />
<br />
<br />
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
<title>Member Registration | DeKUT Clubs Manager</title>
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
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.css">
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
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle Navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp"><i class="fa fa-home"
							aria-hidden="true"></i>&nbsp;HOME</a></li>
					<li><a href="chair.do">DASHBOARD</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="pdf.do">DOWNLOAD FORM</a></li>
					<li><a href="logout.do"><span
							class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<div class="container">
		<center>
			<h3>
				<font style="color: red;">${successMess}</font>
			</h3>
		</center>
		<div class="panel panel-info">
			<div class="panel-heading">
				<span class="lead col-lg-offset-4">MEMBER REGISTRATION FORM </span>
			</div>
			<center>
				<h4>
					<font style="color: red;">N/B.All fields with (*) are
						mandatory fields.</font>
				</h4>
			</center>
			<form:form action="saveMember.do" method="post"
				commandName="newMember">
				<table class="table ">
					<tr>
						<td>
							<div class="form-group col-md-12">
								<div class="col-md-7">
									<label for="Registration Number">Registration Number<font
										style="color: red">*</font></label> <input class="form-control"
										name="regno" placeholder="Registration number" id="reg"
										onkeyup="loaddata();" autofocus />
									<form:errors path="regno" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>
						<td>
							<div class="form-group col-md-12">
								<div class="col-md-7">
									<label for="First Name">First Name<font
										style="color: red">*</font></label> <input class="form-control"
										placeholder="First name" id="fname" name="fname" />
									<form:errors path="fname" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<div class="row">
							<td>
								<div class="form-group col-md-12">
									<div class="col-md-7">
										<label for="Last Name">Last Name<font
											style="color: red">*</font></label> <input class="form-control"
											placeholder="Last name" name="lname" />
										<form:errors path="lname" cssStyle="color:red"></form:errors>
									</div>
								</div>
							</td>
							<td>
								<div class="form-group col-md-12">
									<div class="col-md-7">
										<label for="SurName">Surname<font style="color: red">*</font></label>
										<input class="form-control" placeholder="Surname" name="sname" />
										<form:errors path="sname" cssStyle="color:red"></form:errors>
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
										<label for="Position">Position<font style="color: red">*</font></label>
										<select name="position" class="form-control">
											<option value="select">--Select position--</option>
											<option value="chairperson">Chairperson</option>
											<option value="Vice-chairperson">Vice chairperson</option>
											<option value="treasurer">Treasurer</option>
											<option value="secretary">Secretary</option>
											<option value="Organizing Secretary">Organizing
												Secretary</option>
											<option value="Committee member">Committee Member</option>
											<option value="Regular member">Regular Member</option>
										</select>
										<form:errors path="position" cssStyle="color:red"></form:errors>
									</div>
								</div>
							</td>
							<td>
								<div class="form-group col-md-12">
									<div class="col-md-7">
										<label for="EmailAddress">EmailAddress<font
											style="color: red">(Optional)</font></label>
										<div class="input-group margin-bottom-sm">
											<span class="input-group-addon"><i
												class="fa fa-envelope-o fa-fw"></i></span> <input type="email"
												class="form-control"
												placeholder="Email address eg xyz@gmail.com"
												name="emailaddress" />
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
										<label for="CellPhone">Phone number<font
											style="color: red">*</font></label> <input class="form-control"
											placeholder="Phone number eg 07XXXXXXXX" name="cellphone" />
										<form:errors path="cellphone" cssStyle="color:red"></form:errors>
									</div>
								</div>
							</td>
							<td>
								<div class="form-group col-md-12">
									<div class="col-md-7">
										<label for="Event Name">Club Name<fonts
												style="color: red">*</font></label>
										<form:select cssClass="form-control" path="clubName">
											<form:options items="${clubname}" />
										</form:select>
										<form:errors path="clubName" cssStyle="color:red"></form:errors>
									</div>
								</div>
							</td>
						</div>
					</tr>
					<tr>
						<td colspan="3" align="center"><input class="btn btn-danger"
							type="submit" name="" value="Save"> &nbsp;&nbsp; <input
							type="reset" name="" value="Clear" class="btn btn-warning"></td>
					</tr>
				</table>
			</form:form>
		</div>
		<nav>
			<ul class="pager">
				<li class="previous"><a href="chair.do"><span
						aria-hidden="true">&larr;</span>Previous</a></li>
				<li class="next"><a href="saveClub.do">Next<span
						aria-hidden="true">&rarr;</span></a></li>
			</ul>
		</nav>
	</div>
	<%@include file="footer.jsp"%>
	<%
		}
	%>