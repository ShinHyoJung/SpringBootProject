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
<style>
    .grid {
        display: grid;
        grid-template-columns: 300px 300px 300px;
        grid-template-rows: 300px 300px;
    }

    .item {

    }
</style>
<body>
<p>판매</p>
<div class="grid">
    <c:forEach items="${sellList}" var="sellList">
                <span class="item">
                    <input type="hidden" id="sellID" name="sellID" value="${sellList.sellID}"> <br>
                    <img src="${pageContext.request.contextPath}/static/images/thumbnail/${sellList.thumbnailImage}" onclick="location.href='${pageContext.request.contextPath}/sell/detail/${sellList.sellID}'"/> <br>
                        <a href="${pageContext.request.contextPath}/sell/detail/${sellList.sellID}" name="sellList.title">${sellList.title}</a> <br>
                    ${sellList.price}원 <br>
                </span>
    </c:forEach>
</div>
<a href="${pageContext.request.contextPath}/">뒤로가기</a> <br>
<a href="${pageContext.request.contextPath}/sell/register">판매 등록</a>
</body>
</html>
