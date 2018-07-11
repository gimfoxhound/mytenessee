<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out></title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
        <h3><c:out value="${MyTile}"></c:out></h3>
        <p>
            Description : ${description}"
        </p>
        <p>             <!--uploadedFiles  -->
        <c:forEach items="${uploadedFiles}" var="file">
            <!--c:out value="">c:out>  -->          
            ${file}
        </c:forEach>   
        </p>
        
    </body>
</html>
