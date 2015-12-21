<%--
  User: Mário Kudolani
  Date: 16.12.2015
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<my:layout title="Drives">
    <jsp:attribute name="body">
        <a href="${pageContext.request.contextPath}/drive/new" role="button" class="btn btn-primary">New Drive</a>

        <div class="row" style="margin-top: 25px">
        </div>
    </jsp:attribute>
</my:layout>