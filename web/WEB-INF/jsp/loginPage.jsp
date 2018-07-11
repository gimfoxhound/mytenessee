<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out>  </title>
    </head>
    <body>
    <jsp:include page="_menu.jsp"></jsp:include>
        
        <h1>Login</h1>
    <c:if test="${param.error==true}">
        <div style="color: red; font: bold;margin:10px 0px; ">
            Login Failed!!!<br/>   
            Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>        
    </c:if>
        <h3>Enter your Id and Passworth</h3>  
        <form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
  </form>
        
        
    </body>
</html>
