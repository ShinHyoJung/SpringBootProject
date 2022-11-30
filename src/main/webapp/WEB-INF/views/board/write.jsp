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
<p>글쓰기</p>
<form method="post" action="${pageContext.request.contextPath}/board/write">
  <textarea id="title" name="title"></textarea> <br>
  작성자: ${getWriteResponse.writer} <br>
  <textarea id="content" name="content"></textarea> <br>
  <input type="hidden" id="writer" name="writer" value="${getWriteResponse.writer}"> <br>
  <input type="hidden" id="idx" name="idx" value="${getWriteResponse.idx}"> <br>
  <input type="hidden" id="memberID" name="memberID" value="${getWriteResponse.memberID}">
  <button type="submit">등록</button>
</form>
</body>
</html>
