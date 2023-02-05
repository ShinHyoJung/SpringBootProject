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
<p class="subtitle">문의글 수정</p>
<form class="ui form" style="width: 30%;" method="post" action="${pageContext.request.contextPath}/inquiry/update">
  <div class="ui field">
    <label>제목</label>
    <input type="text" id="title" name="title" value="${getUpdateResponse.title}">
  </div>
  <div class="ui field">
    <label>작성자</label> ${getUpdateResponse.writer}
  </div>
  <div class="ui field">
    <label>내용</label>
    <textarea id="content" name="content">${getUpdateResponse.content}</textarea>
  </div>
  <input type="hidden" id="boardID" name="inquiryID" value="${getUpdateResponse.inquiryID}"> <br>
  <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>
</body>
</html>
