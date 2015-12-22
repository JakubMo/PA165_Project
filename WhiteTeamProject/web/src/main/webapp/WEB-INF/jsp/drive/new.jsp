<%--
  User: Mário Kudolani
  Date: 18.12.2015
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
                <form:form method="post" action="${pageContext.request.contextPath}/drive/create" modelAttribute="driveCreate" cssClass="col-md-5">
                    <div class="form-group">
                        <form:label path="vehicle">Vehicle</form:label>
                        <div>
                            <form:select path="vehicle" cssClass="form-control" items="${vehicles}"/>
                        </div>
                        <c:if test="${not empty vehicle_error}">
                            <p style="color:red"><c:out value="${vehicle_error}" /></p>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <form:label path="startDate">Start date</form:label>
                        <div>
                            <form:input path="startDate" cssClass="form-control" type="date" placeholder="Select start date"/>
                        </div>
                        <c:if test="${not empty startDate_error}">
                            <p style="color:red"><c:out value="${startDate_error}" /></p>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <form:label path="endDate">End date</form:label>
                        <div>
                            <form:input path="endDate" cssClass="form-control" type="date" placeholder="Select end date"/>
                        </div>
                        <c:if test="${not empty endDate_error}">
                            <p style="color:red"><c:out value="${endDate_error}" /></p>
                        </c:if>
                    </div>

                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
            </div>
    </jsp:attribute>
</my:layout>

