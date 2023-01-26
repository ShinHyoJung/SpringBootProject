<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle">글쓰기</p>
<form class="ui form" method="post" action="${pageContext.request.contextPath}/inquiry/write" style="width: 50%;">
  <div class="field">
    <label>제목</label>
    <input type="text" id="title" name="title"/>
  </div>
  <div class="field">
    <label>작성자: ${getWriteResponse.writer}</label>
  </div>
  <div class="field">
    <label>내용</label>
    <textarea id="content" name="content"></textarea>
  </div>
  <input type="hidden" id="writer" name="writer" value="${getWriteResponse.writer}"> <br>
  <input type="hidden" id="idx" name="idx" value="${getWriteResponse.idx}"> <br>
  <input type="hidden" id="loginID" name="loginID" value="${getWriteResponse.loginID}">
  <button class="ui button" type="submit">등록</button>
</form>
</body>
</html>
