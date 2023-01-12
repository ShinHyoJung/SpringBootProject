<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-12
  Time: 오후 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:if test="${isUseCategoryMenu}">
    <div class="ui secondary vertical pointing menu" style="width: 90%;">
        <a class="active item">
            노트북
        </a>
        <a class="item">
            모니터
        </a>
        <a class="item">
           주변기기
        </a>
    </div>
</c:if>
<c:if test="${isUseUserMenu}">
    <div class="ui secondary vertical pointing menu" style="width: 90%;">
        <a class="active item">
            주문 내역
        </a>
        <a class="item">
            배송조회
        </a>
        <a class="item">
            상담 문의
        </a>
    </div>
</c:if>
</body>
</html>
