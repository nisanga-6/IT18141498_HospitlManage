<%@page import="com.user"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/users.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div class="Container">
		<div class="raw">
			<div class="col-6">
				<h1>Registration Page</h1>

				<form id="formReg" name="formReg" >
				
					User Name: 
					<input id="username" name="username" type="text" class="form-control form-control-sm">  
					
					<br> Name:
					<input id="name" name="name" type="text" class="form-control form-control-sm"> 
					
				   <br>	Password:
				   <input id="password" name="password" type="text" class="form-control form-control-sm">
					
				 <br>
					<input id ="btnReg" name="btnReg" type="button" value="Register"class="btn btn-primary"> 
						
				
						<input type="hidden" id="hiduserIDReg" name="hiduserIDReg" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divItemsGrid">
				<%
				user userObj= new user();
				out.print(userObj.readUsers());
				
				%>
			</div>
		</div>
	</div>

</body>
</html>