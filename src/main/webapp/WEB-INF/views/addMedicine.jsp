<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Add Medicine</title>
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
	<form:form method="post" modelAttribute="med" action="addMedicine" class="form-inline">
    	<p>Please Enter Medicine Details</p>
    	<hr>
    	
		<div class="form-group">
			<label for="">Name</label><br>
			<form:input path="name" type="text" class="form-control" placeholder="medicine name" value="" required="required"/>
			<form:errors path="name" />
		</div>
		<br>
		<br>
		
		<div class="form-group">
			<label for="">Company</label><br>
			<form:input path="company" type="text" class="form-control" placeholder="company name" value="" required="required"/>
			<form:errors path="company" />
		</div>
		<br>
		<br>
		
		<div class="form-group">
			<label for="">Price</label><br>
			<form:input path="price" type="number" class="form-control" placeholder="price" value="" required="required"/>
			<form:errors path="price" />
		</div>
		<br>
		<br>
		
		
		<div class="form-group">
			<label for="">In Stock</label><br>
			<form:input path="in_stock" type="number" class="form-control" placeholder="Current Stock" value="" required="required"/>
			<form:errors path="in_stock" />
		</div>
		<br>
		<br>
		
		
		
		<div class="form-group">
			<label for="">Expiration Date</label><br>
			<form:input path="expiration_date" type="date" class="form-control" placeholder="expiration date" value="" required="required"/>
			<form:errors path="expiration_date" />
		</div>
		<br>
		<br>
		
		<h3>Location In Store</h3>
		<select class="form-control selectpicker" name="location_id" path="location_id">
			   <c:forEach items="${locations}" var="location">	
					<option value="${location.id}"> Floor no:${location.floor_no} &nbsp &nbsp  Room no:${location.room_no} &nbsp &nbsp Row no:${location.row_no} &nbsp &nbsp column no:${location.column_no}  </option>
			   </c:forEach>
		</select>
		<br><br>
		<h3>Supplier:</h3>
		<select class="form-control selectpicker" name="supplier_id" path="supplier_id">
			   <c:forEach items="${suppliers}" var="sup">	
					<option value="${sup.id}"> Name:${sup.name} &nbsp &nbsp ID:${sup.id} </option>
			   </c:forEach>
		</select>
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