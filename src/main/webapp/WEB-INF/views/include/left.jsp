<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-12
  Time: 오후 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<c:if test="${menu eq 'sell'}">
    <div class="ui secondary vertical pointing menu" style="width: 90%;">
        <c:forEach items="${categoryList}" var="categoryList">
        <a class="item" href="${pageContext.request.contextPath}/sell/?category=${categoryList.code}">
            ${categoryList.name}
        </a>
        </c:forEach>
    </div>
</c:if>
<c:if test="${menu eq 'manage'}">
    <div class="ui secondary vertical pointing menu" style="width: 90%;">
        <a class="item" href="${pageContext.request.contextPath}/manage/product/">
            재고 관리
        </a>
        <a class="item" href="${pageContext.request.contextPath}/manage/category/">
            카테고리 관리
        </a>
        <a class="item" href="${pageContext.request.contextPath}/manage/code/">
            제품 코드 관리
        </a>
        <a class="item" href="${pageContext.request.contextPath}/manage/service/">
            서비스 관리
        </a>
        <a class="item" href="${pageContext.request.contextPath}/inquiry/manage/">
            상담문의 관리
        </a>
    </div>
</c:if>
<c:if test="${menu eq 'user'}">
    <div class="ui secondary vertical pointing menu" style="width: 90%;">
        <a class="item" href="${pageContext.request.contextPath}/member/info">
            내 정보
        </a>
        <sec:authorize access="hasAnyRole('ROLE_USER')">
            <a class="item" href="${pageContext.request.contextPath}/want/">
                찜한 내역
            </a>
            <a class="item" href="${pageContext.request.contextPath}/purchase/ordered">
                주문 내역
            </a>
            <a class="item" href="${pageContext.request.contextPath}/purchase/cart">
                장바구니
            </a>
            <a class="item" href="${pageContext.request.contextPath}/parcel/delivery-tracking">
                배송조회
            </a>
            <a class="item" href="${pageContext.request.contextPath}/inquiry/">
                상담 문의
            </a>
        </sec:authorize>
    </div>
</c:if>
<script>
</script>
</body>
</html>
