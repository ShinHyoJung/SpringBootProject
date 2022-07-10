<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2022-07-10
  Time: 오후 7:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채팅</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        .container{
            width: 500px;
            margin: 0 auto;
            padding: 25px
        }
        .chating{
            background-color: dodgerblue;
            width: 500px;
            height: 500px;
            overflow: auto;
        }
        .chating p{
            color: #fff;
            text-align: left;
        }
        input{
            width: 330px;
            height: 25px;
        }
        #yourMsg{
            display: none;
        }
    </style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<div id="container" class="container">
    <h1>채팅</h1>
    <div id="chating" class="chating">
    </div>

    <div id="yourName">
        <table class="inputTable">
            <tr>
                <th>사용자명</th>
                <th><input type="text" name="userName" id="userName"></th>
                <th><button onclick="chatName()" id="startBtn">이름 등록</button></th>
            </tr>
        </table>
    </div>
    <div id="yourMsg">
        <table class="inputTable">
            <tr>
                <th>메시지</th>
                <th><input id="message" onkeyup="enterSend()" placeholder="보내실 메시지를 입력하세요."></th>
                <th><button onclick="send()" id="sendBtn">보내기</button></th>
            </tr>
        </table>
    </div>
</div>
</body>

<script>
    var ws;
    function wsOpen() {
        ws = new WebSocket("ws://" + location.host + "/developer/chating");
        wsEvt();
    }

    function wsEvt() {
        ws.onopen = function(data) {

        }
        ws.onmessage = function (data) {
            var msg = data.data;
            if(msg != null && msg.trim() != "") {
                $("#chating").append("<p>" + msg + "</p>");
        }

        //document.addEventListener("keypress", function(e) {


        }
    }

    function chatName() {
        var username = $("#userName").val();
        if(username == null || username.trim() == "") {
            alert("사용자 이름을 입력해주세요.");
            $("#userName").focus();
        } else {
            wsOpen();
            $("#yourName").hide();
            $("#yourMsg").show();
        }
    }

    function enterSend() {
        if (window.event.keyCode == 13) {
            send();
        }
    }

    function send() {
        var username = $("#userName").val();
        var msg = $("#message").val();
        ws.send(username + ":" + msg);
        $("#message").val("");
    }
</script>
</html>
