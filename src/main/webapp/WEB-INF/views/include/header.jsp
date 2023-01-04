<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 3:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div>
    <p class="title">쇼핑몰</p>
</div>
<div>
    <ul class="menu">
    <c:choose>
        <c:when test="${not empty sessionScope.loggedIn}">
            <li><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></li>
            <li><a href="${pageContext.request.contextPath}/member/info">내정보</a></li>
            <li><a href="${pageContext.request.contextPath}/board/1">게시판</a></li>
            <li><a href="${pageContext.request.contextPath}/sell/1">판매</a></li>
            <li><a href="${pageContext.request.contextPath}/product/manage">재고관리</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${pageContext.request.contextPath}/member/signUp">회원가입</a></li>
            <li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
        </c:otherwise>
    </c:choose>
    </ul>
</div>
</body>
</html>
