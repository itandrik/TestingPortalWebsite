<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.javaweb.util.Parameters" %>
<%@ page import="com.javaweb.util.Paths" %>
<%@ page import="com.javaweb.model.entity.person.Gender" %>
<%@ page import="com.javaweb.model.entity.person.PersonRole" %>

<%@ include file="/WEB-INF/view/jsp/template/header.jsp" %>
<%@ include file="/WEB-INF/view/jsp/template/header_jumbotron.jsp" %>

<body>
<div class="container">
    <form class="form-horizontal" method="post" action="${Paths.REGISTER_SUBMIT}">
        <div class="form-group">
            <label class="control-label col-sm-2">First name:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="Enter first name"
                       name="${Parameters.FIRST_NAME_PARAMETER}">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Second name:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="Enter second name"
                       name="${Parameters.SECOND_NAME_PARAMETER}">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Gender:</label>
            <div class="col-sm-8">
                <div class="radio">
                    <label><input type="radio" name="${Parameters.GENDER_PARAMETER}"
                                  value="${Gender.MALE}">Male</label>
                </div>
                <div class="radio">
                    <label><input type="radio" name="${Parameters.GENDER_PARAMETER}"
                                  value="${Gender.FEMALE}">Female</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Login:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" placeholder="Enter login"
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
            <label class="control-label col-sm-2" for="pwd">Role:</label>
            <div class="col-sm-8">
                <div class="radio">
                    <label><input type="radio" name="${Parameters.PERSON_ROLE_PARAMETER}"
                                  value="${PersonRole.TUTOR}">Tutor</label>
                </div>
                <div class="radio">
                    <label><input type="radio" name="${Parameters.PERSON_ROLE_PARAMETER}"
                                  value="${PersonRole.STUDENT}">Student</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-2">
                <button type="submit" class="btn btn-primary btn-lg outline">Register</button>
            </div>
        </div>

    </form>
</div>

<%@ include file="/WEB-INF/view/jsp/template/footer.jsp" %>