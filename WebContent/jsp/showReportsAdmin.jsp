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
<title>Monthly Reports | DeKUT Clubs Manager</title>

<!--Images icon-->
<link rel="shortcut icon" href="images/favcon.jpg" />
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
<script type="text/javascript" src="js/activateClub.js"></script>
<%-- Rev Slider--%>

<script type="text/javascript" src="js/jquery-1.12.2.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<!--jQuery REVOLUTION Slider  -->

<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.revolution.min.js"></script>

<!--REVOLUTION BANNER CSS SETTINGS -->

<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="rs-plugin/css/slider.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/dekutclubs.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
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
	<nav class="navbar navbar-custom">
		<hr class="hr-style">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="navbar-nav">
					<li><a href="index.jsp"><i class="fa fa-home"
							aria-hidden="true"></i>HOME</a>&nbsp;&nbsp;</li>
					<li><a href="admindash.do">DASHBOARD</a></li>
				</ul>
				<ul class="navbar-nav navbar-right">

					<li><a href="logout.do"><span
							class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<%--Gallery--%>
	<br>
	<div class="container">
		<form action="searchReports.do" method="post">
			<div class="panel-heading">
				<span class="lead">UPLOADED REPORTS</span> &nbsp;&nbsp;
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
					placeholder="Search Report by club name" name="clubid" size="25" />
				<input type="submit" class="btn btn-danger" value="Search" />
			</div>
		</form>
		<table class="table pure-table pure-table-bordered pure-table-striped">
			<tr>
				<th>CLUB NAME</th>
				<th>UPLOAD DATE</th>
				<th>REPORT NAME</th>
				<th>DOWNLOAD</th>
				<th>DELETE</th>
			</tr>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4"><font style="color: red;">No Results
							found</font></td>
				</tr>
			</c:if>
			<c:if test="${! empty list}">
				<c:forEach var="report" items="${list}">
					<tr>
						<!--<td><c:out value="${report.count}"></c:out></td>  -->
						<td><c:out value="${report.clubid}"></c:out></td>
						<td><c:out value="${report.uploaddate}"></c:out></td>
						<td><c:out value="${report.url}"></c:out></td>
						<td>&nbsp;&nbsp;&nbsp;<a
							href="downloadReport.do?url=${report.url}"><span
								class="fa fa-download"></span></a>
						</td>
						<td>&nbsp;&nbsp;<a class="btn btn-danger"
							href="javascript:deleteReport('deleteReport.do?count=${report.count}');"><span
								class="fa fa-trash-o"></span></a>
						</td>

					</tr>
				</c:forEach>
			</c:if>
		</table>
		</center>
		<nav>
			<ul class="pager">
				<li class="previous"><a href="admindash.do"><span
						aria-hidden="true">&larr;</span>Previous</a></li>
				<li class="next"><a href="viewAllAdmins.do">Next<span
						aria-hidden="true">&rarr;</span></a></li>
			</ul>
		</nav>
	</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<%@include file="footer.jsp"%>