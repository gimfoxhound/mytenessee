<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out></title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
    <h1><c:out value="${message}"></c:out></h1>
    </body>
</html>
