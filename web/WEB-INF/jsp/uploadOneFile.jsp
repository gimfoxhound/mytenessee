<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">               
        <title><c:out value="${MyTitle}"></c:out> </title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
        
        <h3>Upload one file</h3>
        
        
        <!--MyUpload form -->
        
    <form:form modelAttribute="myUploadForm" method="POST" action="" enctype="multipart/form-data">
        <p> 
        Description :        
        </p>
        <p>
            <form:input path="description" style="width:300px;"></form:input>   
        </p>
        <p>
        File to upload    
        </p>
        <p>
        <form:input path="fileDatas" type="file"></form:input>    
        </p>
        <p>
            <input type="submit" value="Upload"/>   
        </p>        
    </form:form>    
    </body>
</html>
