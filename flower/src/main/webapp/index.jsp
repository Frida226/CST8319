<%
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Flower Online Order</title>
</head>
<body>
	<h2 class="form-title">Flower Online Order</h2>
</body>
</html>