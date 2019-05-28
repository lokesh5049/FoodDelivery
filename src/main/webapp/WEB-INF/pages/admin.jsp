<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN</title>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<%@ include file="header.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-neptune/resources/theme-neptune-all.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/charts.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/classic/resources/charts-all.css"
	rel="stylesheet" />
	
</head>
<body>
<ul class="nav nav-pills">
  <li class="active"><a data-toggle="pill" href="#home">Dashboard</a></li>
  <li><a data-toggle="pill" href="#menu1">Orders</a></li>
  <li><a data-toggle="pill" href="#menu2">Transaction</a></li>
</ul>

<div class="tab-content">
  <div id="home" class="tab-pane fade in active">
    <%@ include file="dashboard_admin.jsp" %>
  </div>
  <div id="menu1" class="tab-pane fade">
   
  </div>
  <div id="menu2" class="tab-pane fade">
   
  </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>