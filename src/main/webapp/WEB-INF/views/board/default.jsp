<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<p>게시판</p>
<c:if test="${not empty getDefaultResponse.boardList}">
<table class="hover">
    <thead>
        <tr>
            <th>글 번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성 날짜</th>
            <th>수정 날짜</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${getDefaultResponse.boardList}" var="boardList">
        <tr>
            <td>${boardList.boardID}</td>
            <td><a href="${pageContext.request.contextPath}/board/read/${boardList.boardID}" name="boardList.title"/>${boardList.title}</td>
            <td>${boardList.writer}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardList.createDate}"/></td>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardList.updateDate}"/></td>
        </tr>
    </tbody>
    </c:forEach>
</table>
</c:if>
<br>
<ul class="pagination">
    <c:if test="${getDefaultResponse.paging.prev eq true}"> < </c:if>
    <c:forEach begin="${getDefaultResponse.paging.startPage}" end="${getDefaultResponse.paging.endPage}" var="currentPage">
        <li><a href="${pageContext.request.contextPath}/board/${currentPage}">${currentPage}</a></li>
    </c:forEach>
    <c:if test="${getDefaultResponse.paging.next eq true}"> > </c:if>
</ul>
<br>
<a href="${pageContext.request.contextPath}/board/write">글쓰기</a> <br>
<a href="${pageContext.request.contextPath}/">뒤로가기</a>
</body>
</html>
