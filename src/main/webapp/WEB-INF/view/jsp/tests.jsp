<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

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
            <th class="tree-header" colspan="2">Choose test</th>
        </tr>
        <tr>
            <th class="tree-header col-lg-4">Name of test</th>
            <th class="tree-header col-lg-4">Duration time in minutes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="test" items="${requestScope[Attributes.TESTS]}">
            <tr>
                <td>
                    <a class="identified" href="${Paths.TESTS}/${test.id}">
                        <c:out value="${test.nameOfTest}"/>
                    </a>
                </td>
                <td>
                    <c:out value="${test.durationTimeInMinutes}" />
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>

