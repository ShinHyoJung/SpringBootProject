<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p>회원 가입</p>

<form action="${pageContext.request.contextPath}/member/signUp" method="post" id="signUpForm">
   아이디:  <input type="text" id="memberID" name="memberID" placeholder="아이디"> <br>
   비밀번호:  <input type="password" id="password" name="password" placeholder="비밀번호">  <br>
   이름: <input type="text" id="name" name="name" placeholder="이름">  <br>
   생년월일:  <input type="text" id="birth" name="birth" placeholder="생년월일">  <br>
   휴대폰번호:  <input type="text" id="mobile" name="mobile" placeholder="휴대폰번호">  <br>
   이메일: <input type="text" id="mail" name="mail" placeholder="이메일">  <br>
   주소: <input type="text" id="address" name="address" placeholder="주소">  <br>
   <button type="submit">확인</button>
</form>
</body>
</html>
