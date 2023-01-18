<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-18
  Time: 오후 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle">배송조회</p>

<c:forEach items="${parcelList}" var="parcelList">
<div class="ui steps">
    <div class="active step">
        <i class="payment icon"></i>
        <div class="content">
            <div class="title">결제 완료</div>
            <div class="description">결제를 확인합니다.</div>
        </div>
    </div>
    <div class="step">
        <i class="dolly flatbed icon"></i>
        <div class="content">
            <div class="title">배송준비</div>
            <div class="description">배송될 상품을 준비합니다.</div>
        </div>
    </div>
    <div class="step">
        <i class="truck icon"></i>
        <div class="content">
            <div class="title">배송</div>
            <div class="description">배송을 시작합니다</div>
        </div>
    </div>
    <div class="step">
        <i class="box icon"></i>
        <div class="content">
            <div class="title">배송 완료</div>
            <div class="description">배송이 완료되었습니다.</div>
        </div>
    </div>
</div>
${parcelList.name} <br>
${parcelList.address} <br>
${parcelList.status} <br>
</c:forEach>
</body>
</html>
