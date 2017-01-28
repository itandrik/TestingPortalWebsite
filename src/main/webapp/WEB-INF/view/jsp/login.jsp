<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <label class="control-label col-sm-2" for="email">Login:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="email" placeholder="Enter login"
                <c:if test="${saved_login != null}"> value="${saved_login}" </c:if>
                       name="${Parameters.LOGIN_PARAMETER}">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="pwd"
                       name="${Parameters.PASSWORD_PARAMETER}"
                       placeholder="Enter password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="${Parameters.REMEMBER_ME_PARAMETER}"/>
                        Remember me
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button type="submit" class="btn btn-primary btn-lg outline">Submit</button>
            </div>
            <p class="col-sm-6">
                Not registered?
                <a class="btn-link" href="${Paths.REGISTER}">Create an account</a>
            </p>
        </div>
    </form>
    <div class="container">
        <%@ include file="/WEB-INF/view/jsp/template/error_messages.jsp" %>
    </div>
</div>
<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>