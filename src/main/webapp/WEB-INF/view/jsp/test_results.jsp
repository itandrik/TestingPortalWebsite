<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/view/taglib/test_result_tag.tld" prefix="testResult" %>

<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>
<%@ page import="com.javaweb.model.entity.task.AnswerType" %>
<%@ page import="com.javaweb.util.Parameters" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/test_result1.js" />"></script>

<c:set var="tutor_tasks" value="${requestScope[Attributes.TASKS]}"/>
<c:set var="student_tasks" value="${requestScope[Attributes.STUDENT_TASKS]}"/>

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
    <c:forEach var="task" items="${tutor_tasks}" varStatus="status">
        <testResult:setCountOfCorrectAnswers var="count_of_all_correct_answers" value="${task}"/>
        <testResult:setCountOfCorrectAnswers var="count_of_student_answers" value="${student_tasks[status.index]}"/>

        <c:set var="is_correct_answer"
               value="${count_of_all_correct_answers == count_of_student_answers}"/>
        <table class="table table-bordered table-shadow" id="task${task.id}">

            <thead class="thead-changed-style">
            <tr>
                <th class="tree-header" colspan="2">
                        ${task.question}
                    <span id="glyphicon-result${task.id}"></span>
                </th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="answer" items="${task.answers}" varStatus="status_answer">
                <c:set var="is_incorrect_student_answer"
                       value="${testResult:contains(student_tasks[status.index].answers, answer)}" />
                <c:set var="is_empty_student_answers"
                       value="${fn:length(student_tasks[status.index].answers) == 0}" />
                <tr>
                    <td id="td${answer.id}">
                        <c:choose>
                            <c:when test="${task.answerType == AnswerType.ONE_ANSWER}">
                                <div class="radio disabled">
                                    <label>
                                        <input type="radio" name="${Parameters.ANSWER_PARAMETER}/${answer.id}"
                                               value="answer${answer.id}" disabled
                                        <c:if test="${answer.isCorrect or is_incorrect_student_answer}">
                                               checked="checked" </c:if>>
                                            ${answer.answerText}
                                    </label>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="checkbox disabled">
                                    <label>
                                        <input type="checkbox" name="${Parameters.ANSWER_PARAMETER}"
                                               value="answer${answer.id}" disabled
                                        <c:if test="${answer.isCorrect or is_incorrect_student_answer}">
                                               checked="checked"</c:if>>
                                            ${answer.answerText}
                                    </label>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <script type="text/javascript">
                            performColorForAnswer(${answer.id}, ${is_incorrect_student_answer}, ${answer.isCorrect});
                        </script>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>
                    <label>
                        <fmt:message key="task.explanation"/>
                    </label>
                    <c:if test="${task.explanation == null}"><fmt:message key="task.empty.explanation"/></c:if>
                    <c:out value="${task.explanation}"/>
                </td>
            </tr>
            </tbody>
        </table>

        <script type="text/javascript">
            makeTableHeaderColor(${is_correct_answer}, ${task.id});
        </script>
    </c:forEach>
</div>

</body>
</html>

