<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ page language="java"%><!DOCTYPE html>
<html>
<title>Home</title>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta charset="utf-8" />


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
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/packages/charts/classic/charts.js"></script>

<style>
.x-grid-item {
	text-transform: uppercase;
}

.x-boundlist-item {
	text-transform: uppercase;
}

.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

.login {
	width: 300px;
	margin: 30px auto;
	font-size: 16px;
}

body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.fixed-header, .fixed-footer {
	width: 100%;
	position: fixed;
	background: #004475;
	padding: 10px 0;
	color: #fff;
}

.fixed-header {
	top: 0;
}

.fixed-footer {
	bottom: 0;
}

.container {
	width: 80%;
	margin: 0 auto; /* Center the DIV horizontally */
}

nav a {
	color: #fff;
	text-decoration: none;
	padding: 7px 25px;
	display: inline-block;
}

marquee {
	-webkit-animation: caption 50s linear 0s infinite;
	font-family: 'Segoe ui';
	padding: 20px 0;
	border: 1px solid #000;
	background-color: #;
	-webkit-box-shadow: inset 0px 2px 2px rgba(0, 0, 0, .5), 0px 1px 0px
		rgba(250, 250, 250, .2);
	box-shadow: inset 0px 2px 2px rgba(0, 0, 0, .5), 0px 1px 0px
		rgba(250, 250, 250, .2);
	-webkit-transition: background-color 350ms;
	-moz-transition: background-color 350ms;
	transition: background-color 350ms;
	overflow: hidden;
	overflow-x: -webkit-marquee;
	-webkit-marquee-direction: right;
	-webkit-marquee-style: scroll;
	-webkit-marquee-speed: normal;
	-webkit-marquee-increment: small;
	-webkit-marquee-repetition: 5;
	overflow-x: marquee-line;
	marquee-direction: forward;
	marquee-style: loop;
	marquee-speed: slow;
	marquee-play-count: 5;
	color: #28282a;
	background: #ffeb95;
}

.login input[type="email"], .login input[type="password"] {
	background: #fff;
	border-color: #bbb;
	color: #555;
}

/* Text fields' focus effect */
.login input[type="email"]:focus, .login input[type="password"]:focus {
	border-color: #888;
}

.login input {
	box-sizing: border-box;
	display: block;
	width: 100%;
	border-width: 1px;
	border-style: solid;
	padding: 16px;
	outline: 0;
	font-family: inherit;
	font-size: 0.95em;
}

.login-triangle {
	width: 0;
	margin-right: auto;
	margin-left: auto;
	border: 12px solid transparent;
	border-bottom-color: #28d;
}

.login-header {
	background: #28d;
	padding: 20px;
	font-size: 1.4em;
	font-weight: normal;
	text-align: center;
	text-transform: uppercase;
	color: #fff;
}

.login-container {
	background: #ebebeb;
	padding: 20px;
}

.login-header, .login p {
	margin-top: 0;
	margin-bottom: 0;
}
</style>
<script type="text/javascript">
function startTime() {
	  var today = new Date();
	  var h = today.getHours();
	  var m = today.getMinutes();
	  var s = today.getSeconds();
	  var date=new Date();
	  m = checkTime(m);
	  s = checkTime(s);
	  document.getElementById('ClockRun').innerHTML ="[ "+date+" ]"+" "+
	  h + ":" + m + ":" + s;
	  var t = setTimeout(startTime, 500);
	}
	function checkTime(i) {
	  if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
	  return i;
	}
</script>
</head>
<body onload="startTime()">
<div class="fixed-header">

	<div class="container">

		<nav>
			<span style="font: italic bold 12px/30px Georgia, serif;">Food
				Delivery System </span> <a href="/fooddelivery/home">Home</a> <a
				href="/fooddelivery/food">Foods</a>
			


			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name == null}">
					<style>
#user {
	margin-top: -34px;
}
</style>
					<a href="/fooddelivery/login">LogIn</a>
					
					<div class="dropdown">
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">
							Register <span class="caret"></span>
						</button>
						<span>Today's Date :<span id="ClockRun" ></span></span>
						<ul class="dropdown-menu">
							<li><a href="/fooddelivery/register/user/">User</a></li>
							<li><a href="/fooddelivery/register/provider_r/">Provider</a></li>
							<li><a href="/fooddelivery/register/admin_r/">Admin</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<a href="/fooddelivery/au2">Service</a>
				</c:otherwise>
			</c:choose>
			
			<div class="dropdown" id='user' style="float: right;">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					<span class="glyphicon glyphicon-user">:User<span
						class="caret"></span></span>
				</button>

				<ul class="dropdown-menu">
					<li><sec:authorize access="hasRole('customer')">
							<!-- For login user -->
							<c:url value="/logout" var="logoutUrl" />
							<form action="${logoutUrl}" method="post" id="logoutForm">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>
							<script>
								function formSubmit() {
									document.getElementById("logoutForm")
											.submit();
								}
							</script>
							<c:choose>
								<c:when test="${pageContext.request.userPrincipal.name != null}">

									<span style="color: black">
										${pageContext.request.userPrincipal.name}</span>


									<a href="javascript:formSubmit()" style="color: black">
										Logout</a>

									<li><a>Edit Profile</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/fooddelivery/login">login</a></li>
								</c:otherwise>
							</c:choose>
						</sec:authorize></li>
				</ul>
			</div>



		</nav>

	</div>

</div>

</body>
</html>