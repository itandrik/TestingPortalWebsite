<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/view/taglib/subjects.tld" prefix="subj" %>

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
    <c:forEach var="subject" items="${requestScope[Attributes.SUBJECTS]}">
        <table class="table table-hover table-bordered table-shadow">
            <thead class="thead-changed-style">
            <tr>
                <th class="text-center"><c:out value="${subject.nameOfSubject}"/></th>
            </tr>
            </thead>
            <tbody id="table-with-entities">
            <c:choose>
                <c:when test="${not subj:containsSubject(requestScope[Attributes.TESTS_PASSED_BY_STUDENT],subject)}">
                    <tr>
                        <td>
                            <div class="col-lg-12 text-center">
                                <fmt:message key="test.no.tests.for.subject"/>
                            </div> 
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="test" items="${requestScope[Attributes.TESTS_PASSED_BY_STUDENT]}">
                        <tr>
                            <td>
                                <c:if test="${subject.id == test.subjectId}">
                                    <!-- TODO зробити нормальний виклик сторінки з результатами тесту-->
                                    <a class="identified" href="${Paths.STUDENTS_LIST}/${student.id}${Paths.TEST}">
                                        <c:out value="${test.nameOfTest}"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
<%@ include file="/WEB-INF/view/jsp/template/logout_modal.jsp" %>
