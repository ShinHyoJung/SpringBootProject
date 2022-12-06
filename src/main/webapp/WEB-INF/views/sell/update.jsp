<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-12-05
  Time: 오후 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form action="${pageContext.request.contextPath}/sell/update" method="post" enctype="multipart/form-data">
    <input type="text" id="title" name="title" value="${getUpdateResponse.title}"> <br>
    <input type="text" id="content" name="content" value="${getUpdateResponse.content}"> <br>
    <input type="text" id="productCode" name="productCode" value="${getUpdateResponse.productCode}"> <br>
    <input type="text" id="price" name="price" value="${getUpdateResponse.price}"> <br>
    <input type="hidden" id="sellID" name="sellID" value="${getUpdateResponse.sellID}"> <br>
    ${getUpdateResponse.image.storedName} ${getUpdateResponse.image.size}.kb <br>
    <input type="file" id="sellImageOrg" name="sellImageOrg" multiple="multiple"> <br>
    <button type="submit">등록</button>
</form>


</body>
</html>
