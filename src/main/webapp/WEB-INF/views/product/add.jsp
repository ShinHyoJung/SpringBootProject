<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-04
  Time: 오후 6:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form method="post" action="${pageContext.request.contextPath}/product/add" enctype="multipart/form-data">
  <input type="text" id="name" name="name" placeholder="제품 이름"> <br>
  <input type="text" id="code" name="code" placeholder="제품 코드"> <br>
  <input type="text" id="fullQuantity" name="fullQuantity" placeholder="총 개수">
  <input type="file" id="image" name="image" multiple="multiple"> <br>
  <button type="submit">등록</button>
</form>
</body>
</html>
