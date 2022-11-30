
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<p>게시판</p>
<table>
    <th>
        <tr>
            <td>글 번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>작성 날짜</td>
            <td>수정 날짜</td>
        </tr>
    </th>
    <c:forEach items="${boardList}" var="boardList">
        <tr>
            <td>${boardList.boardID}</td>
            <td><a href="${pageContext.request.contextPath}/board/read/${boardList.boardID}" name="boardList.title">${boardList.title}</td>
            <td>${boardList.writer}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardList.createDate}"/></td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardList.updateDate}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/board/write">글쓰기</a> <br>
<a href="${pageContext.request.contextPath}/">뒤로가기</a>
</body>
</html>
