<%--
  Created by IntelliJ IDEA.
  User: ShinHyoJung
  Date: 2022-11-16
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
<p class="subtitle"> 회원가입 </p>
   <form class="ui form" action="${pageContext.request.contextPath}/member/signUp" method="post" name="signUpForm" style="width: 30%;">
         <div class="field">
            <label> 아이디 </label>
               <input type="text" id="memberID" name="memberID" placeholder="아이디">
         </div>
         <div class="field">
            <label> 비밀번호 </label>
               <input type="password" id="password" name="password" placeholder="비밀번호">
         </div>
         <div class="field">
            <label> 이름 </label>
               <input type="text" id="name" name="name" placeholder="이름">
         </div>
         <div class="field">
            <label> 생년월일 </label>
               <input type="text" id="birth" name="birth" placeholder="생년월일">
         </div>
         <div class="field">
            <label> 휴대폰번호 </label>
               <input type="text" id="mobile" name="mobile" placeholder="휴대폰번호">
         </div>
         <div class="field">
            <label> 이메일  </label>
               <input type="text" id="mail" name="mail" placeholder="이메일">
         </div>
       <div class="field">
           <label> 우편번호 </label> <button class="ui button" type="button" onclick="searchZipCode();">우편번호 찾기</button>
           <input type="text" id="zipCode" name="zipCode" placeholder="우편번호" style="margin-top: 10px;">
       </div>
       <div class="field">
            <label> 주소 </label>
            <input type="text" id="address" name="address" placeholder="주소">
       </div>
       <div class="field">
           <label> 상세 주소 </label>
           <input type="text" id="detailAddress" name="detailAddress" placeholder="상세 주소">
       </div>
      <button class="ui button" type="button" onclick="signUp()">가입</button>
   </form>
<input type="hidden" id="isVerified" value="">
<script>
   $(document).ready(function(){
      document.getElementById('isVerified').value = false;
   })

   function sendEmail() {
      let mail = document.getElementById('mail').value;
      let subject = '본인인증';
      let templateName = 'self-authentication';

      let postObj = {
         'mail':mail,
         'subject':subject,
         'templateName':templateName
      };

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

   function searchZipCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                let addr = '';
                let extraAddr = '';

                if(data.roadAddress !== '') {
                    addr = data.roadAddress;
                } else if(data.jibunAddress !== '') {
                    addr = data.jibunAddress;
                }

                if(data.userSelectType === 'R') {
                    if(data.bname !== ''&&/[동][로][가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }

                    if(data.buildingName !== ''&& data.apartment === 'Y') {
                        extraAddr += (extraAddr !== ''? ',' + data.buildingName : data.buildingName);
                    }

                    if(extraAddr !== '') {
                        extraAddr = '(' + extraAddr + ')';
                    }
                    document.getElementById('address').value = extraAddr;
                } else {
                    document.getElementById('address').value = '';
                }

                document.getElementById('zipCode').value = data.zonecode;
                document.getElementById('address').value = addr;

                document.getElementById('address').focus();
            }
        }).open();
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
