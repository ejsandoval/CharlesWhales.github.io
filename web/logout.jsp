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
					<a class="navbar-brand" href="<c:url value="/"/>">eObserve</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="<c:url value="/"/>">Home <span class="sr-only">(current)</span></a></li>
	<!-- 					<li><a href="<c:url value="/prices/show?period=1"/>">Daily </a></li>
						<li><a href="<c:url value="/prices/show?period=7"/>">Weekly </a></li>
						<li><a href="<c:url value="/prices/show?period=30"/>">Monthly </a></li> -->
						<li><a href="<c:url value="/contact"/>">About </a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Goodbye <%= session.getAttribute("userName") %> <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<c:url value="/register.jsp"/>">Sign Up</a></li>
								<li><a href="<c:url value="/login.jsp"/>">Sign In</a></li>
							</ul>
						</li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
				
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12"><h2>An Energy Cost Monitoring Tool</h2></div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<br>
					<h3>Thank you for used my application!</h3>
					<h4>To continue please Sign In!</h4>
				</div>
			</div>
<!-- 			<ul class="nav nav-pills">
			  <li role="presentation"><a href="<c:url value="/prices/show?period=1"/>">Daily<span class="sr-only">(current)</span></a></li>
			  <li role="presentation"><a href="<c:url value="/prices/show?period=7"/>">Weekly</a></li>
			  <li role="presentation"><a href="<c:url value="/prices/show?period=30"/>">Monthly</a></li>
			</ul> -->
		
		<script src="<c:url value="/js/vendor/jquery-1.11.2.min.js"/>"></script>
		<script src="<c:url value="/js/vendor/bootstrap.min.js"/>"></script>
		<script src="<c:url value="/js/vendor/Chart.min.js"/>"></script>
		<script src="<c:url value="/js/vendor/moment.js"/>"></script>
		<script src="<c:url value="/js/main.js"/>"></script>

		<footer class="panel-footer">
          <div class="container">
            <p align="right" class="text-muted">Lamprini Vasilaki @ 2015 | University of Thessaly </p>
          </div>
        </footer>
		
	</body>
</html>