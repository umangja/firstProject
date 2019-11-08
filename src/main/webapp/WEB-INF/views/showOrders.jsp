<%@page import="org.springframework.ui.Model"%>
<%@page import="com.umang.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>All Orders</title>
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
	<br><br><br><br><br><br>
	
	
	
	<div class="container">
		<table class="table table-striped">
			<thead>
	     		<tr>
			       <th>Order id</th>
   			       <th>Date And Time</th>
			       <th>Ordered_by</th>
			       <th>Medicines Ordered</th>
			       <th>Action</th>
			     </tr>
	   		</thead>
	   <tbody>
	  
	   <c:forEach items="${orders}" var="order">
	   <tr>
	   <td>${order.id }</td>
	   <td>${order.dateAndTime }</td>   
  	   <td><a href="showPartners/${order.ordered_by}">Show Partner</a></td>
	   <td><a href="showMedicinesForOrder/${order.id}">Show Medicines</a></td>
	   <td><a href="completeOrder/${order.id}">Complete</a></td>
	  
	   </tr>
	   </c:forEach>
	   </tbody>
	</table>
	<input action="action" type="button" onclick="history.go(-1);" value="Go Back"/>
	</div>
	<script src="/dbms/webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="/dbms/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
	
</body>
</html>