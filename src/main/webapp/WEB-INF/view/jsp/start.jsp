<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.javaweb.jsp.Parameters"%>
<%@ page import="com.javaweb.jsp.Paths"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing portal</title>
<link
	href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"
	type="text/css"/>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
<link rel="icon" 
      type="image/ico" 
      href="<c:url value="/resources/img/favicon.ico" />">
</head>
<body>

	<div class="jumbotron">
		<div class="row">
			<h1 class="col-sm-offset-3 col-sm-6">Testing portal</h1>
			<img src="<c:url value="/resources/img/testing-icon.png" />" 
				 class="img-circle img-resposive" alt="Test your skill"
				 width="200" height="200"/>
		</div>
	</div>
	
	<div class="container">
  		<form class="form-horizontal" method="get" action="${Paths.LOGIN}">
    		<div class="form-group">
      			<label class="control-label col-sm-2" for="email">Login:</label>
      			<div class="col-sm-8">
        			<input type="email" class="form-control" id="email" placeholder="Enter email"
        				   name="${Parameters.LOGIN_PARAMETER}">
      			</div>
    		</div>
		    <div class="form-group">
		      	<label class="control-label col-sm-2" for="pwd">Password:</label>
		      	<div class="col-sm-8">          
		        	<input type="password" class="form-control" id="pwd" 
		        		   name="${Parameters.PASSWORD_PARAMETER}" 
		        		   placeholder="Enter password">
		      	</div>
		    </div>
		    <div class="form-group">        
		      	<div class="col-sm-offset-2 col-sm-10">
		        	<div class="checkbox">
		          		<label>
		          			<input type="checkbox" name="${Parameters.REMEMBER_ME_PARAMETER}"/>
		          			Remember me
		          		</label>
		        	</div>
		      </div>
		    </div>
		    <div class="form-group">        
		      	<div class="col-sm-offset-2 col-sm-2">
		        	<button type="submit" class="btn btn-primary btn-lg outline">Submit</button>
		      	</div>
		      	<p class="col-sm-6">
		      		Not registered? 
		      		<a class="btn-link" href="${Paths.REGISTER}">Create an account</a>
		      	</p>
		    </div>
		 </form>
	</div>
	
</body>
</html>