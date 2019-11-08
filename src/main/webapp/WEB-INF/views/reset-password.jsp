<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Reset Password</title>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>

<script type="text/javascript">

function validateEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    if (reg.test(emailField.value) == false) 
    {
        alert('Invalid Email Address');
        return false;
    }

    return true;

}

</script>


<body background="#EE350E">

<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>Logout</a>
			</div>
		</div>
</nav>

<div class="container">
	<br><br><br><br><br><br>
	<form:form method="post" modelAttribute="user" action="reset-password" class="form-inline">
    	<h2>Reset Password</h2>
    	<hr>
		<div class="form-group">
            <label for="">USERNAME</label><br>
            <form:input type="text" class="form-control" path="username"  value="${username}"  readonly="true" />
            <form:errors path="username" />
        </div>
		<br>
		<br>
		<div class="form-group">
			<label for="">PASSWORD</label><br>
			<form:input path="password" type="password" class="form-control" placeholder="password" value="" required="required" />
			<form:errors path="password" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Confirm password</label><br>
			<form:input path="mpassword" type="password" class="form-control" value="" placeholder="confirm password" required="required" />
			<form:errors path="mpassword" />
		</div>
		<br>
		<br>
		<hr>
		<button type="submit" class="btn btn-primary">Submit</button>		
		<td>${message}</td>
	</form:form>
	</div>
	<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>