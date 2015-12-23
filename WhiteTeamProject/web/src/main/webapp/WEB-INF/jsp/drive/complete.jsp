<%--
  User: Mário Kudoláni
  Date: 23.12.2015
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="New Drive">
    <jsp:attribute name="body">
            <div class="row">
                <h3>Please, enter the mileage</h3>
                <form:form method="post" action="${pageContext.request.contextPath}/drive/complete/${drive.id}" modelAttribute="drive" cssClass="col-md-5">
                    <div class="form-group">
                        <form:label path="vehicle">Km</form:label>
                        <div class="input-group">
                            <form:input path="km" cssClass="form-control" />
                            <div class="input-group-addon">km</div>
                        </div>
                        <c:if test="${not empty km_error}">
                            <p style="color:red"><c:out value="${km_error}" /></p>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-success">Complete</button>
                </form:form>
            </div>
    </jsp:attribute>
</my:layout>