<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Registration User</title>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body background="#EE350E">


<script type="text/javascript">

function validateEmail(emailField){
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    if (reg.test(emailField.value) == false) 
    {
        alert('Invalid Email Address');
        return false;
    }

    return true;

}

</script>



<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
			</div>
		</div>
</nav>

<div class="container">
	<br><br><br><br><br><br>
	<form:form method="post" modelAttribute="user" action="registerUser" class="form-inline">
    	<h2>Please fill in this form to register.</h2>
    	<hr>
		<div class="form-group">
            <label for="">USERNAME</label><br>
            <form:input type="text" class="form-control" path="username" placeholder="username" value=""  required="required" />
            <form:errors path="username" />
        </div>
		<br>
		<br>
		<div class="form-group">
			<label for="">PASSWORD</label><br>
			<form:input path="password" type="password" class="form-control" placeholder="password" value="" required="required" />
			<form:errors path="password" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Confirm password</label><br>
			<form:input path="mpassword" type="password" class="form-control" value="" placeholder="confirm password" required="required" />
			<form:errors path="mpassword" />
		</div>
		<br>
		<br>
		<div class="form-group">
            <label for="">Email</label><br>
            <form:input type="text" path = "email" class="form-control"  placeholder="Email" value=""  required="required" onblur="validateEmail(this);"  />
        </div>
		<br>
		<br>
		<div class="form-group">
			<label for="">First Name</label><br>
			<form:input path="firstname" type="text" class="form-control" placeholder="firstname" value="" required="required" />
			<form:errors path="firstname" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Last Name</label><br>
			<form:input path="lastname" type="text" class="form-control" placeholder="lastname" value="" required="required" />
			<form:errors path="lastname" />
		</div>
		<br>
		<br>
		<div class="form-group">
			<label for="">Date Of Birth</label><br>
			<form:input path="DOB" type="date" class="form-control" placeholder="date of birth" value="" required="required" />
			<form:errors path="DOB" />
		</div>
		<br>
		<br>		
		<div class="form-group">
			<label for="">Gender</label><br>
            <form:radiobutton name="gender" path="gender" value="M" label="Male" required="required" /> 
            <form:radiobutton name="gender" path="gender" value="F" label="Female" />
			<form:errors path="gender" />
		</div>
		<br>
		<br>
		
		
		<div class="form-group">
           <label for="">House No</label><br>
           <input type="number" class="form-control" name="house_no" placeholder="Shop No" value="" required="required" />
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
		<td>${error}</td>
	</form:form>
	</div>
	<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>