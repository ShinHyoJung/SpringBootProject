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
<p class="subtitle"> 로그인 </p>
  <form class="ui form" method="post" action="${pageContext.request.contextPath}/member/login" style="width: 30%;">
    <div class="field">
        <label>아이디 </label>
            <input type="text" id="id" name="id">
    </div>
    <div class="field">
      <label> 비밀번호</label>
        <input type="password" id="password" name="password">
    </div>
      <button class="ui button" type="submit">로그인</button>
  </form>
    <button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/id/find'" style="margin-left: 270px; float:left;">아이디 찾기</button>
    <button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/password/find'" style="">비밀번호 찾기/변경</button>
</body>
</html>
