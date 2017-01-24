<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javaweb.jsp.Parameters" %>
<%@ page import="com.javaweb.jsp.Paths" %>
<%@ page import="com.javaweb.jsp.Pages" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Testing portal</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
          type="text/css"/>
    <link rel="icon"
          type="image/ico"
          href="<c:url value="/resources/img/favicon.ico" />">
</head>
<body>

<nav class="navbar">
    <div class="navbar-header">
        <a class="navbar-brand" href="${Paths.HOME}">Testing Portal</a>
        <img src="<c:url value="/resources/img/testing-icon.png" />"
             class="img-circle img-resposive" alt="Test your skill"
             width="50" height="50"/>
    </div>
    <ul class="nav navbar-nav">
        <%--<li class="active"><a href="#">Home</a></li>
        <li><a href="#">Page 1</a></li>
        <li><a href="#">Page 2</a></li>--%>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li>
            <a class="navbar-brand" href="${Paths.LOGOUT}">
                <span class="glyphicon glyphicon-log-out"></span>
                Logout
            </a>
        </li>
    </ul>
</nav>
<div class="container">

</div>
</body>
</html>