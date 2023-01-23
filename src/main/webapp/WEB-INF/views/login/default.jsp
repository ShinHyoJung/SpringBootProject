<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-17
  Time: 오후 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle"> 로그인 </p>
<form class="ui form" style="width: 30%;">
    <div class="field">
        <label>아이디 </label>
        <input type="text" id="id" name="id" onkeyup="enterLogin()">
    </div>
    <div class="field">
        <label> 비밀번호</label>
        <input type="password" id="password" name="password" onkeyup="enterLogin()">
    </div>
    <button class="ui button" type="button" onclick="login();">로그인</button>
</form>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/id/find'" style="margin-left: 270px; float:left;">아이디 찾기</button>
<button class="ui button" onclick="location.href='${pageContext.request.contextPath}/member/password/find'" style="">비밀번호 찾기/변경</button>
</body>
<script>
    function login() {
        let loginID = document.getElementById('id').value;
        let password = document.getElementById('password').value;

        if(!loginID) {
            alert("로그인 아이디를 입력해주세요.");
            return false;
        }

        if(!password) {
            alert("비밀번호를 입력해주세요.");
            return false;
        }

        let postObj = {
            'loginID':loginID,
            'password':password
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/login/do',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                alert(pageResponse.message);

                if(pageResponse.code == 'SUCCESS') {
                    location.href='${pageContext.request.contextPath}/sell/';
                }
            }
        })
    }

    function enterLogin() {
        if(window.event.keyCode == 13) {
            login();
        }
    }
</script>
</html>
