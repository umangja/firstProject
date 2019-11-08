<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Update Employee</title>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body background="#EE350E">

<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<a href="<c:url value="/j_spring_security_logout" />"><p style="text-align:left"><span class="glyphicon glyphicon-log-out"></span>Logout</p></a>
			</div>
		</div>
</nav>

<div class="container">
	<br><br><br><br><br><br>
	<form:form method="post" modelAttribute="user" action="" class="form-inline">
    	<h2><p>Please fill Details for ${username}</p></h2>
    	<hr>
		<div class="form-group">
			<label for="">First Name</label><br>
			<form:input path="firstname" type="text" class="form-control" placeholder="firstname" value="" required="required" />
			<form:errors path="firstname" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Last Name</label><br>
			<form:input path="lastname" type="text" class="form-control" placeholder="lastname" value="" required="required" />
			<form:errors path="lastname" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Date Of Birth</label><br>
			<form:input path="DOB" type="date" class="form-control" placeholder="date of birth" value="" required="required" />
			<form:errors path="DOB" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Joining Date</label><br>
			<form:input path="joining_date" type="date" class="form-control" placeholder="joining_date" value="" required="required" />
			<form:errors path="joining_date" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Post</label><br>
			<form:input path="post" type="text" class="form-control" placeholder="post" value="" required="required"  />
			<form:errors path="post" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Income</label><br>
			<form:input path="Income" type="number" class="form-control" placeholder="income" value="" required="required"   />
			<form:errors path="Income" />
		</div>
		<br>
		<br>		
		<hr>
		<button type="submit" class="btn btn-primary">Submit</button>		
		<td>${error}</td>
	</form:form>
	</div>
	<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>