<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>


<head>
<title>Add Bills</title>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body background="#EE350E" onload="onStart()">

<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Medical Shop</a>
				<br>
				<a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>Logout</a>
			</div>
		</div>
</nav>






<div class="container">
	<form:form method="post" modelAttribute="bill" action="addBill" class="form-inline" id="theFormID">
    	<br><br><br><br>
    	
    	<h1>Please Enter Details</h1>
    	<hr>
    	
		<div class="form-group">
	    	<h3>For Who :</h3>
			<select class="form-control selectpicker" name="for_who" id="for_who" onchange="toggleVisibility()">
				<option value="-"></option>	
				<option value="Partner"> Partner </option>
				<option value="Patient"> Patient </option>
			</select>
		</div>
    	<br>
    	
		<div class="form-group" >
    	<h3  id="lb1">Partner :</h3>
		<select class="form-control selectpicker" name="partner_id" id="partner" >
			   <c:forEach items="${partners}" var="partner">	
					<option value="${partner.id}"> ${partner.name} </option>
			   </c:forEach>
		</select>
		</div>

		
		
		<div class="form-group">
    	<h3  id="lb2">Patient :</h3>
		<select class="form-control selectpicker" name="patient_id" id="patient" >
			   <c:forEach items="${patients}" var="patient">	
					<option value="${patient.id}"> ${patient.name} </option>
			   </c:forEach>
		</select>
		</div>
		
		<br><br>
	
    	
		
		<input class="btn btn-primary" type="button" value="Add Medicine" onclick="add()"/>
		<br>
		<br>
		<span id="fooBar">&nbsp;</span>		
		
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
			
			function toggleVisibility()
			{
				var selectList = document.getElementById("for_who");
				var select_1 = document.getElementById("partner");
				var select_2 = document.getElementById("patient");
				var select_11 = document.getElementById("lb1");
				var select_22 = document.getElementById("lb2");
				var val = selectList.options[selectList.selectedIndex].value;
				if(val==="Partner")
				{
				    select_1.style.display = 'block';
				    select_2.style.display = 'none';
				    select_11.style.display = 'block';
				    select_22.style.display= 'none';
				}
				else if(val==="Patient")
				{
				    select_1.style.display= 'none';
				    select_2.style.display= 'block';
				    select_11.style.display = 'none';
				    select_22.style.display= 'block';
				}
			}
			
			function fun()
			{
				var selectList = document.getElementById("payment");
				var select_33 = document.getElementById("lb3");
				var select_3 = document.getElementById("transaction");
				var val = selectList.options[selectList.selectedIndex].value;
				if(val==="Online_Payment")
				{
				    select_3.style.display = 'block';
				    select_33.style.display= 'block';
				}
				else
				{
				    select_3.style.display = 'none';
				    select_33.style.display= 'none';
				}
			}
			
			

			
			function onStart(){
				var select_1 = document.getElementById("partner");
				var select_2 = document.getElementById("patient");
				var select_11 = document.getElementById("lb1");
				var select_22 = document.getElementById("lb2");
				var select_3 = document.getElementById("transaction");
				var select_33 = document.getElementById("lb3");
			    select_1.style.display= 'none';
			    select_2.style.display= 'none';
			    select_11.style.display = 'none';
			    select_22.style.display= 'none';
			    select_3.style.display = 'none';
			    select_33.style.display= 'none';
				
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
		
		<input type="hidden" id="hid" name="cnt" value="-1">
		
	
		
		
		<div class="form-group">
	    	<h3 >Payment Mode</h3>
			<form:select  class = "form-control selectpicker" path="payment_mode" id="payment" onchange="fun()">	
				<option value="Cash"> Cash </option>
				<option value="Online_Payment"> Online Payment </option>
			</form:select>
		</div>
    	<br><br>
    	
    	
 		<div class="form-group">
			<label for="">Discount Offered</label><br>
			<form:input path="discount_offered" type="number" class="form-control" placeholder="discount offered" value="" required="required"/>
			<form:errors path="discount_offered" />
		</div>
		<br><br>
		
 		<div class="form-group">
			<label id="lb3" for="">transaction id</label><br>
			<form:input id="transaction" path="transaction_id" type="number" class="form-control" placeholder="transaction id" value="0" required=""/>
		</div>
		<br><br>
		

  	
		<hr>
		<button type="button" class="btn btn-primary" onclick="return check_all_clicked()">Submit</button>		
		<br>
		<td>${error}</td>
	</form:form>
	</div>
	<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>

</html>