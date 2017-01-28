<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.javaweb.util.Paths" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<body style="background-color:#BBDEFB;">

<div id="home" class="jumbotron">
    <div class="row ">
        <h1 class="col-lg-12 col-md-12 col-sm-12 col-xs-12 centered-text">Testing portal</h1>
    </div>
    <div class="row">
        <form action="${Paths.LOGIN}" method="get">
            <div class="col-md-12 col-lg-12 col-sm-12 text-center">
                <button type="submit" class="btn btn-lg btn-default">Get started </button>
            </div>
        </form>
    </div>
</div>

<div class="container home-description">
    <p class="home-text"><fmt:message key="home.description"/></p>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
