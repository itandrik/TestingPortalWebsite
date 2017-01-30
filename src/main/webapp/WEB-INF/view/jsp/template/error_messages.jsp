<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.javaweb.util.Attributes" %>

<c:set var="error_messages" value="${requestScope[Attributes.ERROR_MESSAGE]}"/>
<c:set var="error_validation_messages" value="${requestScope[Attributes.ERROR_VALIDATION_MESSAGE]}"/>
<c:set var="simple_message" value="${requestScope[Attributes.MESSAGE]}"/>

<c:choose>
    <c:when test="${not empty error_messages}">
        <c:forEach var="message" items="${error_messages}">
            <div class="alert alert-danger alert-dismissable fade in">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong><fmt:message key="message.error"/></strong>
                <fmt:message key="${message}"/>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty error_validation_messages}">
            <c:forEach var="message" items="${error_validation_messages}">
                <div class="alert alert-danger alert-dismissable fade in">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong><fmt:message key="message.error"/></strong>
                    <fmt:message key="${message}"/>
                </div>
            </c:forEach>
        </c:if>
    </c:otherwise>
</c:choose>