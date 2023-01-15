<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-15
  Time: 오후 5:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle"> 카테고리 추가 </p>
  <form class="ui form" method="post" action="${pageContext.request.contextPath}/manage/category/add" style="width: 50%;">
    <div class="field">
      <input type="text" id="name" name="name" placeholder="카테고리 이름">
    </div>
    <div class="field">
      <input type="text" id="code" name="code" placeholder="카테고리 코드">
    </div>
    <button class="ui button" type="submit"><i class="save icon"></i></button>
  </form>
</body>
</html>
