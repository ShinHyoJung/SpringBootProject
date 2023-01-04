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
        grid-template-rows: 250px 250px;
    }

    .item {

    }
</style>
<body>
<p>판매</p>
<div class="grid-container">
    <div class="grid-x grid-margin-x small-up-2 medium-up-6">
    <c:forEach items="${getDefaultResponse.sellList}" var="sellList">
        <div class="cell">
            <div class="card">
                <input type="hidden" id="sellID" name="sellID" value="${sellList.sellID}"> <br>
                <img src="${pageContext.request.contextPath}/static/images/thumbnail/${sellList.thumbnailImageName}" onclick="location.href='${pageContext.request.contextPath}/sell/detail/${sellList.sellID}'"/>
                <div class="card-section">
                    <a href="${pageContext.request.contextPath}/sell/detail/${sellList.sellID}" name="sellList.title">${sellList.title}</a>
                    <p>${sellList.price}원 </p>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>
<ul class="paging">
    <c:if test="${getDefaultResponse.paging.prev eq true}"> < </c:if>
    <c:forEach begin="${getDefaultResponse.paging.startPage}" end="${getDefaultResponse.paging.endPage}" var="currentPage">
        <a href="${pageContext.request.contextPath}/board/${currentPage}">${currentPage}</a>
    </c:forEach>
    <c:if test="${getDefaultResponse.paging.next eq true}"> > </c:if>
</ul>
<a href="${pageContext.request.contextPath}/">뒤로가기</a> <br>
<a href="${pageContext.request.contextPath}/sell/register">판매 등록</a>
</body>
</html>
