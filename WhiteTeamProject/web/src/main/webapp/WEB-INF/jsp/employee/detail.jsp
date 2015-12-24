<%-- 
    Document   : detail
    Created on : Dec 21, 2015, 11:32:04 PM
    Author     : jakub
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Employee: '${employee.email}'">
    <jsp:attribute name="body">
        
        <div class="row" style="margin-bottom: 50px">
            <div style="float: right" class="col-md-4">
                <my:imagelink src="http://www.bigbangyayinlari.com/img/yazar/unknown_man.jpg" />
            </div>
            
            <div class="col-md-5">
                <table class="table">
                    <tr>
                        <th>ID:</th>
                        <td><c:out value="${employee.id}" /></td>
                    </tr>
                    <tr>
                        <th>First name:</th>
                        <td><c:out value="${employee.firstname}" /></td>
                    </tr>
                    <tr>
                        <th>Last name:</th>
                        <td><c:out value="${employee.lastname}" /></td>
                    </tr>
                    <tr>
                        <th>Email (login name):</th>
                        <td><c:out value="${employee.email}" /></td>
                    </tr>
                    <tr>
                        <th>Phone number:</th>
                        <td><c:out value="${employee.phoneNumber}" /></td>
                    </tr>
                    <tr>
                        <th>User rights:</th>
                        <td><c:out value="${employee.role}" /></td>
                    </tr>
                    <tr>
                        <th>Current credit:</th>
                        <td>
                            <c:out value="${employee.credit}" />
                            <form method="post" action="${pageContext.request.contextPath}/employee/update/${employee.id}">
                                <button type="submit" class="btn-default">Add credit:</button>
                                
                                <input name="newCredit" value="1" type="number" required="required" min="1" />
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <th>Category:</th>
                        <td><c:out value="${employee.category}" /></td>
                    </tr>
                </table>                
                
                <form method="post" action="${pageContext.request.contextPath}/employee/delete/${employee.id}" 
                      onsubmit="return confirm('Do you really want to delete this employee?');">
                    <a href="${pageContext.request.contextPath}/employee/list" class="btn btn-default">Back</a>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
                    </sec:authorize>
                </form>
            </div>
        </div>
    </jsp:attribute>
</my:layout>