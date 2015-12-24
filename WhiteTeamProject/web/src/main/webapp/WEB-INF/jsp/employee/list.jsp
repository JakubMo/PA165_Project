<%-- 
    Document   : list
    Created on : 22.12.2015, 16:33:39
    Author     : jakub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Employees">
    <jsp:attribute name="body">
        <sec:authorize access="hasAuthority('ADMIN')">
            <a href="${pageContext.request.contextPath}/employee/new" role="button" class="btn btn-primary">Create new employee</a>
        </sec:authorize>

        <div class="row" style="margin-top: 25px">

            <div class="caption">
                <table class="table">
                    <tr>
                        <th>#ID</th>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${employees}" var="employee">

                        <tr>
                            <td><c:out value="${employee.id}" /></td>
                            <td><c:out value="${employee.firstname}" /></td>
                            <td><c:out value="${employee.lastname}" /></td>
                            <td><c:out value="${employee.email}" /></td>
                            <td><c:out value="${employee.phoneNumber}" /></td>
                            <td><c:out value="${employee.role}" /></td>
                            
                            
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/employee/delete/${employee.id}" 
                              onsubmit="return confirm('Do you really want to delete this employee?');">
                                    <a href="${pageContext.request.contextPath}/employee/detail/${employee.id}" class="btn btn-primary">Details</a>
                                    <sec:authorize access="hasAuthority('ADMIN')">
                                        <button style="float: right" type="submit" class="btn btn-danger">Delete</button>

                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                                    </sec:authorize>
                                </form>
                            </td>
                            
                        </tr>

                    </c:forEach>
                </table>
            </div>
        </div>
    </jsp:attribute>
</my:layout>