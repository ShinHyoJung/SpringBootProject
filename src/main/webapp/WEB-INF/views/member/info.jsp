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
<form action="${pageContext.request.contextPath}/member/info/update" method="post">
    <div class="grid-container">
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-7 columns">
                    <label> 이름
                        <input type="text" id="name" name="name" value = "${getInfoResponse.name}">
                    </label>
                </div>
            </div>
        </div>
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-7 columns">
                    <label> 아이디
                        ${getInfoResponse.memberID}
                    </label>
                </div>
            </div>
        </div>
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-7 columns">
                    <label> 비밀번호
                        <input type="password" id="password" name="password">
                    </label>
                </div>
            </div>
        </div>
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-7 columns">
                    <label>  생년월일
                        <input type="text" id="birth" name="birth" value="${getInfoResponse.birth}">
                    </label>
                </div>
            </div>
        </div>
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-7 columns">
                    <label> 휴대폰번호
                        <input type="text" id="mobile" name="mobile" value="${getInfoResponse.mobile}">
                    </label>
                </div>
            </div>
        </div>
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-10 columns">
                    <label> 이메일
                        <input type="text" id="mail" name="mail" value="${getInfoResponse.mail}">
                    </label>
                </div>
            </div>
        </div>
        <div class="grid-x grid-padding-x">
            <div class="row">
                <div class="small-10 columns">
                    <label> 주소
                        <input type="text" id="address" name="address" value="${getInfoResponse.address}">
                    </label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="small-7 medium-6 columns">
                <label> 생성일시
                    ${getInfoResponse.createDate}
                </label>
            </div>
        </div>
        <div class="row">
            <div class="small-7 medium-6 columns">
                <label> 변경일시
                    ${getInfoResponse.updateDate}
                </label>
            </div>
        </div>
        <input type="hidden" id="idx" name="idx" value="${getInfoResponse.idx}">
        <button class="primary button" type="submit">수정하기</button>
    </div>
</form>

<a class="submit success button" href="${pageContext.request.contextPath}/member/info/download?idx=${idx}" +>엑셀파일 다운로드</a>
<a class="alert button" href="${pageContext.request.contextPath}">메인으로 가기</a>
<a class="button warning" href="${pageContext.request.contextPath}/member/withdrawal">회원 탈퇴</a>
<script>

</script>
</body>
</html>
