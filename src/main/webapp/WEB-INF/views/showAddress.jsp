<%@page import="org.springframework.ui.Model"%>
<%@page import="com.umang.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>All Employees</title>
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
				<a href="<c:url value="/j_spring_security_logout" />"><p style="text-align:left"><span class="glyphicon glyphicon-log-out"></span>Logout</p></a>
			</div>
		</div>
</nav>
<br><br><br><br><br><br>



<div class="container">
	<table class="table table-striped">
		<thead>
     		<tr>
		       <c:if test="${who=='emp'}"><th>house No</th></c:if>
		       <c:if test="${who=='sup'}"><th>Shop No</th></c:if>
		       <th>Colony</th>
		       <th>city</th>
		       <th>state</th>
		       <th>Pin code</th>
		     </tr>
   		</thead>
   <tbody>
   <c:forEach items="${adds }" var="add" >
   <tr>
   <c:if test="${who=='emp'}"><td>${add.house_no }</td></c:if>
   <c:if test="${who=='sup'}"><td>${add.shop_no }</td></c:if>
   <td>${add.colony_name }</td>
   <td>${add.city }</td>
   <td>${add.state }</td>
   <td>${add.pincode }</td>
   </tr>
   </c:forEach>
   </tbody>
</table>
</div>
	<script src="/dbms/webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="/dbms/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
</body>
</html>