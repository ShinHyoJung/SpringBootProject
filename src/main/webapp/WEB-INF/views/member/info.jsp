<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle">내 정보</p>
<form class="ui form" action="${pageContext.request.contextPath}/member/info/update" method="post" style="width: 50%;">
    <div class="field">
        <label> 이름  </label>
            <input type="text" id="name" name="name" value = "${getInfoResponse.name}">
    </div>
    <div class="field">
        <label> 아이디  </label>
        ${getInfoResponse.memberID}
    </div>
    <div class="field">
        <label> 비밀번호 </label>
            <input type="password" id="password" name="password">
    </div>
    <div class="field">
        <label>  생년월일 </label>
            <input type="text" id="birth" name="birth" value="${getInfoResponse.birth}">
    </div>
    <div class="field">
        <label> 휴대폰번호 </label>
            <input type="text" id="mobile" name="mobile" value="${getInfoResponse.mobile}">
    </div>
    <div class="field">
        <label> 이메일 </label>
            <input type="text" id="mail" name="mail" value="${getInfoResponse.mail}">
    </div>
    <div class="field">
        <label> 주소 </label>
            <input type="text" id="address" name="address" value="${getInfoResponse.address}">
    </div>
    <div class="field">
        <label> 생성일시 </label>
        <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getInfoResponse.createDate}"/>
    </div>
    <div class="field">
        <label> 변경일시 </label>
        <fmt:formatDate pattern="yyyy-MM-dd hh:MM" value="${getInfoResponse.updateDate}"/>
    </div>
        <input type="hidden" id="idx" name="idx" value="${getInfoResponse.idx}">
        <button class="ui button" type="submit"><i class="save icon"></i></button>
</form>

<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/info/download?idx=${idx}'">
    <i class="download icon"></i>엑셀파일 다운로드</button>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}'">메인으로 가기</button>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/withdrawal'">회원 탈퇴</button>
</body>
</html>
