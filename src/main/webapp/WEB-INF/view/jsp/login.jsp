<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.javaweb.util.Parameters" %>
<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<%@ include file="/WEB-INF/view/jsp/template/header_jumbotron.jsp" %>
<c:set var="saved_login" value="${requestScope[Attributes.LOGIN_DATA].login}"/>
<body>
<div class="container">
    <form class="form-horizontal" method="post" action="${Paths.LOGIN}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="email">
                <fmt:message key="login.login.text" />
            </label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="email"
                       placeholder="<fmt:message key="login.login.placeholder"/>"
                <c:if test="${saved_login != null}"> value="${saved_login}" </c:if>
                       name="${Parameters.LOGIN_PARAMETER}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">
                <fmt:message key="login.password.text"/>
            </label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="pwd"
                       name="${Parameters.PASSWORD_PARAMETER}"
                       placeholder="<fmt:message key="login.password.placeholder"/>">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="${Parameters.REMEMBER_ME_PARAMETER}"/>
                        <fmt:message key="login.remember.me.text" />
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button type="submit" class="btn btn-primary btn-lg outline">
                    <fmt:message key="login.submit.button"/>
                </button>
            </div>
            <p class="col-sm-6">
                <fmt:message key="login.not.registered.text"/>
                <a class="btn-link" href="${Paths.REGISTER}">
                    <fmt:message key="login.create.new.account.text" />
                </a>
            </p>
        </div>
    </form>
    <div class="container">
        <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
    </div>
</div>
<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>