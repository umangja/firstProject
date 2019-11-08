<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Add Supplier</title>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<style>


/* Add padding to containers */
.container {
  padding: 16px;
  background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity: 1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  background-color: #f1f1f1;
  text-align: center;
}
</style>
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
	<form:form method="post" modelAttribute="supplier" action="addSupplier" class="form-inline">
    	<h2><p>Please Enter Supplier Details</p></h2>
    	<hr>
    	
		<div class="form-group">
			<label for="">Name</label><br>
			<form:input path="name" type="text" class="form-control" placeholder="name" value="" required="required"/>
			<form:errors path="name" />
		</div>
		<br>
		<br>
		
		<div class="form-group">
			<label for="">Phone Number</label><br>
			<form:input path="phone_no" type="number" class="form-control" placeholder="phone no" value="" required="required"/>
			<form:errors path="phone_no" />
		</div>
		<br>
		<br>
		
		
		
		
		<div class="form-group">
           <label for="">Shop No</label><br>
           <input type="number" class="form-control" name="shop_no" placeholder="Shop No" value="" required="required" />
      	</div>
		<br><br>
		<div class="form-group">
           <label for="">Colony Name</label><br>
           <input type="text" class="form-control" name="colony_name" placeholder="colony name" value="" required="required" />
		</div>
		<br><br>
		<div class="form-group">
           <label for="">City</label><br>
           <input type="text" class="form-control" name="city" placeholder="city" value=""  required="required" />
		</div>
		<br><br>
		<div class="form-group">
           <label for="">State</label><br>
           <input type="text" class="form-control" name="state" placeholder="state" value="" required="required" />
   		</div>
		<br><br>
		<div class="form-group">
           <label for="">Pincode</label><br>
           <input type="number" class="form-control" name="pincode" placeholder="pincode" value="" required="required" />
   		</div>
		<br><br>
		
		
		
			
		<hr>
		<button type="submit" class="btn btn-primary">Submit</button>		
		<br>
		<td>${error}</td>
	</form:form>
	</div>
	<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>