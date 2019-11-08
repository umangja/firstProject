<%@page import="org.springframework.ui.Model"%>
<%@page import="com.umang.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>All Medicines</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<%@ page isELIgnored="false"%>
</head>



<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
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
		<table class="table table-striped">
			<thead>
	     		<tr>
			       <th>Medicine Name</th>
			       <th>Company Name</th>
			       <th>Price</th>
			       <th>Stock Available</th>
			       <th>Expiration date</th>
			       <th>Location</th>
			       <th>Supplier</th>
			       <th>Can Cure</th>
			       <th>Contains</th>
			     </tr>
	   		</thead>
	   <tbody>
	   
	   <c:forEach items="${medicines }" var="med">
	   <tr>
	   <td>${med.name }</td>
	   <td>${med.company }</td>
	   <td>${med.price }</td>
	   <td>${med.in_stock }</td>
	   <td>${med.expiration_date }</td>
	   <td><a href="showLocationOfMedicine/${med.id}">Location</a></td>
	   <td><a href="showSupplierforMedicine/${med.id}">Supplier</a></td>
	   <td><a href="showDiseaseforMedicine/${med.id}/${med.name}">Cures</a></td>
	   <td><a href="showDrugforMedicine/${med.id}/${med.name}">Contain</a></td>
	   </tr>
	   </c:forEach>
	   </tbody>
	</table>
	<input type="button" onclick="history.go(-1);" value="Go Back"/>
	</div>
	<script src="/dbms/webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="/dbms/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
	
</body>
</html>