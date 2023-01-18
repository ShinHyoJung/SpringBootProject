<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-16
  Time: 오전 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body onload="printList()">
<p class="subtitle">주문 내역</p>
    <div class="ui relaxed divided list" id="list">
    <!--<c:forEach items="${purchaseList}" var="purchaseList">
        <div class="ui item">
             <img class="ui image" src="${pageContext.request.contextPath}/static/images/cut/${purchaseList.thumbnailImageName}"/>
          <div class="content">
              <div class="description">주문 번호 ${purchaseList.purchaseID}</div>
              <div class="description">제품 명 ${purchaseList.name}</div>
              <div class="description">수량 ${purchaseList.price}</div>
              <div class="description">주문 상태 ${purchaseList.orderStatus}</div>
              <div class="description"> <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${purchaseList.purchaseDate}"/></div>
              <button class="ui button" onclick="cancel(this.value)" value="${purchaseList.purchaseID}">주문 취소</button>
          </div>
        </div>
      </c:forEach>-->
    </div>
<div id="pagination" class="ui pagination menu"></div>
<script>
function printList(currentPage) {
    if(!currentPage) {
        currentPage = 1;
    }

    let postObj = {
        'currentPage':currentPage
    };

    $.ajax({
        url: '${pageContext.request.contextPath}/purchase/ordered/list',
        method: 'post',
        dataType: 'json',
        data:JSON.stringify(postObj),
        contentType: 'application/json; charset=utf-8',
        success: function(pageResponse) {
            console.log(pageResponse);
            let pageHTML = '';
            let listHTML = '';

            if (pageResponse.code == "SUCCESS") {
                if (pageResponse.paging.prev) {
                    pageHTML += '<a class="item"> < </a>';
                }

                for (let i = pageResponse.paging.startPage; i <= pageResponse.paging.endPage; i++) {
                    if (currentPage == i) {
                        pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                    } else {
                        pageHTML += '<a class="item" onclick="printList(' + i + ')">' + i + '</a>';
                    }
                }

                if (pageResponse.paging.next) {
                    pageHTML += '<a class="item"> > </a>';
                }

                $.each(pageResponse.purchaseList, function(i, purchaseList) {
                    listHTML += '<div class="ui item">';
                    listHTML += '<div class="description">주문 번호: ' + purchaseList.purchaseID + '</div>';
                    listHTML += '<div class="description">제품 명: ' + purchaseList.name + '</div>';
                    listHTML += '<div class="description">수량: ' + purchaseList.quantity + '</div>';
                    listHTML += '<div class="description">가격 : ' + purchaseList.price + '</div>';
                    listHTML += '<div class="description">주문 상태: ' + purchaseList.orderStatus + '</div>';
                    listHTML += '<div class="description">주문 일시: ' + purchaseList.purchaseDate + '</div>';
                    listHTML += '<button class="ui button" type="button" onclick="cancel('+ purchaseList.purchaseID + ')">주문취소</button>';
                    listHTML += '</div>';
                });

                $('#list').html(listHTML);
                $('#pagination').html(pageHTML);
            } else {
                $('#list').css('display', 'none');
                $('#pagination').css('display', 'none');
                alert(pageResponse.message);
            }
        }
    });
}

function cancel(purchaseID) {
    let postObj = {
      'purchaseID':purchaseID
    };

    $.ajax({
      url: '${pageContext.request.contextPath}/purchase/cancel',
      method: 'post',
      dataType: 'json',
      data: JSON.stringify(postObj),
      contentType: 'application/json; charset=utf-8',
      success: function(pageResponse) {
          location.reload();
      }
    });
  }
</script>
</body>
</html>
