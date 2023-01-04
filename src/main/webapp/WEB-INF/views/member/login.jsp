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
<div class="grid-container">
  <form method="post" action="${pageContext.request.contextPath}/member/login" id="loginForm">
    <div class="grid-x grid-padding-x">
        <div class="small-12 cell">
          <label>아이디
            <input type="text" id="id" name="id">
          </label>
        </div>
        <div class="small-12 cell">
          <label> 비밀번호
            <input type="password" id="password" name="password">
          </label>
        </div>
      <button class="primary button" type="submit">로그인</button>
    </div>
  </form>
</div>
</body>
</html>
