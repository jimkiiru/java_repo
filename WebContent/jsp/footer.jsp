<%--Footer--%>
<footer class="footer_style">
	<div class="col-lg-12 text-center footer">
		<div class="container text-center container-hr">
			<div class="col-lg-6">
				<ul class="list-block text-left" style="color: #FFF">
					<font style="color: #DBB900;"><h5>
							<b>CONTACTS</b>
						</h5></font>
					<li>P.O BOX 657-10100,</li>
					<li>Nyeri, Kenya.</li>
					<li>Tel: +254 061 2050000</li>
					<li>Mobile: 0713 835 965</li>
					<li>Email: info@dkut.ac.ke</li>
				</ul>
			</div>
			<div class="col-lg-6">
				<ul class="list-block text-left"">
					<font style="color: #DBB900">
						<h5 class="text-left">
							<b>RESOURCES</b>
						</h5>
					</font>
					<li><a href="http://portal.dkut.ac.ke:81/Default.aspx">Student
							Portal</a><span style="color: #ff0000;"></span></li>
					<li><a href="http://portal.dkut.ac.ke:81/staff/Default.aspx">Staff
							Portal<sup><span style="color: #ff0000;"></span></sup>
					</a><sup><span style="color: #ff0000;"></span></sup></li>
					<li><a href="http://helpdesk.dkut.ac.ke/user-support/">ICT
							Helpdesk</a></li>
					<li><a
						href="http://172.16.32.4/oasis/catalog/(S(jfy44wfbztngar55io0z3i55))/default.aspx?session=new&amp;installation=kimathi">Library
							Catalogue</a></li>
					<li><a href="http://repository.dkut.ac.ke:8080/xmlui/"
						target="_blank">Institutional Repository</a></li>
					<li><a href="/index.php/newsletters">Newsletters</a></li>
				</ul>
			</div>
		</div>
		<%--End container--%>
		<div id="hr-footer">
			<p class="text-center">
				<font style="color: #fff">&COPY; 2016 Dedan Kimathi
					University of Technology.</font>
			</p>
		</div>
	</div>
	</div>
</footer>

<script>
<!--End stylemap-->
	<SCRIPT type="text/javascript">
	function resetForm() {
		document.form.reset();
	}
</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.2/js/bootstrap.min.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="http://http://code.jquery.com/jquery-2.2.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous">
</script>
<%--Rev Slider --%>
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.tools.min.js"></script>
<script type="text/javascript"
	src="rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
<script type="text/javascript">
	(function() {
		"use strict";
		// Slider Revolution
		jQuery('.fullwidthbanner').revolution({
			delay : 10000,
			startwidth : 1170,
			startheight : 540,
			fullWidth : "on",
			fullScreen : "off",
			hideTimerBar : "on",
			spinner : "spinner4",
			navigationStyle : "preview4",
			soloArrowLeftHOffset : 20,
			soloArrowRightHOffset : 20
		});
	}());
</script>
<!-- jQuery Plugin -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.3.min.js"></script>

<!-- Preloader -->
<script type="text/javascript">
	//<![CDATA[
	$(window).load(function() { // makes sure the whole site is loaded
		$('#status').fadeOut(); // will first fade out the loading animation
		$('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
		$('body').delay(350).css({
			'overflow' : 'visible'
		});
	})
	//]]>
</script>
</body>
</html>
