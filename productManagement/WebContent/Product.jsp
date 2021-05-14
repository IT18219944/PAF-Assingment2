<%@ page import="com.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/product.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>PRODUCT DETAILS</h1>
				<form id="formProduct" name="formProduct" method="post" action="Product.jsp">  
					Product Name: 
 	 				<input id="pName" name="pName" type="text"  class="form-control form-control-sm">
					<br> Manufacture Date:   
  					<input id="pDate" name="pDate" type="text" class="form-control form-control-sm">   
  					<br> Price:   
  					<input id="pPrice" name="pPrice" type="text"  class="form-control form-control-sm">
					<br> Description:   
  					<input id="pDes" name="pDes" type="text" class="form-control form-control-sm">   
  					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divProductGrid">
					<%
						Product productObj = new Product();
					    
						out.print(productObj.readproduct());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>