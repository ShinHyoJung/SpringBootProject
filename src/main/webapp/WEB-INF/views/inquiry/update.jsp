<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 6:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form method="post" action="${pageContext.request.contextPath}/board/update">
  제목: <textarea id="title" name="title">${getUpdateResponse.title}</textarea> <br>
  작성자: ${getUpdateResponse.writer}<br>
  내용: <textarea id="content" name="content">${getUpdateResponse.content}</textarea> <br>
  <input type="hidden" id="boardID" name="boardID" value="${getUpdateResponse.boardID}"> <br>
  <button type="submit">등록</button>
</form>
</body>
</html>
