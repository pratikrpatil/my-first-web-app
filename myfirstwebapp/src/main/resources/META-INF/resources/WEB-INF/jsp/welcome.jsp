<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >
		<title>Welcome Page</title>
	</head>
	<body>
		<%@ include file="common/navigation.jspf" %>
		<div class="container">
		<h1>Welcome - ${name}</h1>
		<hr>
		<div><a href="list-todos">Click here</a> to manage your todos.</div>
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	</body>
</html>