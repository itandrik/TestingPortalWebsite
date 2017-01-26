<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.javaweb.jsp.Paths" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Testing portal</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
          type="text/css"/>
    <link rel="icon"
          type="image/ico"
          href="<c:url value="/resources/img/favicon.ico" />">
</head>
<body style="background-color:#BBDEFB;">

<div id="home" class="jumbotron">
    <div class="row ">
        <h1 class="col-lg-12 col-md-12 col-sm-12 col-xs-12 centered-text">Testing portal</h1>
    </div>
    <div class="row">
        <form action="${Paths.LOGIN}" method="get">
            <div class="col-lg-offset-5 col-lg-1 col-md-offset-5 col-md-1 col-sm-offset-5 col-sm-1">
                <button type="submit" class="btn btn-lg btn-success">Get started </button>
            </div>
        </form>
    </div>
</div>

<div class="container home-description">
    <p id="home-text"><fmt:message key="home.description"/></p>
</div>

</body>
</html>