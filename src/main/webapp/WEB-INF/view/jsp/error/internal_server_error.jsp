<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.util.Attributes" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<c:set var="error_messages" value="${requestScope[Attributes.ERROR_MESSAGE]}"/>

<body>
<div class="error-wrapper">
    <div class="error-blur in-server-blur"></div>
    <div class="error-inner-wrapper col-lg-offset-2 col-md-offset-2
             col-sm-offset-2 col-lg-8 col-md-8 col-sm-8 text-center">
        <h1><fmt:message key="error.internal.server.number"/></h1><br/>
        <h2><fmt:message key="error.internal.server.header"/></h2><br/>
        <c:if test="${not empty error_messages}">
            <c:forEach var="message" items="${error_messages}">
                <h2><u><strong><fmt:message key="message.error"/></strong>
                    <fmt:message key="${error_messages}" /> </u></h2>
                <br/>
            </c:forEach>
        </c:if>
        <h3>
            <fmt:message key="error.internal.server.text.before.link"/>
            <a href="${Paths.HOME}"><fmt:message key="error.internal.server.link.home.page"/></a>
            <fmt:message key="error.internal.server.text.after.link"/>
        </h3><br/>
        <h3><fmt:message key="error.internal.server.admin.email" /></h3>
    </div>
</div>
</body>
</html>