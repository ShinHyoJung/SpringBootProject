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
<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getUpdateResponse.createDate}"/> <br>
<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getUpdateResponse.updateDate}"/>
<input type="file" id="sellImageOrg" name="sellImageOrg" value="${getUpdateResponse.storedName}" multiple="multiple">
</form>
</body>
</html>
