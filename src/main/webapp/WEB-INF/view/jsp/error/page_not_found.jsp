<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.javaweb.util.Paths" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<body>
    <div class="error-wrapper">
        <div class="not-found-blur error-blur"></div>
        <div class="error-inner-wrapper col-lg-offset-2 col-md-offset-2
         col-sm-offset-2 col-lg-8 col-md-8 col-sm-8 text-center">
            <h1><fmt:message key="error.page.not.found.number" /> </h1><br/>
            <h2><fmt:message key="error.page.not.found.header" /></h2><br/>

            <h3>
                <fmt:message key="error.page.not.found.text.before.link" />
                <a href="${Paths.HOME}"><fmt:message key="error.page.not.found.link.home.page"/></a>
                <fmt:message key="error.page.not.found.text.after.link" />
            </h3>
        </div>
    </div>
</body>
</html>