
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
<%--<%@page import="java.util.List"%>--%>
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
<title>Uploaded Constitution | DeKUT Clubs Manager</title>
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
<script src="js/jquery.1.9.1.min.js"></script>

<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<!--Reseting all fields in a form after submission-->
<!--Styling for  map-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap core CSS -->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.2/css/bootstrap.css"
	rel="stylesheet" media="screen">
<link type="text/css" rel="stylesheet" href="css/dekutclubs.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.css">
<script type="text/javascript" src="js/clubs.js"></script>
<script type="text/javascript" src="js/reports.js"></script>
<script type="text/javascript" src="js/members.js"></script>
<script type="text/javascript" src="js/admin.js"></script>
<script type="text/javascript" src="js/constApprove.js"></script>
<script type="text/javascript" src="js/constDisapprove.js"></script>
<script type="text/javascript" src="js/constitution.js"></script>
<script type="text/javascript" src="js/category.js"></script>
<script type="text/javascript" src="js/events.js"></script>
<script type="text/javascript" src="js/myuploadfunction.js"></script>
<%-- Rev Slider--%>
<script type="text/javascript" src="js/jquery-1.12.2.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<!-- jQuery REVOLUTION Slider  -->
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
<script src="dist/semantic.min.js"></script>

<!-- REVOLUTION BANNER CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="rs-plugin/css/slider.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/dekutclubs.css">
<link rel="stylesheet" type="text/css" class="ui"
	href="dist/semantic.min.css">
<link rel="stylesheet" href='<c:url value="css/pure-0.4.2.css"/>'>
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
							aria-hidden="true"></i>HOME</a></li>
					<li><a href="admindash.do">DASHBOARD</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.do"><span
							class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<center>
			<h3>
				<font style="color: red;">${successMess}</font>
			</h3>
		</center>
		<form action="searchConstitutions.do" method="post">
			<div class="panel panel-info">
				<div class="panel-heading">
					<span class="lead">UPLOADED CONSTITUTIONS</span> &nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
						placeholder="Search by club name" name="clubname" /> &nbsp;&nbsp;<input
						type="submit" class="btn btn-danger" value="Search" />
				</div>
		</form>
		<table class="table pure-table pure-table-bordered pure-table-striped">
			<tr>
				<th>CLUB NAME</th>
				<th>CONSTITUTION</th>
				<th>UPLOAD DATE</th>
				<th>DOWNLOAD</th>
				<th>APPROVE</th>
				<th>DISAPPROVE</th>
				<th>DELETE</th>
			</tr>
			<c:if test="${empty SEARCH_CONTACTS_RESULTS_KEY}">
				<tr>
					<td colspan="4"><font style="color: red;">No Results
							found</font></td>
				</tr>
			</c:if>
			<c:if test="${! empty SEARCH_CONTACTS_RESULTS_KEY}">
				<c:forEach var="consti" items="${SEARCH_CONTACTS_RESULTS_KEY}">
					<tr>
						<%--<td><c:out value="${consti.fieldid}"></c:out></td>--%>
						<%--<td><c:out value="${consti.chairid}"></c:out></td>--%>
						<td><c:out value="${consti.clubname}"></c:out></td>
						<td><c:out value="${consti.url}"></c:out></td>
						<td><c:out value="${consti.currentDate}"></c:out></td>
						<td>&nbsp;<a href="downloadConstitution.do?url=${consti.url}"><i
								class="fa fa-download"></i></a> &nbsp;
						</td>
						<td><a
							href="javascript:approveConstitution('approveConstitution.do?logid=${consti.chairid}');"><i
								class="fa fa-check" aria-hidden="true"></i> </a> &nbsp;&nbsp;</td>
						<td><a
							href="disApproveConstitutio.do?logid=${consti.chairid}"><i
								class="fa fa-times"></i></a> <%
 	session.setAttribute("logid", "${consti.chairid}");
 %> &nbsp;&nbsp;&nbsp;</td>
						<td><a class="btn btn-danger"
							href="javascript:deleteConstitution('deleteConstitution.do?fieldid=${consti.fieldid}');"><i
								class="fa fa-trash-o" aria-hidden="true"></i></a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		</center>
		
	</div>
	<nav>
			<ul class="pager">
				<li class="previous"><a href="admindash.do"><span
						aria-hidden="true">&larr;</span>Previous</a></li>
				<li class="next"><a href="viewDeactivatedClubs.do">Next<span
						aria-hidden="true">&rarr;</span></a></li>
			</ul>
		</nav>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="footer.jsp"%>
	<% }%>