<%-- 
    Document   : new
    Created on : 22.12.2015, 18:08:31
    Author     : jakub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<my:layout title="Register new employee">
    <jsp:attribute name="body">
        <div class="row">
            <form:form method="post" action="${pageContext.request.contextPath}/employee/register" modelAttribute="employeeRegister" cssClass="col-md-5">
                <div class="form-group">
                    <form:label path="firstname">First name:</form:label>
                    <div>
                        <form:input path="firstname" cssClass="form-control" required="required" placeholder="Enter first name of employee" />
                    </div>
                    <c:if test="${not empty firstname_error}">
                        <p style="color: red"><c:out value="${firstname_error}" /></p>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <form:label path="lastname">Last name:</form:label>
                    <div>
                        <form:input path="lastname" cssClass="form-control" required="required" placeholder="Enter last name of employee" />
                    </div>
                    <c:if test="${not empty lastname_error}">
                        <p style="color: red"><c:out value="${lastname_error}" /></p>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <form:label path="email">Email:</form:label>
                    <div>
                        <form:input path="email" cssClass="form-control" required="required" placeholder="Enter email of employee" pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"/>
                    </div>
                    <c:if test="${not empty email_error}">
                        <p style="color: red"><c:out value="${email_error}" /></p>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <form:label path="phoneNumber">Phone:</form:label>
                    <div>
                        <form:input path="phoneNumber" cssClass="form-control" required="required" placeholder="Enter phone number of employee" />
                    </div>
                    <c:if test="${not empty phoneNumber_error}">
                        <p style="color: red"><c:out value="${phoneNumber_error}" /></p>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <form:label path="role">Role:</form:label>
                    <div>
                        <form:select path="role" items="${roles}" />
                    </div>
                    <c:if test="${not empty role_error}">
                        <p style="color: red"><c:out value="${role_error}" /></p>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <form:label path="credit">Credit:</form:label>
                    <div>
                        <form:input path="credit" cssClass="form-control" type="number" min="0" value="5" />
                    </div>
                    <c:if test="${not empty credit_error}">
                        <p style="color: red"><c:out value="${credit_error}" /></p>
                    </c:if>
                </div>
                    
                <div class="form-group">
                    <form:label path="category">Category:</form:label>
                    <div>
                        <form:select path="category" items="${categories}" />
                    </div>
                    <c:if test="${not empty category_error}">
                        <p style="color: red"><c:out value="${category_error}" /></p>
                    </c:if>
                </div>
                    
                <div style="height: 35px"></div>
                    
                <div class="form-group">
                    <label>Password:</label>
                    <div>
                        <p><input cssClass="form-control" type="password" name="password" required="required" placeholder="Enter password for user" /></p>
                    </div>
                    <c:if test="${not empty password_error}">
                        <p style="color: red"><c:out value="${password_error}" /></p>
                    </c:if>
                </div>
                    
                <div style="height: 35px"></div>
                
                <a href="${pageContext.request.contextPath}/employee/list" class="btn btn-default">Back</a>
                <button type="submit" class="btn btn-primary">Register!</button>
            </form:form>
        </div>
    </jsp:attribute>
</my:layout>
