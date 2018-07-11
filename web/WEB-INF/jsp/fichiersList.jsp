
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${MyTitle}"></c:out></title>
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
                color: red;
                font-style: italic;
                margin-top: 30px;
            }                        
        </style>
        
        
    </head>
    <body>
        <jsp:include page="_menu.jsp"></jsp:include>
        <h3><c:out value="${MyTitle}"></c:out></h3>
    
    <p>
        
    <a href="${pageContext.request.contextPath}/uploadOneFile">Upload fichier</a>    
    </p>
    <br/>
    
    <c:set var="chemin" value="${applicationScope.monCheminUpload}"/>
      <c:set  var="imgvalue" value="${fileinfos.remarque}"></c:set>
      <c:set var="alpha" value='\\'></c:set>
      <c:set var="cheminfinal" value="${chemin.concat(alpha)}"></c:set>
      <c:set var="imgfinal" value="${cheminfinal.concat(imgvalue)}"></c:set>
    
    <table border="1">

 
  <tr>
      <th>Numero de fichier</th>
      <th>Nom</th>    
      <th>remarque</th> 
      <th>taille</th> 
      <th>photo</th> 
      <th>description</th> 
      <th>Edit</th>
      <th>Delete</th>
  </tr>
                      
    <c:forEach items="${fichierInfos}" var="fileinfos">
  <tr>
      <td>${fileinfos.numfile}</td> 
      <td>${fileinfos.namefile}</td>   
      
      
           
      <td>  <a href="<c:url value='/images/${fileinfos.remarque}'></c:url>" target="_blank" >  <img width="102" height="102" alt="images" src="<c:url value='/images/${fileinfos.remarque}'></c:url>" /> </a></td> 
      
      <td>${fileinfos.taille}</td> 
      <td>${fileinfos.photo}</td> 
      <td>${fileinfos.description} ... <c:out value='/Img/${fileinfos.remarque}'/> </td>
      <td><a href="editFichier?numfile=${fileinfos.numfile}">Edit</a>  </td>
      <td><a href="deleteFichier?numfile=${fileinfos.numfile}">Delete</a></td>
  </tr>    
  </c:forEach>      
    </table>
      <hr>
      <hr>
      <p>
          <a href="${pageContext.request.contextPath}/uploadOneFile"> Upload one file.</a>    
      </p>
      <p>
          <a href="${pageContext.request.contextPath}/uploadMultiFile">Upload files</a>   
      </p>
      <p>
          <a href="${pageContext.request.contextPath}/welcome"><b>Home</b></a>  
      </p>
      <jsp:include page="_footer.jsp"></jsp:include>
    </body>
</html>
