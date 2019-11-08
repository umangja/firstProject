<%@page import="org.springframework.ui.Model"%>
<%@page import="com.umang.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>All Employees</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
</head>
<style>

table.sortable thead {
    background-color:#eee;
    color:#666666;
    font-weight: bold;
    cursor: default;
}

</style>



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
			       <th>UserName</th>
			       <th>First Name</th>
			       <th>Last Name</th>
			       <th>Gender</th>
			       <th>Date Of Birth</th>
			       <th>Post</th>
			       <th>Income</th>
			       <th>Joining Date</th>
			       <th>Address</th>
			       <c:if test="${role=='admin'}">
			       		<th>Action</th>
			       		<th>Action</th>		
			       </c:if>
			     </tr>
	   		</thead>
	   <tbody>
	   
	   <c:forEach items="${users }" var="user">
	   <c:if test="${user != null }">
	   <tr>
		   <td>${user.username }</td>
		   <td>${user.firstname }</td>
		   <td>${user.lastname }</td>
		   <td>${user.gender }</td>
		   <td>${user.DOB }</td>
		   <td>${user.post }</td>
		   <td>${user.income }</td>
		   <td>${user.joining_date }</td>
		   <c:choose>
		       <c:when test="${user.lives_at == 0}"><td>Address Not Set</td></c:when>
		       <c:when test="${user.lives_at == null}"><td>Address Not Set</td></c:when>
		       <c:otherwise><td><a href="findAddress/${user.lives_at}">Address</a></td></c:otherwise>
		   </c:choose>
	       <c:if test="${role=='admin'}">
	       		<td><a href="deleteEmployee/${user.username}">delete</a></td>
	       		<td><a href="updateEmployee/${user.username}">update</a></td>	
	       </c:if>
	   </tr>
	   
	   </c:if>

	   </c:forEach>
	   </tbody>
	</table>
	</div>
	<script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
	<c:if test="${role=='admin'}">
	<div class="container">
		<form:form action="viewAllEmpSorted" class="form-inline" method="get">
		  <div class="form-group"><input type="checkbox" name="income" value="true"> Sort By Income</div><br><br>
		  <div class="form-group"><input type="checkbox" name="joiningdate" value="true"> Sort By Joining Date</div><br><br>
		  <input type="submit" value="Submit" class="btn btn-primary">
		</form:form>
	</div>
	</c:if>
	
	
	
	
	
</body>
</html>