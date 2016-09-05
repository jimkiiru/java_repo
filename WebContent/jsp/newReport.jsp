
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
<title>Upload Report | DeKUT Clubs Manager</title>
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
					<li><a href="viewAllReportChair.do">MONTHLY REPORTS</a></li>
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
		<center>
			<h3>
				<font style="color: red;">${successMess}</font>
			</h3>
		</center>
		<div class="panel panel-info">
			<div class="panel-heading">
				<span class="lead col-lg-offset-4">UPLOAD CLUB MONTHLY REPORT</span>
			</div>
			<center>
				<h4>
					<font style="color: red;">N/B.All fields with (*) are
						mandatory fields.</font>
				</h4>
			</center>
			<center>
				<form:form action="saveReport.do" method="post"
					commandName="newReport" enctype="multipart/form-data">
					<table class="table" align="center" id="uploaded-files">
						<tr>
							<div class="row">
								<td>
									<div class="form-group col-lg-offset-4">
										<div class="col-md-5">
											<label for="Email Address">Club Name<font
												style="color: red">*</font></label>
											<form:select cssClass="form-control" path="clubid">
												<form:options items="${clubname}" />
											</form:select>
											
											<form:errors path="clubid" cssStyle="color:red"></form:errors>
										</div>

									</div>
							</div>
							</td>
							</div>
						</tr>
						<tr>
							<div class="row">
								<td>
									<div class="form-group col-lg-offset-4">
										<div class="col-md-5">
											<label for="Password">Upload Reports<font
												style="color: red">*</font></label> <input path="url"
												id="fileupload" class="form-control btn btn-primary"
												name="fileUpload" type="file" name="files[]"
												data-url="saveReport.do" multiple required />
											<form:errors path="url" cssStyle="color:red"></form:errors>
										</div>
									</div>
								</td>
							</div>
						</tr>
						<tr>
							<td colspan="3" align="center"><input type="submit"
								class="btn btn-danger" name="" value="Upload">
								&nbsp;&nbsp; <input type="reset" class="btn btn-warning" name=""
								value="Reset"></td>
						</tr>
					</table>
				</form:form>
			</center>
		</div>
		<nav>
			<ul class="pager">
				<li class="previous"><a href="chair.do"><span
						aria-hidden="true">&larr;</span>Previous</a></li>
				<li class="next"><a href="saveEvent.do">Next<span
						aria-hidden="true">&rarr;</span></a></li>
			</ul>
		</nav>
	</div>
	<br>
	<br>
	<br>
	<br>
	<%@include file="footer.jsp"%>
	<%
		}
	%>