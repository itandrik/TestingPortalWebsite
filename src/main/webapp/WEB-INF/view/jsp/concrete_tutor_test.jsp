<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>
<%@ page import="com.javaweb.model.entity.task.AnswerType" %>
<%@ page import="com.javaweb.util.Parameters" %>

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

<c:if test="${not empty requestScope[Attributes.ERROR_MESSAGE]}">
<div class="col-lg-12 floating-error">
    <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
</div>
</c:if>

<div class="data col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-8 col-md-8 col-sm-8 col-xs-8">
    <c:forEach var="task" items="${requestScope[Attributes.TASKS]}">
        <table class="table table-bordered table-shadow" id="task${task.id}">
            <thead class="thead-changed-style">
            <tr>
                <th class="tree-header" colspan="2">${task.question}</th>
            </tr>
            </thead>
            <tbody>
            <form method="post" action="${Paths.TESTS}/${test.id}">
                <c:forEach var="answer" items="${task.answers}">
                    <tr>
                        <td class="col-lg-12">
                            <c:choose>
                                <c:when test="${task.answerType == AnswerType.ONE_ANSWER}">
                                    <div class="radio">
                                        <label class="col-lg-12">
                                            <input type="radio" name="${Parameters.ANSWER_PARAMETER}"
                                                   value="answer${answer.id}"
                                                    <c:if test="${answer.isCorrect}">checked</c:if>/>
                                            <input type="text" name="${Parameters.ANSWER_TEXT_PARAMETER}"
                                                    value="${answer.answerText}" />
                                        </label>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="${Parameters.ANSWER_PARAMETER}"
                                               value="answer${answer.id}">
                                        <input type="text"
                                               name="${Parameters.ANSWER_TEXT_PARAMETER}"
                                               value="${answer.answerText}"
                                               <c:if test="${answer.isCorrect}">checked</c:if>>
                                    </label>
                                </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <input type="number" hidden="true" name="${Parameters.TASK_PARAMETER}" value="${task.id}"/>
                </c:forEach>
                <tr>
                    <td>
                        <div class="text-center">
                            <button id="add-button" type="button"
                                    onclick="addAnswer(this, ${Parameters.ANSWER_PARAMETER},
                                            '${Parameters.ANSWER_TEXT_PARAMETER}',${task.id})"
                                    class="btn btn-lg btn-primary">
                                <span class="glyphicon glyphicon-plus"></span>
                                <fmt:message key="add.answer" />
                            </button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="comment"><fmt:message key="task.explanation"/></label>
                            <textarea class="form-control" rows="6"
                                      id="comment" style="resize:vertical;"
                                      name="${Parameters.EXPLANATION_PARAMETER}">${task.explanation}</textarea>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"
                                       name="${Parameters.ANSWER_TYPE_PARAMETER}${task.id}"
                                       onchange="handleChange(this, ${task.id});"
                                       <c:if test="${task.answerType == AnswerType.ONE_ANSWER}">checked</c:if>>
                                <fmt:message key="task.is.one.answer"/>
                            </label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="text-right">
                            <button type="submit" class="btn btn-lg btn-primary">
                                <fmt:message key="test.button.save.answer"/>
                            </button>
                        </div>
                    </td>
                </tr>
            </form>
            </tbody>
        </table>
    </c:forEach>

</div>
<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
