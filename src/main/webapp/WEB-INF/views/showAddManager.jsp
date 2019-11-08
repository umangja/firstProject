<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Add Manager</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
</head>


<body>
<nav class="navbar navbar-inverse navbar-fixed-top" style="background:#000000">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
					</c:if>
				</ul>	
			</div>
		</div>
</nav>
<div class="container">

<br><br><br><br><br><br>

<div class="jumbotron">
	<center><h2 style="font-size: 50px;font-family: Western">ADD MANAGER</h2></center>
</div>

<div class="jumbotron">
<form action="addManager">
<h3>Manager UserName:</h3>
<select name="manager_id" class="form-control selectpicker">
	   <c:forEach items="${users}" var="user">	
			<option value="${user.username}"> ${user.username} </option>
	   </c:forEach>
</select>
<br><br>
<h3>Manages Employee:</h3>
<select name="emp_id" class="form-control selectpicker">
	   <c:forEach items="${users}" var="user">	
			<option value="${user.username}"> ${user.username} </option>
	   </c:forEach>
</select>
<br><br>
<button type="submit" class="btn btn-primary">Add</button><br>
<td>${error}</td>
</form>
 </div>
  </div>
  <script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>

</body>
</html>
