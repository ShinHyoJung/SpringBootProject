<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2023-01-21
  Time: 오후 5:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p class="subtitle">비밀번호 찾기</p>
<form class="ui form" style="width: 30%">
    <div class="field">
        <label>아이디</label>
        <input type="text" id="loginID" name="loginID" placeholder="아이디">
    </div>
    <div class="field">
        <label>이름</label>
        <input type="text" id="name" name="name" placeholder="이름">
    </div>
    <div class="field">
        <label>생년월일</label>
        <input type="text" id="birth" name="birth" placeholder="생년월일">
    </div>
    <div class="field">
        <label>이메일</label>
        <input type="text" id="email" name="email" placeholder="이메일">
    </div>
    <div class="field">
        <button class="ui button" type="button" onclick="sendEmail()">인증번호 전송</button>
    </div>
    <div class="field">
        <div id="timeout"></div>
    </div>
    <div class="field">
        <label>인증번호</label>
        <input type="text" id="code" name="code" placeholder="인증번호">
    </div>
    <button class="ui button" type="button" onclick="find()">비밀번호 변경</button>
</form>
<input type="hidden" id="idx" value="">
<script>
    let emailVal = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    let timer = null;
    let isRunning = false;
    let display = $('#timeout');

    function sendEmail() {
        let loginID = document.getElementById('loginID').value.trim();
        let name = document.getElementById('name').value.trim();
        let birth = document.getElementById('birth').value.trim();
        let email = document.getElementById('email').value.trim();

        if(!loginID) {
            alert("아이디를 입력해주세요.");
            return false;
        }

        if(!name) {
            alert("이름을 입력해주세요.");
            return false;
        }

        if(!birth) {
            alert("생년월일을 입력해주세요.");
            return false;
        }

        if(!email) {
            alert("이메일을 입력해주세요.");
            return false;
        }

        if(!emailVal.test(email)) {
            alert("이메일 형식에 맞지않습니다. 다시 입력해주세요.");
            return false;
        }

        let postObj = {
            'loginID':loginID
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/member/id/validate',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                if(pageResponse.code == 'SUCCESS') {
                    postObj = {
                        'name':name,
                        'birth':birth,
                        'email':email
                    };

                    $.ajax({
                        url: '${pageContext.request.contextPath}/member/validate',
                        method: 'post',
                        dataType: 'json',
                        data: JSON.stringify(postObj),
                        contentType: 'application/json; charset=utf-8',
                        success: function(pageResponse) {
                            if(pageResponse.code == 'SUCCESS') {
                                document.getElementById('idx').value = pageResponse.idx;

                                postObj = {
                                    'email':email,
                                    'subject':'본인인증용 코드',
                                    'templateName':'authentication_code',
                                    'text':'본인 인증용 인증번호 입니다. 위 인증번호를 입력창에 기입하여 주세요.'
                                };

                                $.ajax({
                                    url: '${pageContext.request.contextPath}/authentication/send-email',
                                    method: 'post',
                                    dataType: 'json',
                                    data: JSON.stringify(postObj),
                                    contentType: 'application/json; charset=utf-8',
                                    success: function(pageResponse) {
                                        setTimer();
                                        alert(pageResponse.message);
                                    }
                                })

                            } else {
                                alert(pageResponse.message);
                            }
                        }
                    })
                } else {
                    alert(pageResponse.message);
                }
            }
        });
    }

    function find() {
        let code = document.getElementById('code').value;

        let postObj = {
            'code':code
        };

        $.ajax({
            url: '${pageContext.request.contextPath}/authentication/check-auth',
            method: 'post',
            dataType: 'json',
            data: JSON.stringify(postObj),
            contentType: 'application/json; charset=utf-8',
            success: function(pageResponse) {
                alert(pageResponse.message);

                if(pageResponse.code == 'SUCCESS') {
                    let idx = document.getElementById('idx').value;
                    location.href='${pageContext.request.contextPath}/member/password/change?idx=' + idx;
                }
            }
        })
    }

    function setTimer() { // 타이머 설정
        document.getElementById('code').disabled = false;
        document.getElementById('code').value = null;
        let leftSec = 180;

        if(isRunning) {
            clearInterval(timer);
            display.html('');
            startTimer(leftSec, display);
        } else {
            startTimer(leftSec, display);
        }
    }

    function startTimer(leftSec, display) { // 인증 타이머 시작
        let minutes, seconds;

        timer = setInterval(function () {
            minutes = parseInt(leftSec / 60, 10);
            seconds = parseInt(leftSec % 60, 10);

            minutes = minutes < 10 ? '0' + minutes : minutes;
            seconds = seconds < 10 ? '0' + seconds : seconds;

            display.html(minutes + ':' + seconds);

            if(--leftSec < 0) {
                clearInterval(timer);
                alert("시간이 초과되었습니다.")
                $('#alert').modal('show');

                display.html('00:00');
                isRunning = false;
                document.getElementById('code').disabled = true;
            }
        }, 1000);
        isRunning = true;
    }
</script>
</body>
</html>
