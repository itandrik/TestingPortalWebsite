<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>
<%@ page import="com.javaweb.model.entity.task.AnswerType" %>
<%@ page import="com.javaweb.util.Parameters" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>

<body onunload="unloadP('UniquePageNameHereScroll')"
      onload="loadP('UniquePageNameHereScroll')">
<nav class="navbar navbar-fixed-top">
    <div class="navbar-header">
        <a class="navbar-brand" href="${Paths.HOME}"><fmt:message key="navbar.title.text" /> </a>
        <img src="<c:url value="/resources/img/testing-icon.png" />"
             class="img-circle img-resposive" alt="Test your skill"
             width="50" height="50"/>
    </div>
    <ul class="nav navbar-nav">
        <!-- TODO insert menu here-->
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <c:if test="${user.role == PersonRole.TUTOR}">
            <li>
                <a class="navbar-brand" href="${Paths.STUDENTS_LIST}">
                    <span class="glyphicon glyphicon-education"></span>
                    <fmt:message key="students.info"/>
                </a>
            </li>
        </c:if>
        <li>
            <a class="navbar-brand" href="${Paths.USER_INFO}/${user.login}">
                <span class="glyphicon glyphicon-user"></span>
                <fmt:message key="account.info"/>
            </a>
        </li>
        <li>
            <a class="navbar-brand" href="${Paths.LOGOUT}">
                <span class="glyphicon glyphicon-log-out"></span>
                <fmt:message key="logout"/>
            </a>
        </li>
    </ul>
</nav>
<c:if test="${not empty requestScope[Attributes.ERROR_MESSAGE]}">
<div class="col-lg-12 floating-error">
    <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
</div>
</c:if>
<script type="text/javascript">
    history.pushState(null, null, location.href);
    window.onpopstate = function(event) {
        history.go(1);
    };
</script>
<div class="data col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-xs-offset-2 col-lg-8 col-md-8 col-sm-8 col-xs-8">
    <c:forEach var="task" items="${requestScope[Attributes.TASKS]}">
        <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}${Paths.UPDATE_TASK}">
            <table class="table table-bordered table-shadow" id="task${task.id}">
                <thead class="thead-changed-style">
                <tr>
                    <th class="tree-header" colspan="2">
                        <div class="form-group col-lg-12">
                        <textarea class="form-control" rows="3" name="${Parameters.QUESTION_PARAMETER}"
                                  style="resize:vertical;">${task.question}</textarea>
                        </div>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="answer" items="${task.answers}">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${task.answerType == AnswerType.ONE_ANSWER}">
                                    <div class="rad col-lg-12">
                                        <div class="input-group">
                                              <span class="input-group-addon">
                                                <input type="radio" aria-label="Radio button for following text input"
                                                       name="${Parameters.ANSWER_PARAMETER}"
                                                       value="${Parameters.ANSWER_TEXT_PARAMETER}${answer.id}"
                                                       <c:if test="${answer.isCorrect}">checked</c:if>>
                                              </span>
                                            <input type="text" class="form-control"
                                                   aria-label="Text input with radio button"
                                                   name="${Parameters.ANSWER_TEXT_PARAMETER}${answer.id}"
                                                   value="${answer.answerText}">
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="check col-lg-12">
                                        <div class="input-group">
                                              <span class="input-group-addon">
                                                <input type="checkbox"
                                                       aria-label="Radio button for following text input"
                                                       name="${Parameters.ANSWER_PARAMETER}"
                                                       value="${Parameters.ANSWER_TEXT_PARAMETER}${answer.id}"
                                                       <c:if test="${answer.isCorrect}">checked</c:if>>
                                              </span>
                                            <input type="text" class="form-control"
                                                   aria-label="Text input with radio button"
                                                   name="${Parameters.ANSWER_TEXT_PARAMETER}${answer.id}"
                                                   value="${answer.answerText}">
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <input type="number" hidden="true" name="${Parameters.TASK_PARAMETER}" value="${task.id}"/>
                    <c:set var="last_answer_id" value="${answer.id}" scope="page"/>
                </c:forEach>
                <tr>
                    <td>
                        <div class="text-center">
                            <button type="button"
                                    onclick="
                                        <c:set var="last_answer_id" value="${last_answer_id + 1}" scope="page"/>
                                            addAnswer(this, '${Parameters.ANSWER_PARAMETER}',
                                            '${Parameters.ANSWER_TEXT_PARAMETER}','${last_answer_id}',
                                            '<fmt:message key="enter.answer.placeholder"/>',
                                        ${task.id})"
                                    class="btn btn-lg btn-primary">
                                <span class="glyphicon glyphicon-plus"></span>
                                <fmt:message key="add.answer"/>
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
                                       name="${Parameters.ANSWER_TYPE_PARAMETER}"
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
                </tbody>
            </table>
        </form>
        <c:set var="last_task_id" value="${task.id}" scope="page"/>
    </c:forEach>

    <div class="col-lg-12">
        <div class="text-center" style="margin:20px 0 100px 0;">
            <button type="button"
                    onclick="getMsg('<fmt:message key="add.answer"/>',
                            '<fmt:message key="task.explanation"/>',
                            '<fmt:message key="task.is.one.answer"/>',
                            '<fmt:message key="test.button.save.answer"/>',
                            '<fmt:message key="enter.question.placeholder"/>',
                            '<fmt:message key="enter.answer.placeholder"/>',
                            '<fmt:message key="enter.explanation.placeholder"/>');
                            addTask('${requestScope['javax.servlet.forward.request_uri']}${Paths.ADD_TASK}',
                            '${Parameters.QUESTION_PARAMETER}',
                            '${Parameters.ANSWER_PARAMETER}',
                            '${Parameters.ANSWER_TEXT_PARAMETER}',
                            '${last_answer_id + 1}',
                            '${Parameters.EXPLANATION_PARAMETER}',
                            '${Parameters.ANSWER_TYPE_PARAMETER}',
                            '${last_task_id + 1}',
                            this);"
                    class="btn btn-primary btn-lg outline" style="width:90%;">
                <span class="glyphicon glyphicon-plus"></span>
                <fmt:message key="add.task"/>
            </button>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>
