<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-15
  Time: 오후 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle"> 카테고리 수정 </p>
<form class="ui form" method="post" action="${pageContext.request.contextPath}/manage/category/update" style="width: 50%;">
  <input type="hidden" id="categoryID" name="categoryID" value="${category.categoryID}">
  <div class="field">
    <label>카테고리 이름</label>
    <input type="text" id="name" name="name" value="${category.name}">
  </div>
  <div class="field">
    <label>카테고리 코드</label>
    <input type="text" id="code" name="code" value="${category.code}">
  </div>
  <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>
</body>
</html>
