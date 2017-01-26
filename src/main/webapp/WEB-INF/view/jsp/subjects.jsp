<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javaweb.jsp.Paths" %>
<%@ page import="com.javaweb.jsp.Attributes" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html">
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
<nav class="navbar navbar-fixed-top">
    <div class="navbar-header">
        <a class="navbar-brand" href="${Paths.HOME}">Testing Portal</a>
        <img src="<c:url value="/resources/img/testing-icon.png" />"
             class="img-circle img-resposive" alt="Test your skill"
             width="50" height="50"/>
    </div>
    <ul class="nav navbar-nav">

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
<div class="data col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-8 col-md-8 col-sm-8 col-xs-8">
    <table class="table table-hover table-bordered table-shadow">
        <thead class="thead-changed-style">
        <tr>
            <th>Choose subject :</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subject" items="${requestScope[Attributes.SUBJECTS]}">
            <tr><td>
                <a class="identified" href="${Paths.SUBJECTS}/${subject.id}">
                    <c:out value="${subject.nameOfSubject}"/>
                </a>
            </td></tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>