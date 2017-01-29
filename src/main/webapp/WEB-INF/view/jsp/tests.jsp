<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
                    <button type="button" class="identified btn btn-link btn-lg"
                            data-toggle="modal" data-target="#modal_start_test">
                        <c:out value="${test.nameOfTest}"/>
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
                                    <form action="${Paths.TESTS}/${test.id}">
                                        <button type="submit" class="btn btn-default">
                                            <p><fmt:message key="modal.start.test.button"/></p>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <c:out value="${test.durationTimeInMinutes}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<c:import url="/WEB-INF/view/jsp/template/footer.jsp"/>
<c:import url="/WEB-INF/view/jsp/template/logout_modal.jsp"/>


