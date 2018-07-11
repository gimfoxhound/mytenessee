
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page  session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out>  </title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
    <h3 style="color: red;font: bold"><c:out value="${message}"></c:out>  </h3>
    </body>
</html>
