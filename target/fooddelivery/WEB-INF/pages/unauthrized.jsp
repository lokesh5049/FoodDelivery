<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Ristriction</title>
<head><meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<%@ include file="header.jsp" %>
</head>
<body>
<h1>You are not allowed for this page!</h1>
   <header>
   <div style="float:right">
   
	
	</div>
	</header>
	<c:choose>
		<c:when test="${empty username}">
		  <h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
		  <h2>Username : ${username} <br/>
                     permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
	
	<%@ include file="footer.jsp" %>
	</body>
	</html>
	