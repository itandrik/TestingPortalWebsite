<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>
<%@ page import="com.javaweb.model.entity.person.PersonRole" %>

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

    </ul>
    <ul class="nav navbar-nav navbar-right">
        <li>
            <a class="navbar-brand" href="${Paths.USER_INFO}/${user.login}">
                <span class="glyphicon glyphicon-user"></span>
                <fmt:message key="account.info"/>
            </a>
        </li>
        <li>
            <a class="navbar-brand" data-toggle="modal" data-target="#modal_logout">
                <span class="glyphicon glyphicon-log-out"></span>
                <fmt:message key="logout"/>
            </a>
        </li>
    </ul>
</nav>

<div class="col-lg-12 floating-error">
    <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
</div>

<div class="data col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-8 col-md-8 col-sm-8 col-xs-8">
    <table class="table table-hover table-bordered table-shadow">
        <thead class="thead-changed-style">
        <tr>
            <th> <fmt:message key="students.table.header"/> </th>
        </tr>
        </thead>
        <tbody id="table-with-entities">
        <c:forEach var="student" items="${requestScope[Attributes.STUDENTS]}">
            <tr>
                <td>
                    <a class="identified" href="${Paths.STUDENTS_LIST}/${student.id}${Paths.TEST}">
                        <c:out value="${student.firstName} ${student.secondName} [${student.login}]"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
<%@ include file="/WEB-INF/view/jsp/template/logout_modal.jsp" %>
