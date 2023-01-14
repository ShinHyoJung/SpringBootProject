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
<p class="subtitle">제품 등록</p>
<form class="ui form" method="post" action="${pageContext.request.contextPath}/product/add" enctype="multipart/form-data" style="width: 50%;">
  <div class="field">
    <input type="text" id="name" name="name" placeholder="제품 이름">
  </div>
  <div class="field">
    <input type="text" id="fullQuantity" name="fullQuantity" placeholder="총 개수">
  </div>
  <div class="field">
    <select class="ui dropdown" name="category">
      <option value="">카테고리</option>
      <option value="notebook">노트북</option>
      <option value="monitor">모니터</option>
      <option value="etc">주변기기</option>
    </select>
  </div>
  <div class="field">
    <input type="text" id="info" name="info" placeholder="상품 정보">
  </div>
    <input type="file" id="sellImage" name="sellImage" multiple="multiple">
  <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>

<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/product/manage'">뒤로가기</button>
</body>
</html>
