<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-17
  Time: 오후 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form method="post" action="${pageContext.request.contextPath}/member/login" id="loginForm">
  아이디: <input type="text" id="id" name="id"> <br>
  비밀번호: <input type="password" id="password" name="password"> <br>
  <button type="submit">로그인</button>
</form>
</body>
</html>
