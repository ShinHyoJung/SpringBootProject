<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 2:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
메인
<c:choose>
    <c:when test="${not empty sessionScope.loggedIn}">
        <a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
        <a href="${pageContext.request.contextPath}/member/info">내정보</a>
        <a href="${pageContext.request.contextPath}/board/1">게시판</a>
        <a href="${pageContext.request.contextPath}/sell/1">판매</a>
    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/member/signUp">회원가입</a>
        <a href="${pageContext.request.contextPath}/member/login">로그인</a>
    </c:otherwise>
</c:choose>

</body>
</html>
