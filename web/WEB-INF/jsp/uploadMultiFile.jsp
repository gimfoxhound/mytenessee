<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out></title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
        
        <h3><c:out value="${MyTitle}"></c:out></h3>
    <!--MyUploadForm   -->
    <form:form modelAttribute="myUploadForm" method="post" action="" enctype="multipart/form-data">
        <p>
         Description :   
        </p>   
        <p>
            <form:input path="description" style="width:400px;"></form:input>   
        </p>
        <p>
          Files to upload :  
        </p>
        <p>
        <form:input path="fileDatas" type="file"></form:input><br/>   
        <form:input path="fileDatas" type="file"></form:input><br/>
        <form:input path="fileDatas" type="file"></form:input><br/>
        <form:input path="fileDatas" type="file"></form:input><br/>
        <form:input path="fileDatas" type="file"></form:input><br/>
        </p>
        
        <input type="submit" value="Upload">
    </form:form>
    
        
        
    </body>
</html>
