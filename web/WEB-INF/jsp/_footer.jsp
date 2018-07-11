<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
    <a href="${pageContext.request.contextPath}/welcome">Home</a>   
      | &nbsp;
      <a href="${pageContext.request.contextPath}/userInfo">User Info</a>   
    | &nbsp;
    <a href="${pageContext.request.contextPath}/admin">Admin</a>
    <c:if test="${pageContext.request.userPrincipal.name!=null}">
    | &nbsp;
    <a href="${pageContext.request.contextPath}/logout">Logout</a>             
    </c:if>
    | &nbsp;
    <a href="${pageContext.request.contextPath}/mescandidats">Application List</a> 
    | &nbsp;
    <a href="${pageContext.request.contextPath}/registerUser">Register</a>
    | &nbsp;
    <a href="${pageContext.request.contextPath}/uploadOneFile">Upload one File</a>
    | &nbsp;
    <a href="${pageContext.request.contextPath}/uploadMultiFile">Upload Multi File</a>
    | &nbsp;
    <a href="${pageContext.request.contextPath}/fichierList">Liste des fichiers</a>
    | &nbsp;
</div>
    </p>