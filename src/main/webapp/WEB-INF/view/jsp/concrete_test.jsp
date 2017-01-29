<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>
<%@ page import="com.javaweb.model.entity.task.AnswerType" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<body onload="startTimer(${requestScope[Attributes.TEST_TIME_DURATION]})">
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
<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 time-and-submit">
    <div class=row">
        <h1 class="text-center">Time</h1>
    </div>
    <div class="row text-center">
        <p id="minutes" class="col-lg-5 timer text-right"></p>
        <p class="col-sm-2 timer">:</p>
        <p id="seconds" class="col-lg-5 timer text-left"></p>
    </div>
    <div class=row">
        <button class="btn btn-default btn-bottom col-lg-12 text-center"
                data-toggle="modal" data-target="#modal_finish_test">
            <fmt:message key="finish.the.test.button"/>
        </button>
    </div>
</div>
<div class="data col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-8 col-md-8 col-sm-8 col-xs-8">
    <c:forEach var="task" items="${requestScope[Attributes.TASKS]}">
        <table class="table table-bordered table-shadow">
            <thead class="thead-changed-style">
            <tr>
                <th class="tree-header" colspan="2">${task.question}</th>
            </tr>
            </thead>
            <tbody>
            <form method="post" action="${Paths.ADD_ANSWER_TO_HISTORY}">
                <c:forEach var="answer" items="${task.answers}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${task.answerType == AnswerType.ONE_ANSWER}">
                                    <div class="radio">
                                        <label><input type="radio" name="answer${answer.id}">${answer.answerText}
                                        </label>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="checkbox">
                                        <label><input type="checkbox" name="answer${answer.id}">${answer.answerText}
                                        </label>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <div class="text-right">
                            <button type="submit" class="btn btn-lg btn-primary">Accept and write to database
                            </button>
                        </div>
                    </td>
                </tr>
            </form>
            </tbody>
        </table>
    </c:forEach>
</div>

<!-- MODAL -->
<div class="modal fade" id="modal_finish_test" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><fmt:message key="modal.finish.the.test.header"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="modal.finish.the.test.text"/></p>
            </div>
            <div class="modal-footer">
                <form action="${Paths.SAVE_TEST_RECORD}" method="post">
                    <button type="submit" class="btn btn-default">
                        <p><fmt:message key="modal.button.yes"/></p>
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <p><fmt:message key="modal.button.no"/></p>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
