<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out>   </title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>        
        
        <h2>Admin page</h2>
        <h3>Welcome : <c:out value="${pageContext.request.userPrincipal.name}"></c:out>  </h3>
    <br>
    <p>
    <b><c:out value="${message}"></c:out> </b>
    </p>
    <jsp:include page="_footer.jsp"></jsp:include>
    </body>
</html>
