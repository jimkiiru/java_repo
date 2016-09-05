<%@include file="taglib_includes.jsp"%>
<%@page import="java.util.*"%>
<%--<%@page import="java.util.List"%>--%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="keywords"
	content="dkut,kuct,kit,dedan kimathi university,dedan kimathi university of technology,kimathi university college,kimathi university of technology" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="Dedan Kimathi University of Technology" />
<meta name="generator"
	content="Joomla! - Open Source Content Management" />
<title>Password Recovery | DeKUT Clubs Manager</title>
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
<link type="text/css" rel="stylesheet" href="css/dekutclubs.css">
<link rel="stylesheet" type="text/css"
	href="font-awesome/css/font-awesome.css">

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
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp"><i
							class="fa fa-home" aria-hidden="true"></i>&nbsp;HOME</a></li>
					
				</ul>
				<ul class="nav navbar-nav navbar-right">

					<li><a href="loginform.do"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<div class="container">
	<h1 style="color:red;">${mess}</h1>
		<div class="panel panel-info">
			<div class="panel-heading">
				<span class="lead col-lg-offset-4">ENTER YOUR USERNAME</span>
			</div>
			<form:form action="forgotpass.do" method="post"
				commandName="forgotPassword">
				<table class="table">
					<tr>
						<td>
							<div class="form-group col-md-6 col-lg-6 col-lg-offset-4">
								<div class="col-md-7">
									<label for="Comment">Username<font style="color:red;">*</font> </label>
									<input type="email" name="username" class="form-control"/>
									<form:errors path="username" cssStyle="color:red"></form:errors>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input class="btn btn-danger"
							type="submit" name="" value="Submit">&nbsp;&nbsp; <input
							type="reset" name="" value="Clear" class="btn btn-warning">
					</tr>
				</table>
			</form:form>
		</div>
		<nav>
			<ul class="pager">
				<li class="previous"><a href="loginform.do"><span
						aria-hidden="true">&larr;</span>Previous</a></li>
				<li class="next disabled"><a href="#">Next<span
						aria-hidden="true">&rarr;</span></a></li>
			</ul>
		</nav>
	</div>
	<%@include file="footer.jsp"%>
	