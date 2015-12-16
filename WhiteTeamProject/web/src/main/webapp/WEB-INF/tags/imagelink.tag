<%-- 
    Document   : imagelink
    Created on : Dec 16, 2015, 8:07:42 PM
    Author     : Marek
--%>

<%@tag description="Creates clickable image." pageEncoding="UTF-8"%>

<%@attribute name="src" required="true"%>
<%@attribute name="href" required="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value='${src}' var="srcUrl" />
<c:url value="${not empty href ? href : src}" var="hrefUrl" />
<a href="${hrefUrl}" class="thumbnail"><img src="${srcUrl}" class="img-responsive" class="col-md-3" /></a>