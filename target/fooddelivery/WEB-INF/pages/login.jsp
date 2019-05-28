
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
<meta charset="utf-8" />
<%@ include file="header.jsp" %>
</head>
<body onload='document.loginForm.username.focus();'
	style="background-color: powderblue;">
	<div id="login-box" class="login">
		<div class="login-triangle"></div>

		<h2 class="login-header">Log in</h2>



		<form name='loginForm' onsubmit="return validateForm()" class="login-container"
			action="<c:url value='/login' />" method='POST'
			accept-charset="utf-8">
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>


			<input class="form-control" type='email' name='username'
				placeholder="Email"><br><input type='password'
				class="form-control" name='password' placeholder="Password" /><br> <input
				class="btn btn-primary btn-sm" name="submit" type="submit" value="LogIn" />
			<%-- <input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> --%>

		</form>
	</div>
    <script type="text/javascript">
    function validateForm() {
    	  var username = document.forms["loginForm"]["username"].value;
    	  var password = document.forms["loginForm"]["password"].value;
    	  if (username == ""&&password=='') {
    		Ext.Msg.alert("empty value!");
    	    return false;
    	  }
    	} 
    
    </script>
    
    <%@ include file="footer.jsp" %>
</body>
</html>