<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="test" uri="/WEB-INF/view/taglib/test.tld" %>

<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>
<%@ page import="com.javaweb.util.Parameters" %>
<%@ page import="com.javaweb.model.entity.person.PersonRole" %>

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
            <th class="tree-header" colspan="2">Choose test</th>
        </tr>
        <tr>
            <th class="tree-header col-lg-4">Name of test</th>
            <th class="tree-header col-lg-4">Duration time in minutes</th>
        </tr>
        </thead>
        <tbody id="table-with-entities">
        <c:forEach var="test" items="${requestScope[Attributes.TESTS]}">
            <tr>
                <td>
                    <c:set var="is_test_passed" value="${test:isTestPassed(test,user)}"/>
                    <c:choose>
                        <c:when test="${user.role == PersonRole.TUTOR}">
                            <button type="button" class="identified btn btn-link btn-lg"
                                    data-toggle="modal" data-target="#modal_start_test"
                                    <c:if test="${is_test_passed}">disabled</c:if>>
                                <c:out value="${test.nameOfTest}"/>
                                <c:if test="${is_test_passed}">
                                    <fmt:message key="test.passed"/>
                                </c:if>
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="modal_start_test" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">
                                                <fmt:message key="modal.start.test.header"/>
                                                <c:out value="${test.nameOfTest}"/>
                                            </h4>
                                        </div>
                                        <div class="modal-body">
                                            <p><fmt:message key="modal.start.test.text"/></p>
                                        </div>
                                        <div class="modal-footer">
                                            <form action="${Paths.TESTS}${Paths.STUDENT}/${test.id}">
                                                <button type="submit" class="btn btn-default">
                                                    <p><fmt:message key="modal.start.test.button"/></p>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form method="get" action="${Paths.TESTS}${Paths.TUTOR}/${test.id}">
                                <button type="submit" class="identified btn btn-link btn-lg">
                                    <c:out value="${test.nameOfTest}"/>
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:out value="${test.durationTimeInMinutes}"/>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${user.role == PersonRole.TUTOR}">
            <tr>
                <td colspan="2">
                    <div class="text-center">
                            <%--
                                                    <input id="tests-param" type="hidden" name="${Parameters.TESTS}" value="${requestScope[Attributes.TESTS]}" />
                            --%>
                        <button id="add-button"
                                onclick="addEntity('${requestScope['javax.servlet.forward.request_uri']}',
                                        '${Parameters.NAME_OF_TEST_PARAMETER}')"
                                class="btn btn-lg btn-primary">
                            <span class="glyphicon glyphicon-plus"></span>
                            Add new test
                        </button>
                    </div>
                </td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>
<c:import url="/WEB-INF/view/jsp/template/footer.jsp"/>
<c:import url="/WEB-INF/view/jsp/template/logout_modal.jsp"/>


