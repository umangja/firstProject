<%@page import="org.springframework.ui.Model"%>
<%@page import="com.umang.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Partner</title>
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
			       <th>Name</th>
			       <th>Phone No.</th>
			       <th>Address</th>
			       <c:if test="${showTotal == 'yes'}">
			       <th>Total Bill</th>
			       </c:if>
			     </tr>
	   		</thead>
	   <tbody>
	   
	   <c:forEach items="${partners }" var="sup">
	   <tr>
	   <td>${sup.name }</td>
	   <td>${sup.phone_no }</td>
   	   <c:choose>
	       <c:when test="${sup.address_id == 0}"><td>Address Not Set</td></c:when>
	       <c:when test="${sup.address_id == null}"><td>Address Not Set</td></c:when>
	       <c:when test="${showTotal == 'yes'}"><td><a href="findAddress/${sup.address_id}">Address</a></td></c:when>
	       <c:otherwise><td><a href="../findAddress/${sup.address_id}">Address</a></td></c:otherwise>
	   </c:choose>
       <c:if test="${showTotal == 'yes'}">
       <td>${sup.total}</td>
       </c:if>
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