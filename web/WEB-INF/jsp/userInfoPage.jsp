<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out>  </title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
        
        
        <h2>User Info Page</h2>
        <h3> Welcome : ${pageContext.request.userPrincipal.name} </h3>
    <p>
        <c:out value="${message}"></c:out>
    </p>
    
    </body>
</html>
