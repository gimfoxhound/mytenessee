<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des utilisateurs</title>
        <style>
            table{
                margin-top: 10px;
                border: solid black 1px;                
            }    
            table td{
                padding: 5px;
            }
            .message{
                font-size: 90%;
                color: blue;
                font-style: italic;
                margin-top: 30px;
            }            
        </style>
    </head>
    <body>
        <jsp:include page="_menu.jsp"></jsp:include>
        <p>
            <a href="${pageContext.request.contextPath}/registerUser">Register User</a>                
        </p>
        <br/>
        <table border="1">
            <tr>
                <th>Username</th>    
                <th>Passworth</th>
            </tr>  
            <c:forEach items="${userInfos}" var="usr">
                <tr>
                <td>${usr.username} </td>   
                <td>${usr.password}</td>
                <td><a href="editUser?username=${usr.username}">Edit</a> </td>
                <td><a href="deleteUser?username=${usr.username}">Delete</a> </td>
                </tr>
            </c:forEach>            
        </table>
        
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>
        
    </body>
</html>
