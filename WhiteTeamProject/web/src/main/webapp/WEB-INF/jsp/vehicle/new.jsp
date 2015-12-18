<%-- 
    Document   : new
    Created on : Dec 15, 2015, 9:18:28 PM
    Author     : Marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Create vehicle">
    <jsp:attribute name="body">
        <div class="row">
            <form:form method="post" action="${pageContext.request.contextPath}/vehicle/create" modelAttribute="vehicleCreate" cssClass="col-md-5">
                <div class="form-group">
                    <form:label path="vin">VIN:</form:label>
                    <div>
                        <form:input path="vin" cssClass="form-control" placeholder="Enter vehicle's VIN" />
                    </div>
                    <c:if test="${not empty vin_error}">
                        <p style="color: red"><c:out value="${vin_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="brand">Brand:</form:label>
                    <div>
                        <form:input path="brand" cssClass="form-control" placeholder="Enter brand" />
                    </div>
                    <c:if test="${not empty brand_error}">
                        <p style="color: red"><c:out value="${brand_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="model">Model:</form:label>
                    <div>
                        <form:input path="model" cssClass="form-control" placeholder="Enter model" />
                    </div>
                    <c:if test="${not empty model_error}">
                        <p style="color: red"><c:out value="${model_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="type">Type:</form:label>
                    <div>
                        <form:input path="type" cssClass="form-control" placeholder="Enter type" />
                    </div>
                    <c:if test="${not empty type_error}">
                        <p style="color: red"><c:out value="${type_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="engineType">Engine type:</form:label>
                    <div>
                        <form:input path="engineType" cssClass="form-control" placeholder="Enter engine type" />
                    </div>
                    <c:if test="${not empty engineType_error}">
                        <p style="color: red"><c:out value="${engineType_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="yearOfProduction">Year of production:</form:label>
                    <div>
                        <form:input path="yearOfProduction" cssClass="form-control" type="number" value="2000" placeholder="Enter year of production" />
                    </div>
                    <c:if test="${not empty yearOfProduction_error}">
                        <p style="color: red"><c:out value="${yearOfProduction_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="mileage">Mileage:</form:label>
                    <div>
                        <form:input path="mileage" cssClass="form-control" type="number" value="0" placeholder="Enter mileage" />
                    </div>
                    <c:if test="${not empty mileage_error}">
                        <p style="color: red"><c:out value="${mileage_error}" /></p>
                    </c:if>
                </div>

                <div class="form-group">
                    <form:label path="maxMileage">Maximum mileage:</form:label>
                    <div>
                        <form:input path="maxMileage" cssClass="form-control" type="number" value="100000" placeholder="Enter maximum mileage" />
                    </div>
                    <c:if test="${not empty maxMileage_error}">
                        <p style="color: red"><c:out value="${maxMileage_error}" /></p>
                    </c:if>
                </div>
                
                <div class="form-group">
                    <form:label path="serviceCheckInterval">Service check interval:</form:label>
                    <div>
                        <form:input path="serviceCheckInterval" cssClass="form-control" type="number" value="1" placeholder="Enter service check interval" />
                    </div>
                    <c:if test="${not empty serviceCheckInterval_error}">
                        <p style="color: red"><c:out value="${serviceCheckInterval_error}" /></p>
                    </c:if>
                </div>

                <a href="${pageContext.request.contextPath}/vehicle/list" class="btn btn-default">Back</a>
                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
    </jsp:attribute>
</my:layout>
