<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<c:set var="user" value="${sessionScope[Attributes.USER]}"/>

<body>
<nav class="navbar navbar-fixed-top">
    <div class="navbar-header">
        <a class="navbar-brand" href="${Paths.HOME}">Testing Portal</a>
        <img src="<c:url value="/resources/img/testing-icon.png" />"
             class="img-circle img-resposive" alt="Test your skill"
             width="50" height="50"/>
    </div>
    <ul class="nav navbar-nav">
        <!-- TODO insert menu here-->
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li>
            <a class="navbar-brand" data-toggle="modal" data-target="#modal_logout">
                <span class="glyphicon glyphicon-log-out"></span>
                <fmt:message key="logout"/>
            </a>
        </li>
    </ul>
</nav>

<div class="data container">
    <div class="panel panel-info">
        <div class="panel-heading text-center">Your account</div>
        <div class="panel-body">
            <div class="row">
                <strong><p class="col-lg-2"><fmt:message key="user.account.first.name"/></p></strong>
                <p class="col-lg-8"><c:out value="${user.firstName}"/></p>
            </div>
            <div class="row">
                <strong><p class="col-lg-2"><fmt:message key="user.account.second.name"/></p></strong>
                <p class="col-lg-8"><c:out value="${user.secondName}"/></p>
            </div>
            <div class="row">
                <strong><p class="col-lg-2"><fmt:message key="user.account.gender"/></p></strong>
                <p class="col-lg-8"><c:out value="${user.gender}"/></p>
            </div>
            <div class="row">
                <strong><p class="col-lg-2"><fmt:message key="user.account.login"/></p></strong>
                <p class="col-lg-8"><c:out value="${user.login}"/></p>
            </div>
            <div class="row">
                <strong><p class="col-lg-2"><fmt:message key="user.account.role"/></p></strong>
                <p class="col-lg-8"><c:out value="${user.role}"/></p>
            </div>
        </div>
    </div>
    <div class="col-sm-8 col-md-8 col-lg-8">
        <button type="submit" onclick="history.back()" class="btn btn-primary btn-lg outline">Back</button>
    </div>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
<c:import url="/WEB-INF/view/jsp/template/logout_modal.jsp"/>
