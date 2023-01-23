
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
<body onload="printList()">
<p class="subtitle">배송조회</p>
<div class="ui relaxed divided list" id="list" style="width: 55%;">
 <!--   <c:forEach items="${parcelList}" var="parcelList">
            <div class="item">
            <div>
                주문번호 : ${parcelList.purchaseID} <br>
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
                 <br>
                우편번호 : ${parcelList.zipCode} <br>
                배송주소 : ${parcelList.address} ${parcelList.detailAddress}
            <a class="ui button" href="https://tracker.delivery/#/kr.epost/${parcelList.waybillNumber}" target="_blank">배송조회</a>
            </div>
        </div>
    </c:forEach>-->
</div>
<div id="pagination" class="ui pagination menu" style="margin-top: 20px; margin-left: 200px;">
</div>
<script>
    function printList(currentPage) {
        if(!currentPage) {
            currentPage = 1;
        }

        let postObj = {
            'currentPage':currentPage
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/parcel/delivery-tracking/list',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                console.log(pageResponse);
                let pageHTML = '';
                let listHTML = '';

                if (pageResponse.paging.prev) {
                    pageHTML += '<a class="item"> < </a>';
                }

                for (let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                    if (currentPage == i) {
                        pageHTML += '<a class="active item" onclick="printList(' + i + ')">' + i + '</a>';
                    } else {
                        pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                    }
                }

                if (pageResponse.paging.next) {
                    pageHTML += '<a class="item"> > </a>';
                }

                $.each(pageResponse.parcelList, function(i, parcelList) {
                   let deliveryTrackingSrc = 'https://tracker.delivery/#/kr.epost/' + parcelList.waybillNumber;
                   listHTML += '<div class="item">';
                   listHTML += '<div>';
                   listHTML += '<p style="font-weight: bold">주문번호 ' + parcelList.purchaseID + '</p>';
                   listHTML += '<div class="ui steps">';
                   if(parcelList.status == 0) {
                       listHTML += '<div class="active step">';
                   } else {
                       listHTML += '<div class="step">';
                   }
                   listHTML += '<i class="payment icon"></i>';
                   listHTML += '<div class="content">';
                   listHTML += '<div class="title">결제 완료</div>';
                   listHTML += '<div class="description">결제를 확인합니다.</div>';
                   listHTML += '</div>';
                   listHTML += '</div>';
                   if(parcelList.status == 1) {
                       listHTML += '<div class="active step">';
                   } else {
                       listHTML += '<div class="step">';
                   }
                   listHTML += '<i class="dolly flatbed icon"></i>';
                   listHTML += '<div class="content">';
                   listHTML += '<div class="title">배송준비</div>';
                   listHTML += '<div class="description">배송될 상품을 준비합니다.</div>';
                   listHTML += '</div>';
                   listHTML += '</div>';
                   if(parcelList.status == 2) {
                       listHTML += '<div class="active step">';
                   } else {
                       listHTML += '<div class="step">';
                   }
                   listHTML += '<i class="truck icon"></i>';
                   listHTML += '<div class="content">';
                   listHTML += '<div class="title">배송</div>';
                   listHTML += '<div class="description">배송중입니다.</div>';
                   listHTML += '</div>';
                   listHTML += '</div>';
                   if(parcelList.status == 3) {
                       listHTML += '<div class="active step">'
                   } else {
                       listHTML += '<div class="step">';
                   }
                   listHTML += '<i class="box icon"></i>';
                   listHTML += '<div class="content">';
                   listHTML += '<div class="title">배송 완료</div>';
                   listHTML += '<div class="description">배송이 완료되었습니다.</div>';
                   listHTML += '</div>';
                   listHTML += '</div>';
                   listHTML += '</div>';
                   listHTML += '<br>배송 상품 :' + parcelList.name + ' ' + parcelList.quantity + '<br><br>';
                   listHTML += '우편번호 : ' + parcelList.zipCode + '<br>';
                   listHTML += '배송주소 : ' + parcelList.address + ' ' + parcelList.detailAddress+ '<br>';
                   listHTML += '운송장번호: <a href="' + deliveryTrackingSrc + '" target="_blank" style="margin-left: 30px;">' + parcelList.waybillNumber + '</a>';
                   listHTML += '</div>';
                   listHTML += '</div>';
                });
                $('#list').html(listHTML);
                $('#pagination').html(pageHTML);

            }
        })
    }
</script>
</body>
</html>
