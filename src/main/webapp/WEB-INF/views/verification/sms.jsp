<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-07-08
  Time: 오전 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sms</title>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<input type="text" id="phoneNum" placeholder="전화번호"/>
<button type="button" onclick="code();">인증번호 받기</button>
<input type="text" id="code" placeholder="내용 입력"/>
<button type="button" id="check" onclick="validate()">인증 확인</button>
<br>
<a href="/demo/home">돌아가기</a>
<script>
    function code() {
        var phoneNum = document.getElementById("phoneNum").value;

        $.ajax({
            method:"post",
            dataType: "json",
            data: {"phoneNum":phoneNum},
            url: "/demo/sms"
        })
        alert("인증번호가 전송되었습니다.");
    }

    function validate() {
        var code = document.getElementById("code").value;
        var postObj = {"code":code};

        $.ajax({
            method:"post",
            dataType: "json",
            data: JSON.stringify(postObj),
            url:"/validate",
            success: function(data) {
                alert(data.message);
            }
        })
    }


</script>
</body>
</html>

