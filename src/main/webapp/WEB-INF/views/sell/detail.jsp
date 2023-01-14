<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-12-01
  Time: 오후 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/sell/update/${getDetailResponse.sell.sellID}'"><i class="alternate pencil icon"></i></button>
<button class="ui button" onclick="href='${pageContext.request.contextPath}/sell/delete/${getDetailResponse.sell.sellID}'"><i class="trash alternate icon"></i></button>
<br>
${getDetailResponse.sell.title} <br>
<div class="ui items">
    <div class="item">
        <a class="ui small sellImage">
            <img src="${pageContext.request.contextPath}/static/images/${orgImageName}"/>
        </a>
        <div class="content">
            <a class="header">${getDetailResponse.sell.name}</a>
            <div class="ui right labeled input">
                <input type="text" placeholder="수량">
                <div class="ui basic label">
                    개
                </div>
            </div>
            <div class="description">
                <p>가격 : ${getDetailResponse.sell.price} 원</p>
            </div>
        </div>
    </div>
</div>
<button class="ui button">구매</button> <button class="ui button"><i class="cart arrow down icon"></i></button>
<div class="ui divider">
<p class="subtitle"> 상세 정보 </p>
<img src="${pageContext.request.contextPath}/static/images/detail/${detailImageName}"/> <br>
${getDetailResponse.sell.content} <br>
작성날짜: <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getDetailResponse.sell.createDate}"/> <br>
수정날짜: <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getDetailResponse.sell.updateDate}"/> <br>
<button class="ui button" style="margin-top: 20px;" onclick="location.href='${pageContext.request.contextPath}/sell/'"><i class="list icon"></i></button>
</div>
</body>
</html>
