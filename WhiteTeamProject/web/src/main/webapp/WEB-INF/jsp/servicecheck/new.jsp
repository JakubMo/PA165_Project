<%--
  Created by IntelliJ IDEA.
  User: tborcin
  Date: 12/17/15
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Create service check">
    <jsp:attribute name="body">
        <div class="row">
					<form:form method="post" action="${pageContext.request.contextPath}/servicecheck/create" modelAttribute="servicecheckCreate" cssClass="col-md-5">

						<div class="form-group">
							<form:label path="status">State:</form:label>
							<div>
								<form:select path="status" items="${statuses}" />
							</div>
							<c:if test="${not empty status_error}">
								<p style="color: red"><c:out value="${status_error}" /></p>
							</c:if>
						</div>

						<div class="form-group">
							<form:label path="serviceCheckDate">Date:</form:label>
							<div>
								<form:input path="serviceCheckDate" cssClass="form-control" placeholder="Enter date" type="date" />
							</div>
							<c:if test="${not empty serviceCheckDate_error}">
								<p style="color: red"><c:out value="${serviceCheckDate_error}" /></p>
							</c:if>
						</div>

						<div class="form-group">
							<form:label path="vehicle">Vehicle VIN:</form:label>
							<div>
								<form:select path="vehicle" items="${vehicles}" />
							</div>
							<c:if test="${not empty vehicle_error}">
								<p style="color: red"><c:out value="${vehicle_error}" /></p>
							</c:if>
						</div>

						<div class="form-group">
							<form:label path="serviceEmployee">Employee:</form:label>
							<div>
								<form:input path="serviceEmployee" cssClass="form-control" placeholder="Enter employee name" />
							</div>
							<c:if test="${not empty serviceEmployee_error}">
								<p style="color: red"><c:out value="${serviceEmployee_error}" /></p>
							</c:if>
						</div>

						<button type="submit" class="btn btn-default">Submit</button>
					</form:form>
				</div>
    </jsp:attribute>
</my:layout>