<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<p>회원 가입</p>

<form action="${pageContext.request.contextPath}/member/signUp" method="post" name="signUpForm">
   아이디:  <input type="text" id="memberID" name="memberID" placeholder="아이디"> <br>
   비밀번호:  <input type="password" id="password" name="password" placeholder="비밀번호">  <br>
   이름: <input type="text" id="name" name="name" placeholder="이름">  <br>
   생년월일:  <input type="text" id="birth" name="birth" placeholder="생년월일">  <br>
   휴대폰번호:  <input type="text" id="mobile" name="mobile" placeholder="휴대폰번호"> <button type="button" onclick="sendSms()">전송</button> <br>
   이메일: <input type="text" id="mail" name="mail" placeholder="이메일">  <button type="button" onclick="sendEmail()">전송</button> <br>
   본인인증 코드 :<input type="text" id="code" name="code" placeholder="코드 입력"> <button type="button" onclick="checkAuth()">인증</button><br>
   주소: <input type="text" id="address" name="address" placeholder="주소">  <br>
   <button type="button" onclick="signUp()">확인</button>
</form>
<input type="hidden" id="isVerified" value="">
<script>
   $(document).ready(function(){
      document.getElementById('isVerified').value = false;
   })

   function sendEmail() {
      let mail = document.getElementById('mail').value;
      let subject = '인증번호 발송';
      let templateName = 'self-authentication';
      let text = '본인인증';

      let postObj = {
         'mail':mail,
         'subject':subject,
         'templateName':templateName,
         'text':text
      };


      if(document.getElementById('isVerified').value = 'true') {
         alert("인증이 이미 완료되었습니다.");
      }

      $.ajax({
         method: 'post',
         url: '${pageContext.request.contextPath}/authentication/send-mail',
         dataType: 'json',
         data: JSON.stringify(postObj),
         contentType: 'application/json; charset=utf-8',
         success: function(pageResponse) {
            alert(pageResponse.message);
         }
      })
   }

   function sendSms() {
      let mobile = document.getElementById('mobile').value;
      let type = 'SMS';

      let postObj = {
         'mobile':mobile,
         'type':type
      }

      if(document.getElementById('isVerified').value) {
         alert("인증이 이미 완료되었습니다.");
         return false;
      }

      $.ajax({
         method: 'post',
         url: '${pageContext.request.contextPath}/authentication/send-sms',
         dataType: 'json',
         data: JSON.stringify(postObj),
         contentType: 'application/json; charset=utf-8',
         success: function(pageResponse) {
            alert(pageResponse.message);
         }
      })
   }

   function checkAuth() {
      let code = document.getElementById('code').value;

      let postObj = {
         'code':code
      }

      $.ajax({
         method: 'post',
         url: '${pageContext.request.contextPath}/authentication/check-auth',
         dataType: 'json',
         data: JSON.stringify(postObj),
         contentType: 'application/json; charset=utf-8',
         success: function(pageResponse) {
            alert(pageResponse.message);
            document.getElementById('isVerified').value = true;
         }
      })
   }

   function signUp() {
      let memberID = document.getElementById('memberID').value;
      let password = document.getElementById('password').value;
      let name = document.getElementById('name').value;
      const isVerified = document.getElementById('isVerified').value;

      if(memberID == "") {
         alert("아이디를 입력해주세요.");
         return false;
      }

      if(password == ""){
         alert("비밀번호를 입력해주세요.");
         return false;
      }

      if(name == "") {
         alert("이름을 입력해주세요.");
         return false;
      }

      if(!isVerified) {
         alert("본인인증을 해주세요.");
         return false;
      }

     let form = document.signUpForm;
     form.submit();
   }
</script>
</body>
</html>
