<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="com.javaweb.jsp.Paths" %>
<%@ page import="com.javaweb.jsp.Attributes" %>
<%@ page import="com.javaweb.jsp.Parameters" %>
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
        <l1>
            <a class="navbar-brand" href="${Paths.USER_INFO}">
                <span class="glyphicon glyphicon-user"></span>
                Account info
            </a>
        </l1>
        <li>
            <a class="navbar-brand" href="${Paths.LOGOUT}">
                <span class="glyphicon glyphicon-log-out"></span>
                Logout
            </a>
        </li>
    </ul>
</nav>

<%--<c:choose>
    <c:when test="${fn:length(requestScope[Attributes.SUBJECTS] == 0)}">
     <h1 class="text-center">No subjects in the database</h1>
    </c:when>
<c:otherwise>--%>
<div class="data col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-8 col-md-8 col-sm-8 col-xs-8">
    <table class="table table-hover table-bordered table-shadow">
        <thead class="thead-changed-style">
        <tr>
            <th>Choose subject :</th>
        </tr>
        </thead>
        <tbody id="table-with-subjects">
        <c:forEach var="subject" items="${requestScope[Attributes.SUBJECTS]}">
            <tr>
                <td>
                    <a class="identified" href="${Paths.SUBJECTS}/${subject.id}">
                        <c:out value="${subject.nameOfSubject}"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${sessionScope[Attributes.USER].role == PersonRole.TUTOR}">
            <tr>
                <td>
                    <div class="text-center">
                        <button id="add-button"
                                onclick="createFormToAddSubject('${Paths.ADD_SUBJECT}','${Parameters.NAME_OF_SUBJECT_PARAMETER}')"
                                class="btn btn-lg btn-primary">
                            <span class="glyphicon glyphicon-plus"></span>
                            Add new subject
                        </button>
                    </div>
                </td>
            </tr>
        </c:if>

        </tbody>
    </table>
</div>
<%--</c:otherwise>
</c:choose>--%>
<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
