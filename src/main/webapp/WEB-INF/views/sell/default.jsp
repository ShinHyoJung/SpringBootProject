<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-30
  Time: 오후 3:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<p>판매</p>
<table>
    <c:forEach items="${sellList}" var="sellList">
        <tr>
            <td>${sellList.sellID}</td>
        </tr>
        <tr>
            <td><img src="${pageContext.request.contextPath}/static/images/thumbnail/${sellList.thumbnailImage}"></td>
        </tr>
        <tr>
            <td><a href="${pageContext.request.contextPath}/sell/detail/${sellList.sellID}" name="sellList.title"/>${sellList.title}</td>
        </tr>
        <tr>
            <td>${sellList.price}</td>
        </tr>
        <tr>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sellList.createDate}"/></td>
        </tr>
        <tr>
            <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${sellList.updateDate}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/sell/register">판매 등록</a>
</body>
</html>
