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
제품 등록
<form class="ui form" method="post" action="${pageContext.request.contextPath}/product/add" enctype="multipart/form-data">
  <div class="field">
    <input type="text" id="name" name="name" placeholder="제품 이름">
  </div>
  <div class="field">
    <input type="text" id="fullQuantity" name="fullQuantity" placeholder="총 개수">
  </div>
  <div class="field">
    <input type="text" id="info" name="info" placeholder="상품 정보">
  </div>
    <input type="file" id="image" name="image" multiple="multiple">
  <button class="ui button" type="submit">등록</button>
</form>

<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/product/manage'">뒤로가기</button>
</body>
</html>
