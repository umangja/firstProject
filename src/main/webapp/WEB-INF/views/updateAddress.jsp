<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Update Address</title>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>


<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<a href="<c:url value="/j_spring_security_logout" />"><p style="text-align:left"><span class="glyphicon glyphicon-log-out"></span>Logout</p></a>
			</div>
		</div>
</nav>


<div class="container">
<br><br><br><br><br>
<c:if test="${is_supplier==false}">
	<form:form method="post" modelAttribute="add" action="updateAddress" class="form-inline">
	<h2><p>Please Enter Address</p></h2>	
    	<hr>
		<div class="form-group">
			<label for="">House No</label><br>
			<form:input path="house_no" type="number" class="form-control" placeholder="house no" value="" required="required"/>
			<form:errors path="house_no" />
		</div>
		<br>
		<br>
		
		<div class="form-group">
			<label for="">Colony Name</label><br>
			<form:input path="colony_name" type="text" class="form-control" placeholder="colony name" value="" required="required"/>
			<form:errors path="colony_name" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">City</label><br>
			<form:input path="city" type="text" class="form-control" placeholder="city" value="" required="required"/>
			<form:errors path="city" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">State</label><br>
			<form:input path="state" type="text" class="form-control" placeholder="state" value="" required="required"/>
			<form:errors path="state" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Pincode</label><br>
			<form:input path="pincode" type="number" class="form-control" placeholder="pincode" value="" required="required"/>
			<form:errors path="pincode" />
		</div>
		<br>
		<br>		
		<hr>
		<button type="submit" class="btn btn-primary">Submit</button>		
		<br>
		<td>${error}</td>
	</form:form>
</c:if>

<c:if test="${is_supplier==true}">
<form:form method="post" modelAttribute="add" action="addSupplierAddress" class="form-inline">
	<h2><p>Please Enter Address</p></h2>
    	<hr>
		
    	<c:if test="${is_supplier==true}">
			<div class="form-group">
				<label for="">Shop No</label><br>
				<form:input path="shop_no" type="number" class="form-control" placeholder="Shop no" value="" required="required"/>
				<form:errors path="shop_no" />
			</div>
		</c:if>
		<br>
		<br>
		
    	<c:if test="${is_supplier==true}">
			<div class="form-group">
				<h3>Supplier:</h3>
				<select name="supplier_id">
					   <c:forEach items="${suppliers}" var="sup">	
							<option value="${sup.id}"> ${sup.name} </option>
					   </c:forEach>
				</select>
			</div>
		</c:if>
		<br>
		<br>
		
		<div class="form-group">
			<label for="">Colony Name</label><br>
			<form:input path="colony_name" type="text" class="form-control" placeholder="colony name" value="" required="required"/>
			<form:errors path="colony_name" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">City</label><br>
			<form:input path="city" type="text" class="form-control" placeholder="city" value="" required="required"/>
			<form:errors path="city" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">State</label><br>
			<form:input path="state" type="text" class="form-control" placeholder="state" value="" required="required"/>
			<form:errors path="state" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Pincode</label><br>
			<form:input path="pincode" type="number" class="form-control" placeholder="pincode" value="" required="required"/>
			<form:errors path="pincode" />
		</div>
		<br>
		<br>		
		<hr>
		<button type="submit" class="btn btn-primary">Submit</button>		
		<br>
		<td>${error}</td>
	</form:form>
</c:if>
</div>
<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>