<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<div>
    <p class="title">Shop</p>
</div>
<div class="ui secondary menu" style="margin-top: 30px; margin-left: 1200px;">
    <div class="item">
        <i class="shopping cart icon"></i>
    </div>
<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/login/logout" class="item">로그아웃</a>
    <a href="${pageContext.request.contextPath}/member/info" class="item" id="info">내정보</a>
    <a href="${pageContext.request.contextPath}/manage/product/" class="item" id="manage">관리자 메뉴</a>
</sec:authorize>
<sec:authorize access="isAnonymous()">
       <!-- <a href="${pageContext.request.contextPath}/board/" class="item">게시판</a>-->
        <a href="${pageContext.request.contextPath}/member/signUp" class="item" id="signUp">회원가입</a>
        <a href="${pageContext.request.contextPath}/login/" class="item" id="login">로그인</a>
</sec:authorize>
    <a href="${pageContext.request.contextPath}/sell/" class="item">판매</a>
</div>
</body>
</html>
