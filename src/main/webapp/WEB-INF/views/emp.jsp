<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@page import="com.umang.*" %>
<%@page import="com.umang.dao.*" %>
<%@page import="com.umang.model.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page  import ="javax.sql.DataSource" %>

<%@page  import ="org.springframework.beans.factory.annotation.Autowired" %>
<%@page  import ="org.springframework.dao.DataAccessException" %>
<%@page  import ="org.springframework.jdbc.core.JdbcTemplate" %>
<%@page  import ="org.springframework.jdbc.core.ResultSetExtractor" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Employee </title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" />
<link href="https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css" rel="stylesheet">
</head>

<style>
a:link {
  color: green;
  background-color: transparent;
  text-decoration: none;
}

a:visited {
  color: green;
  background-color: transparent;
  text-decoration: none;
}

a:hover {
  color: red;
  background-color: transparent;
  text-decoration: underline;
}

a:active {
  color: yellow;
  background-color: transparent;
  text-decoration: underline;
}
</style>

<script type="text/javascript">
 	var medicine_names = [];
	<c:forEach var="med" items="${expiredMedicines}">
		medicine_names.push("${med}");
	</c:forEach>
	var mess = "Medicines "
	for(var i=0;i<medicine_names.length;i++)
	{
		mess=mess+medicine_names[i];
		mess=mess+",";
	}
	mess=mess+" are expired today";
	if(medicine_names.length != 0)
	{
		alert(mess);
	}
</script>


<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="emp/updateProfile"><span class="glyphicon glyphicon-user"></span> Update Profile</a></li>	
					<li><a href="emp/updateAddress"><span class="glyphicon glyphicon-home"></span> Update Address</a></li>
   					<li><a href="emp/viewProfile"><span class="glyphicon glyphicon-user"></span>  My Profile</a></li>
   					<li><a href="emp/addMedicine"><span class="glyphicon glyphicon-plus	"></span> Medicine</a></li>
   					<li><a href="emp/showAllMedicine"><span class="glyphicon glyphicon-apple"></span>  All Medicine</a></li>
					<li><a href="emp/addBill"><span class="glyphicon glyphicon-file"></span>  Make Bill</a></li>
					<li><a href="emp/showBills"><span class="glyphicon glyphicon-file"></span>  All Bills</a></li>
					<li><a href="emp/addContain"><span class="glyphicon glyphicon-plus	"></span> Drug</a><li>
					<li><a href="emp/canCure"><span class="glyphicon glyphicon-plus"></span> Diseases</a></li>
					<li><a href="emp/showOrders"><span class="glyphicon glyphicon-shopping-cart	"></span> Orders</a><li>
					<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
</nav>
<div class="container">
	<br><br><br><br><br><br>
	<div class="jumbotron">
    	<c:if test="${pageContext.request.userPrincipal.name != null}">
        	<center><h2 style="font-size: 25px; color:#000000; ">Welcome : Mr/Mrs ${firstname} ${lastname}</h2> </center>
    	</c:if>
    </div>
    
   	<h2>Find Medicine</h2>
	<div class="jumbotron">
		<form action="emp/FindMedicine" class="form-inline">
			<div class="form-group">
	           <label for="">Medicine</label><br>
	           <input type="text" class="form-control" name="medicine" placeholder="medicine name" value="" />
       		</div>
			<br><br>
			<div class="form-group">
				<input type="submit" value = "Find Medicine" class="btn btn-primary">
			</div>
		</form>
		${error3}
	</div>
    
    
    
    <h2>Add Suppliers</h2>
   	<div class="jumbotron">
	     <h2> <a href="emp/addSupplier"><span class="glyphicon glyphicon-plus	"></span> Supplier</a></h2>
    </div>
    
    <h2>Add Inventory</h2>
   	<div class="jumbotron">
   	     <h2> <a href="emp/addLocation"><span class="glyphicon glyphicon-plus	"></span> Location</a></h2>
	     <h2> <a href="emp/addDisease"><span class="glyphicon glyphicon-plus	">    </span> Diseases</a></h2>
	  	 <h2> <a href="emp/addPartner"><span class="glyphicon glyphicon-plus	"></span> Partner</a></h2>
		 <h2> <a href="emp/addPatient"><span class="glyphicon glyphicon-plus	"></span> Patient</a></h2>
    </div> 
    
    <h2>Filter Medicines By Diseases</h2>
    <div class="jumbotron">
		<form action="emp/MedicineThatCanCure">
			<h3>Medicine That Cures Disease :</h3>
			
			<select name="disease_id" class="form-control selectpicker">
				   <c:forEach items="${diseases}" var="disease">	
						<option value="${disease.id}"> ${disease.name} </option>
		 			</c:forEach>
			</select>
			<br>
			<button type="submit" class="btn btn-primary">Find</button><br>
			${error1}
		</form>
		<form action="emp/diseaseForMedicine">
			<h3>Disease Cured By Medicine :</h3>
			<select name="medicine" class="form-control selectpicker">
				   <c:forEach items="${medicines}" var="medicine">	
						<option value="${medicine.id} ${medicine.name}"> ${medicine.name} </option>
		 			</c:forEach>
			</select>
			<br>
			<button type="submit" class="btn btn-primary">Find</button><br>
			${error2}
		</form>
	</div>
	
	<h2>Filter Medicines</h2>
	<div class="jumbotron">
		<form method="POST" action="emp/showFilteredMedicine" class="form-inline">
			<div class="form-group">
	           <label for="">company</label><br>
	           <input type="text" class="form-control" name="company" placeholder="company name" value="" />
       		</div>
			<br><br>
			<div class="form-group">
	           <label for="">Price Less Than</label><br>
	           <input type="number" class="form-control" name="price" placeholder="price less than" value="" />
       		</div>
			<br><br>
			<div class="form-group">
	           <label for="">Stock</label><br>
	           <input type="number" class="form-control" name="in_stock" placeholder="stock less than" value="" />
       		</div><br>
			<br>
			<div class="form-group">
	           <label for="">Expiration Date Less than</label><br>
	           <input type="date" class="form-control" name="expiration_date" placeholder="expiration date" value="" />
       		</div>
			<br>
			<br>
			<div class="form-group">
			<input type="submit" value = "Find Medicine"class="btn btn-primary">
			</div>
		</form>
	</div>
	
	<h2>Filter Bills</h2>
	<div class="jumbotron">	 
 		<div class="container">
			<form method="POST" action="emp/showFilteredBill" class="form-inline">
				<div class="form-group">
		           <label for="">Total</label><br>
		           <input type="number" class="form-control" name="total" placeholder="total greater than" value="" />
	       		</div>
			<br><br>
				<div class="form-group">
		           <label for="">Discount Offered</label><br>
		           <input type="number" class="form-control" name="discount_offered" placeholder="Discount Offered Greater Than" value="" />
	       		</div>
			<br><br>
				<div class="form-group">
					<label for="">Employee Issuing</label><br>
					<select name="employee_issuing" class="form-control selectpicker">
						<option value="all"></option>
						   <c:forEach items="${users}" var="user">	
								<option value="${user.username}"> ${user.username} </option>
						   </c:forEach>
					</select>
	       		</div>
			<br><br>
				<div class="form-group">
		           <label for="">Bill Up to</label><br>
		           <input type="date" class="form-control" name="datetime" placeholder="Bill up to" value="" />
	       		</div>
			<hr>
			<br>
			<div class="form-group">
			<input type="submit" value = "Find Bills"class="btn btn-primary">
			</div>
		</form>
	</div>
</div>


     
    
    </div>
</body>
</html>
