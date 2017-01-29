<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="com.javaweb.util.Paths" %>

<div class="modal fade" id="modal_logout" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"><fmt:message key="modal.logout.header"/></h4>
            </div>
            <div class="modal-body">
                <p><fmt:message key="modal.logout.text"/></p>
            </div>
            <div class="modal-footer">
                <form action="${Paths.LOGOUT}">
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
