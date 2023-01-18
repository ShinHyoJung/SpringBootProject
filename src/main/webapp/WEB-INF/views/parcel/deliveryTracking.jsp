
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-18
  Time: 오후 4:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<p class="subtitle">배송조회</p>

    <c:forEach items="${parcelList}" var="parcelList">
    <div>
    <div class="ui steps">
        <c:choose>
        <c:when test="${parcelList.status eq 0}">
            <div class="active step">
        </c:when>
        <c:otherwise>
            <div class="step">
        </c:otherwise>
        </c:choose>
            <i class="payment icon"></i>
            <div class="content">
                <div class="title">결제 완료</div>
                <div class="description">결제를 확인합니다.</div>
            </div>
        </div>
        <c:choose>
            <c:when test="${parcelList.status eq 1}">
                <div class="active step">
            </c:when>
            <c:otherwise>
                <div class="step">
            </c:otherwise>
        </c:choose>
            <i class="dolly flatbed icon"></i>
            <div class="content">
                <div class="title">배송준비</div>
                <div class="description">배송될 상품을 준비합니다.</div>
            </div>
        </div>
        <c:choose>
            <c:when test="${parcelList.status eq 2}">
                <div class="active step">
            </c:when>
            <c:otherwise>
                <div class="step">
            </c:otherwise>
        </c:choose>
            <i class="truck icon"></i>
            <div class="content">
                <div class="title">배송</div>
                <div class="description">배송을 시작합니다</div>
            </div>
        </div>
        <c:choose>
            <c:when test="${parcelList.status eq 3}">
                <div class="active step">
            </c:when>
            <c:otherwise>
                <div class="step">
            </c:otherwise>
        </c:choose>
            <i class="box icon"></i>
            <div class="content">
                <div class="title">배송 완료</div>
                <div class="description">배송이 완료되었습니다.</div>
            </div>
        </div>
    </div>
    <br>
    배송 상품: ${parcelList.name} ${parcelList.quantity} 개<br>
    주문번호 : ${parcelList.purchaseID} <br>
    배송주소 : ${parcelList.address} <br>
    <a href="https://tracker.delivery/#/kr.epost/${parcelList.waybillNumber}" target="_blank">배송조회</a>
    </div>
    </c:forEach>

</body>
</html>
