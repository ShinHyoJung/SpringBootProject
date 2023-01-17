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
<body>
<p class="subtitle">주문 내역</p>
    <div class="ui list">
      <c:forEach items="${purchaseList}" var="purchaseList">
        <div class="ui item">
         <!-- <img class="ui image" src="${pageContext.request.contextPath}/static/images/cut/${purchaseList.thumbnailImageName}"/> -->
          <div class="content">
            <div class="header">${purchaseList.name}
            <div class="description">${purchaseList.price}</div>
            <div class="description">${purchaseList.orderStatus}</div>
            <div class="description"> <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${purchaseList.purchaseDate}"/></div>
              <button class="ui button" onclick="cancel(this.value)" value="${purchaseList.purchaseID}">주문 취소</button>
              <button class="ui button">배송 조회</button>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
<script>
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
