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
            <c:if test="${showFirstStep == true}">
                <form:form method="post" action="${pageContext.request.contextPath}/drive/new/step2" modelAttribute="driveCreate" cssClass="col-md-5">
                    <div class="form-group">
                        <form:label cssClass="control-label" path="startDate">Start Date</form:label>
                        <div>
                            <form:input path="startDate" type="date" cssClass="form-control" placeholder="Start date"/>
                        </div>
                        <c:if test="${not empty startDate_error}">
                            <p style="color: red"><c:out value="${startDate_error}"/></p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:label cssClass="control-label" path="endDate">End Date</form:label>
                        <div>
                            <form:input path="endDate" type="date" cssClass="form-control" placeholder="End date"/>
                        </div>
                        <c:if test="${not empty endDate_error}">
                            <p style="color: red"><c:out value="${endDate_error}"/></p>
                        </c:if>
                    </div>

                    <a href="${pageContext.request.contextPath}/drive/list" class="btn btn-default">Back</a>
                    <button type="submit" class="btn btn-primary">Next Step</button>
                </form:form>
            </c:if>
            <c:if test="${showSecondStep}">
                <form:form method="post" action="${pageContext.request.contextPath}/drive/create" modelAttribute="driveCreate" cssClass="col-md-5">
                    <div class="form-group">
                        <form:label cssClass="control-label" path="startDate">Start Date</form:label>
                        <div>
                            <form:hidden path="startDate"/>
                            <fmt:formatDate value="${driveCreate.startDate}" pattern="yyyy-MM-dd" />
                        </div>
                        <c:if test="${not empty startDate_error}">
                            <p style="color: red"><c:out value="${startDate_error}"/></p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:label cssClass="control-label" path="endDate">End Date</form:label>
                        <div>
                            <form:hidden path="endDate"/>
                            <fmt:formatDate value="${driveCreate.endDate}" pattern="yyyy-MM-dd" />
                        </div>
                        <c:if test="${not empty endDate_error}">
                            <p style="color: red"><c:out value="${endDate_error}"/></p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <form:label path="vehicle" cssClass="control-label">Vehicle</form:label>
                        <div>
                            <form:select path="vehicle">
                                <form:option value="${null}">Select a vehicle</form:option>
                                <c:forEach items="${vehicles}" var="vehicle">
                                    <form:option value="${vehicle.id}">${vehicle.brand} ${vehicle.model} ${vehicle.yearOfProduction}, vin: ${vehicle.vin},
                                        mileage: ${vehicle.mileage}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </div>
                    <form:hidden path="employee.id" />
                    <form:hidden path="km" value="0"/>
                    <form:hidden path="driveStatus" />
                    <a href="${pageContext.request.contextPath}/drive/new/step1" class="btn btn-default">Back</a>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
            </c:if>
        </div>
    </jsp:attribute>
</my:layout>

