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
					<li><a href="user/updateProfile"><span class="glyphicon glyphicon-user"></span> Update Profile</a></li>	
					<li><a href="user/updateAddress"><span class="glyphicon glyphicon-home"></span> Update Address</a></li>
   					<li><a href="user/viewProfile"><span class="glyphicon glyphicon-user"></span>  My Profile</a></li>
   					<li><a href="user/showAllMedicine"><span class="glyphicon glyphicon-apple"></span>  All Medicine</a></li>
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
    
   	<h2>Order Medicines</h2>
    <div class="jumbotron">
	<form:form method="post" modelAttribute="orders" action="user/addorder" class="form-inline" id="theFormID">
    
		<input class="btn btn-primary" type="button" value="Add Medicine" onclick="add()"/>
		<br><br>
		<span id="fooBar">&nbsp;</span>		
		<input type="hidden" id="hid" name="cnt" value="-1">
		
		<script type="text/javascript">
			var cnt=-1;
			var medicines_id = [];
			var medicines_name = [];
			var check_value = [];
			<c:forEach var="med" items="${medicines}">
				medicines_id.push("${med.id}");
				medicines_name.push("${med.name}");
			</c:forEach>
			
			
			function add()
			{
				if(cnt!=-1 && check_value[cnt]!=1)
				{
					alert("Medicine "+(cnt+1)+" is not verified" );
					return ;
				}
				cnt++;
				check_value.push(-1);
				var selectList = document.createElement("select");
				var inputBox = document.createElement("input");
				var myParent   = document.getElementById("fooBar");
				var hiddenTextBox   = document.getElementById("hid");
				var verifyButton = document.createElement("input");
				var something = document.createElement("hr");
				
				selectList.id = "medicine"+cnt;
				selectList.name = "medicine"+cnt;
				selectList.className = "form-control selectpicker";
				
				inputBox.id = "quantity"+cnt;
				inputBox.name = "quantity"+cnt;
				inputBox.placeholder = "quantity";
				inputBox.value = 0;
				inputBox.type = "number";
				inputBox.className="form-control";
				
				verifyButton.id = "button"+cnt;
				verifyButton.name = "button"+cnt
				verifyButton.type = "button";
				verifyButton.value = "Verify";
				verifyButton.className="btn btn-primary";
				
				hiddenTextBox.value=cnt;

				//Create and append the options
				for (var i = 0; i < medicines_id.length; i++) {
				    var option = document.createElement("option");
				    option.value = medicines_id[i];
				    option.text = medicines_name[i];
				    selectList.appendChild(option);
				}
				
				
				
				myParent.appendChild(selectList);
				myParent.appendChild(document.createElement("br"));
				myParent.appendChild(document.createElement("br"));
				myParent.appendChild(inputBox);
				myParent.appendChild(document.createElement("br"));
				myParent.appendChild(document.createElement("br"));
				myParent.appendChild(verifyButton);
				myParent.appendChild(something);
				var dup = cnt;
				verifyButton.onclick = function() {verify_data(parseInt(dup),selectList.options[selectList.selectedIndex].value)};
				selectList.onchange = function() {change_verified_status(parseInt(cnt))};
			}
			
			function change_verified_status(index)
			{
				index = parseInt(index);
				check_value[index]=0;
			}
			
			function verify_data(cnt_this,medicine_id)
			{
				cnt_this = parseInt(cnt_this);
				medicine_id=parseInt(medicine_id);
				var required_quantity = document.getElementById("quantity"+cnt_this).value;
				required_quantity=parseInt(required_quantity);
				if(required_quantity<=0)
				{
					alert("Quantity Can't be less than or equal to 0");
					return ;
				}
				var flag=0;
				<c:forEach var="med" items="${medicines}">
					var med_id = "${med.id}";
					var med_in_stock = "${med.in_stock}";
					med_id=parseInt(med_id);
					med_in_stock=parseInt(med_in_stock);
					if(med_in_stock >= required_quantity && med_id===medicine_id){
						flag=1;	
						var index = parseInt(cnt_this);
						check_value[index]=1;
					}
				</c:forEach>
				
				if (flag===0){
					var index = parseInt(cnt_this);
					check_value[index]=0;
					alert("We Don't have this much stock");
				}	

			}

		
			
			function check_all_clicked()
			{
				var flag=1;
				for(var i=0;i<=cnt;i++)
				{
					if(check_value[i]!=1)
					{
						alert("Medicine "+(i+1)+" is not verified" );
						flag=0;	
					}
				}
				
				if(flag==1)
				{
					document.getElementById("theFormID").submit();
					return(true);
				}
				else
				{
					return(false);
				}
				
			}
			
			
		
		</script>
		
		<div class="form-group">
			<label for="">Partner Id</label><br>
			<form:input path="ordered_by" type="number" class="form-control" placeholder="partner_id" value="" required="required"/>
			<form:errors path="ordered_by" />
		</div>
		<hr>
		<button type="button" class="btn btn-primary" onclick="return check_all_clicked()">Order</button>		
		<br>
		</form:form>	
		
	</div>	
	
	<h2>Feedback</h2>
	
	<div class="jumbotron">
	
		
	
		<form:form method="post" modelAttribute="feedback" action="user/addFeedback" class="form-inline" id="theFormID2">
			<div class="form-group">
				<label for="">Partner Id</label><br>
				<form:input path="partner_id" type="number" class="form-control" placeholder="partner_id" value="" required="required"/>
				<form:errors path="partner_id" />
			</div>
			
			<br><br>
			
			<div class="form-group">
				<label for="">Feedback</label><br>
				<form:input path="feedback" type="textarea" class="form-control" placeholder="feedback" value="" required="required"/>
				<form:errors path="feedback" />
			</div>
			
			<br><br>
			
			<hr>
			<button type="submit" class="btn btn-primary">Submit</button>	
			
			
		
		</form:form>
	
	
	</div>
	
	
	</div>
</body>
</html>
