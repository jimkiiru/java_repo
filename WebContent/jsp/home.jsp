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
	content="dkutclubs,dekutclubsmanager,clubs,dkut,kuct,kit,dedan kimathi university,dedan kimathi university of technology,kimathi university college,kimathi university of technology" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="Dedan Kimathi University of Technology" />
<meta name="generator"
	content="Joomla! - Open Source Content Management" />
<title>Home | DeKUT Clubs Manager</title>
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
<link rel="stylesheet" href="css/styles.css" type="text/css" media="screen, print"/>
<!--End stylemap-->
<SCRIPT type="text/javascript">
	function resetForm() {
		document.form.reset();
	}
</script>
</head>
<body>
	<!-- Preloader -->
	<div id="preloader">
		<div id="status">&nbsp;</div>
	</div>
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
				<ul class="nav navbar-inverse navbar-nav">
					<li><a href="index.jsp"><i class="fa fa-home"
							aria-hidden="true"></i>&nbsp;HOME</a></li>
				</ul>
				<ul class="nav navbar-inverse navbar-nav navbar-right">
					<li><a href="enterReg.do"><span
							class="glyphicon glyphicon-user"></span> SIGN UP</a>&nbsp;&nbsp;</li>
					<li><a href="loginform.do"><span
							class="glyphicon glyphicon-log-in"></span> LOGIN</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="fullwidthbanner-container">
		<span class="Apple-tab-span"> </span>
		<div class="fullwidthbanner">
			<ul>
				<!-- SLIDE 1 -->
				<li data-transition="random" data-slotamount="7"
					data-masterspeed="1500">
					<!-- MAIN IMAGE --> <img src="images/slider/s1.jpg" alt="desk"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat"> <!-- LAYERS --> <!-- LAYER NR. 1 -->
					<div class="tp-caption slider-title" data-x="center"
						data-y="center" data-voffset="-30" data-speed="500"
						data-start="1200" data-easing="Power4.easeInOut">
						<h1 class="text-center">
							<font style="color: gold;">Welcome to <span>DeKUT
									Clubs</span></font>
						</h1>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 2 -->
					<div class="tp-caption" data-x="center" data-y="center"
						data-voffset="40" data-speed="500" data-start="1800"
						data-easing="Power4.easeInOut" style="color: #fff;"
						data-captionhidden="on">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
							aute irure</p>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 3 -->
					<div class="tp-caption scroll-to" data-x="center" data-y="center"
						data-voffset="120" data-speed="500" data-start="2400"
						data-easing="Power4.easeInOut" data-captionhidden="on"></div> <!-- /tp-caption -->
				</li>
				<li data-transition="random" data-slotamount="7"
					data-masterspeed="1500">
					<!-- MAIN IMAGE --> <img src="images/slider/s2.jpg" alt="desk"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat"> <!-- LAYERS --> <!-- LAYER NR. 1 -->
					<div class="tp-caption slider-title" data-x="center"
						data-y="center" data-voffset="-30" data-speed="500"
						data-start="1200" data-easing="Power4.easeInOut">
						<h1>
							<font style="color: gold;">Welcome to <span>DeKUT
									Clubs</span></font>
						</h1>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 2 -->
					<div class="tp-caption" data-x="center" data-y="center"
						data-voffset="40" data-speed="500" data-start="1800"
						data-easing="Power4.easeInOut" style="color: #fff;"
						data-captionhidden="on">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
							aute irure</p>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 3 -->
					<div class="tp-caption scroll-to" data-x="center" data-y="center"
						data-voffset="120" data-speed="500" data-start="2400"
						data-easing="Power4.easeInOut" data-captionhidden="on"></div> <!-- /tp-caption -->
				</li>
				<li data-transition="random" data-slotamount="7"
					data-masterspeed="1500">
					<!-- MAIN IMAGE --> <img src="images/slider/s3.jpg" alt="desk"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat"> <!-- LAYERS --> <!-- LAYER NR. 1 -->
					<div class="tp-caption slider-title" data-x="center"
						data-y="center" data-voffset="-30" data-speed="500"
						data-start="1200" data-easing="Power4.easeInOut">
						<h1>
							<font style="color: gold;">Welcome to <span>DeKUT
									Clubs</span></font>
						</h1>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 2 -->
					<div class="tp-caption" data-x="center" data-y="center"
						data-voffset="40" data-speed="500" data-start="1800"
						data-easing="Power4.easeInOut" style="color: #fff;"
						data-captionhidden="on">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
							aute irure</p>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 3 -->
					<div class="tp-caption scroll-to" data-x="center" data-y="center"
						data-voffset="120" data-speed="500" data-start="2400"
						data-easing="Power4.easeInOut" data-captionhidden="on"></div> <!-- /tp-caption -->
				</li>
				<!-- SLIDE 2 -->
				<li data-transition="random" data-slotamount="7"
					data-masterspeed="1500">
					<!-- MAIN IMAGE --> <img src="images/slider/s4.jpg" alt="desk"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat"> <!-- LAYERS --> <!-- LAYER NR. 1 -->
					<div class="tp-caption slider-title" data-x="center"
						data-y="center" data-voffset="-30" data-speed="500"
						data-start="1200" data-easing="Power4.easeInOut">
						<h1>
							<font style="color: gold;">Welcome to <span>DeKUT
									Clubs</span></font>
						</h1>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 2 -->
					<div class="tp-caption" data-x="center" data-y="center"
						data-voffset="40" data-speed="500" data-start="1800"
						data-easing="Power4.easeInOut" style="color: #fff;"
						data-captionhidden="on">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
							aute irure</p>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 3 -->
					<div class="tp-caption scroll-to" data-x="center" data-y="center"
						data-voffset="120" data-speed="500" data-start="2400"
						data-easing="Power4.easeInOut" data-captionhidden="on"></div> <!-- /tp-caption -->
				</li>
				<li data-transition="random" data-slotamount="7"
					data-masterspeed="1500">
					<!-- MAIN IMAGE --> <img src="images/slider/s5.jpg" alt="desk"
					data-bgfit="cover" data-bgposition="left top"
					data-bgrepeat="no-repeat"> <!-- LAYERS --> <!-- LAYER NR. 1 -->
					<div class="tp-caption" data-x="center" data-y="center"
						data-voffset="-30" data-speed="500" data-start="1200"
						data-easing="Power4.easeInOut">
						<h1>
							<font style="color: gold;">Welcome to <span>DeKUT
									Clubs</span></font>
						</h1>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 2 -->
					<div class="tp-caption" data-x="center" data-y="center"
						data-voffset="40" data-speed="500" data-start="1800"
						data-easing="Power4.easeInOut" style="color: #fff;"
						data-captionhidden="on">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit
							aute irure</p>
					</div> <!-- /tp-caption --> <!-- LAYER NR. 3 -->
					<div class="tp-caption scroll-to" data-x="center" data-y="center"
						data-voffset="120" data-speed="500" data-start="2400"
						data-easing="Power4.easeInOut" data-captionhidden="on"></div> <!-- /tp-caption -->
				</li>
			</ul>
		</div>
	</div>
	<hr>
	<br>
	<br>
	<%--End container--%>
	<%@include file="footer.jsp"%>