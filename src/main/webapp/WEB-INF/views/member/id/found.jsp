<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="field">
    로그인 아이디는 ${loginID} 입니다.
</div>
<div class="field">
    <button class="ui button" onclick="location.href='${pageContext.request.contextPath}/sell/'">홈으로 가기</button>
</div>
</body>
</html>
