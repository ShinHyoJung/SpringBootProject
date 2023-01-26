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
<table class="ui basic table" style="width: 50%;">
    <tr>
    <td>제목: ${getReadResponse.title}</td>
    </tr>
    <tr>
    <td>작성자: ${getReadResponse.writer}</td>
    </tr>
    <tr style="height:500px;">
        <td >${getReadResponse.content}</td>
    </tr>
    <tr>
        <td>작성날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getReadResponse.createDate}"/></td>
    </tr>
    <tr>
        <td>수정날짜: <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${getReadResponse.updateDate}"/></td>
    </tr>
</table>
<br>
<a class="ui button" href="${pageContext.request.contextPath}/inquiry/update/${getReadResponse.inquiryID}"><i class="alternate pencil icon"></i></a>
<a class="ui button" href="${pageContext.request.contextPath}/inquiry/"><i class="list icon"></i></a>

</body>
</html>
