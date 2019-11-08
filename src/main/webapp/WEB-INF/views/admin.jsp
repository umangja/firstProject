<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>ADMIN</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
</head>

<style>
/*the container must be positioned relative:*/
.custom-select {
  position: relative;
  font-family: Arial;
}

.custom-select select {
  display: none; /*hide original SELECT element:*/
}

.select-selected {
  background-color: LightBlue;
}

/*style the arrow inside the select element:*/
.select-selected:after {
  position: absolute;
  content: "";
  top: 14px;
  right: 10px;
  width: 0;
  height: 0;
  border: 6px solid transparent;
  border-color: #fff transparent transparent transparent;
}

/*point the arrow upwards when the select box is open (active):*/
.select-selected.select-arrow-active:after {
  border-color: transparent transparent #fff transparent;
  top: 7px;
}

/*style the items (options), including the selected item:*/
.select-items div,.select-selected {
  color: #ffffff;
  padding: 8px 16px;
  border: 1px solid transparent;
  border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
  cursor: pointer;
  user-select: none;
}

/*style items (options):*/
.select-items {
  position: absolute;
  background-color: LightBlue;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 99;
}

/*hide the items when the select box is closed:*/
.select-hide {
  display: none;
}

.select-items div:hover, .same-as-selected {
  background-color: rgba(0, 0, 0, 0.1);
}

body, html {
  height: 100%;
  margin: 0;
}


.bg {
  background-image: url("${pageContext.request.contextPath}/resources/images/background.png");

  height: 100%; 

  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

</style>


<body>
<div>

<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<ul class="nav navbar-nav navbar-right">
			    	<li><a href="admin/addEmp"><span class="glyphicon glyphicon-plus"></span> Employees</a></li>
    				<li><a href="admin/viewAllEmp"><span class="glyphicon glyphicon-user"></span> show Employees</a></li>
   					<li><a href="admin/showManagerForm"><span class="glyphicon glyphicon-plus"></span> Manager</a></li>
					<li><a href="admin/showMostValuablePartners"><span class="glyphicon glyphicon-usd"></span> show valuable Partners</a></li>
					<li><a href="admin/showMostValuablePatients"><span class="glyphicon glyphicon-usd"></span> show valuable Patients</a></li>
					<li><a href="admin/showFeedback"><span class="glyphicon glyphicon-envelope"></span> Feedbacks</a></li>
					<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
					</c:if>
				</ul>	
			</div>
		</div>
</nav>
<div class="container">
	<br><br><br><br><br><br>
	<div class="jumbotron">
    	<c:if test="${pageContext.request.userPrincipal.name != null}">
        	<center><h2 style="font-size: 25px; color:#000000;" >Welcome : Mr. ${firstname} ${lastname}</h2> </center> 
    	</c:if>
    </div> 
   
    <div class="jumbotron">
		<form action="admin/findManagerForEmp">
			<h3>Find Manager For UserName :</h3>
			<select name="emp_id" class="form-control selectpicker">
				   <c:forEach items="${users}" var="user">	
						<option value="${user.username}"> ${user.username} </option>
				   </c:forEach>
			</select>
			<br>
			<button type="submit" class="btn btn-primary">Find</button><br>
			${error1}
			</form>
			<form action="admin/findEmpForManager">
			<h3>Find Employee Managed By     :</h3>
			<select name="manager_id" class="form-control selectpicker">
				   <c:forEach items="${users}" var="user">	
						<option value="${user.username}"> ${user.username} </option>
				   </c:forEach>
			</select>
			<br>
			<button type="submit" class="btn btn-primary">Find</button><br>
			${error2}
		</form>
	</div>

    </div>
</div>
</body>
</html>
