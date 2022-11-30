<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 5:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
제목: ${getReadResponse.title}
<br>
작성자: ${getReadResponse.writer}
<br>
내용: ${getReadResponse.content}
<br>
작성날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getReadResponse.createDate}"/>
<br>
수정날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getReadResponse.updateDate}"/>
<br>
<a href="${pageContext.request.contextPath}/board/update/${getReadResponse.boardID}">수정</a>
<a href="${pageContext.request.contextPath}/board/">뒤로가기</a>

</body>
</html>
