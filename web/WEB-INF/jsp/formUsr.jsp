<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib  prefix="form" uri="http://www.springframework.org/tags/form" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out>   </title>
        <style>
            .error-message{
            color: red;
            font-size: 90%;
            font-weight: bold;           
            }               
        </style>        
    </head>    
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
    
        <h3><c:out value="${formTitle}"></c:out>  </h3>
    <form:form action="saveUsr" method="post" modelAttribute="usrForm" >
        <table>
            <tr>
                <td>Username</td>   
                <td><form:input path="username"></form:input>  </td>
            </tr>
            <tr>
                <td>Passworth</td>
                <td><form:input path="password"></form:input> </td>
            </tr>
            <tr>
                <td>&nbsp;</td>  
                <td><input type="submit" value="submit"><a href="${pageContext.request.contextPath}/userList">Cancel</a>  </td>
                
                
            </tr>
            
            
        </table>
        
        
        
    </form:form>
    
    
    </body>
</html>
