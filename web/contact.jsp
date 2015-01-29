<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	
	<head>
		<title>eObserve</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,700,300&subset=latin,greek-ext,greek' rel='stylesheet' type='text/css'>
		<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
		<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
	</head>
		
	<body>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<c:url value="/home"/>">eObserve</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="<c:url value="/home"/>">Home <span class="sr-only">(current)</span></a></li>
						<li><a href="<c:url value="/prices/show?period=1"/>">Daily </a></li>
						<li><a href="<c:url value="/prices/show?period=7"/>">Weekly </a></li>
						<li><a href="<c:url value="/prices/show?period=30"/>">Monthly </a></li>
						<li class="active"><a href="<c:url value="/contact"/>">About </a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<% if (session == null || session.getAttribute("userName") == null) { %>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Welcome Guest <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<c:url value="/register.jsp"/>">Register</a></li>
								<li><a href="<c:url value="/login.jsp"/>">Login</a></li>
							</ul>
						</li> 
						<% } else { %>	
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Welcome <%= session.getAttribute("userName") %> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<c:url value="/logout"/>">Logout</a></li>
							</ul>
						</li>							
						<% } %>	
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
				
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12"><h2>An Energy Cost Monitoring Tool</h2></div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<br>
					<p><strong>eObserve</strong> is an application which was developed for the course <strong>"Web Technologies"</strong> during the academic semester <strong>2014-2015.</strong></p>
					<p>It was developed by <strong>Lamprini Vasilaki</strong>, undergraduate student at the Department of <strong>Computer & Communication Engineering</strong>, University of Thessaly.</p>
					<p>eObserve serves the purpose of an energy price simulator. More specifically, eObserve simulates the existance of different electric current providers, uses a pseudo-random number generator to produce fake values for each provider and for each time period which then presents to the end user in the form of line charts.</p>
					<p><strong>Simulation parameters </strong></p>
					<p>We assume that we have 4 electric current providers and each one changes the electric power value once per hour.</p>
					<p>So, the user can be informed at each given moment which provider has the lower value of electric current</p>
					<p><strong>In real conditions</strong>, this application could have a connection with the suppliers of electric current and through the application each user could choose every time who provider wants to supply to him electrical power</p>
				</div>	
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<dl class="dl-horizontal">
						<h3>Used Tecnologies</h3>
					  	<dt>FrontEnd</dt>
					  	<dd>HTML, CSS, Javascript, AJAX, Bootstrap </dd>
					  	<dt>BackEnd</dt>
					  	<dd>Java, Java Servlets, JSP, JSTL, MySQL </dd>
					</dl>
					<dl class="dl-horizontal">
						<h3>Contact Information</h3>
					  	<dt>Developer</dt>
					  	<dd>Lamprini Vasilaki</dd>
					  	<dt>Email</dt>
					  	<dd><a href="mailto:#">lavasila@inf.uth.gr</a></dd>
					  	<dt>Address</dt>
					  	<dd>Volos, Magnesia, Greece, CA 38221</dd>
					  	<dt>Phone</dt>
					  	<dd>(+30) 6977476758</dd>
					</dl>
					Here is my fully <a href="http://lavasila.tk/AM1300cv/">Curriculum Vitae.</a>
				</div>
			</div>
		</div>
		<br>

		<footer class="panel-footer">
	      <div class="container">
	        <p align="right" class="text-muted">Lamprini Vasilaki @ 2015 | University of Thessaly </p>
	      </div>
	    </footer>

		<script src="<c:url value="/js/vendor/jquery-1.11.2.min.js"/>"></script>
		<script src="<c:url value="/js/vendor/bootstrap.min.js"/>"></script>
    </body>
</html>
