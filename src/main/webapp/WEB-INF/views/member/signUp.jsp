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
         <input type="hidden" id="loginID" name="loginID" value="">
         <div class="field">
            <label> 아이디 </label>
               <input type="text" id="inputLoginID" placeholder="아이디">
         </div>
         <div class="field">
           <button class="ui button" type="button" onclick="checkDuplicate()">아이디 중복체크</button>
         </div>
         <div class="field">
            <label> 비밀번호 </label>
               <input type="password" id="password" name="password" placeholder="비밀번호">
         </div>
         <div class="field">
            <label> 비밀번호 확인</label>
            <input type="password" id="passwordConfirm" placeholder="비밀번호 확인">
         </div>
         <div class="field">
            <label> 이름 </label>
               <input type="text" id="name" name="name" placeholder="이름">
         </div>
         <div class="field">
            <label> 생년월일 </label>
               <input type="text" id="birth" name="birth" placeholder="생년월일 8자리">
         </div>
         <div class="field">
            <label> 휴대폰번호 </label>
               <input type="text" id="phone" name="phone" placeholder="휴대폰번호">
         </div>
         <div class="field">
            <label> 이메일  </label>
               <input type="text" id="email" name="email" placeholder="이메일">
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
<script>
    let idVal = /^[a-z][0-9a-z]{4,9}$/;
    let pwdVal = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]).{10,20}$/;
    let emailVal = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    let phoneVal = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    let blankVal = /^.*( ).*$/;

   function sendEmail() {
      let email = document.getElementById('email').value;
     // let subject = '본인인증';
      //let templateName = 'self-authentication';

      let postObj = {
         'mail':email,
      //   'subject':subject,
    //     'templateName':templateName
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

   function checkDuplicate() {
       let loginID = document.getElementById('inputLoginID').value.trim();

       if(!loginID) {
           alert("아이디를 입력해주세요.");
           return false;
       }

       if(!idVal.test(loginID) || blankVal.test(loginID)) {
           alert("아이디는 공백없이 영문소문자와 숫자로 5~10자리로 입력해주세요.");
           return false;
       }

       let postObj = {
           'loginID':loginID
       };

       $.ajax({
           url: '${pageContext.request.contextPath}/member/check-duplicate',
           method: 'post',
           dataType: 'json',
           data: JSON.stringify(postObj),
           contentType: 'application/json; charset=utf-8',
           success: function(pageResponse) {
               alert(pageResponse.message);

               if(pageResponse.code == 'SUCCESS') {
                   document.getElementById('loginID').value = document.getElementById('inputLoginID').value;
                   document.getElementById('inputLoginID').disabled = true;
               }
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
      let password = document.getElementById('password').value.trim();
      let passwordConfirm = document.getElementById('passwordConfirm').value.trim();
      let name = document.getElementById('name').value.trim();
      let birth = document.getElementById('birth').value.trim();
      let phone = document.getElementById('phone').value.trim();
      let email = document.getElementById('email').value.trim();
      let zipCode = document.getElementById('zipCode').value.trim();
      let address = document.getElementById('address').value.trim();
      let detailAddress = document.getElementById('detailAddress').value.trim();

      if(!document.getElementById('inputLoginID').disabled) {
          alert("아이디 중복체크를 해주세요.");
          return false;
      }

      if(!password){
         alert("비밀번호를 입력해주세요.");
         return false;
      }

      if(!pwdVal.test(password) || blankVal.test(password)) {
          alert("비밀번호는 공백없이 영문대소문자와 숫자, 특수문자 포함하여 10~20자리로 입력해주세요.");
          return false;
      }

      if(!passwordConfirm) {
          alert("비밀번호를 한번 더 입력해주세요.");
          return false;
      }

      if(password != passwordConfirm) {
          alert("비밀번호가 서로 다릅니다. 다시 입력해주세요.");
          return false;
      }

      if(!name) {
         alert("이름을 입력해주세요.");
         return false;
      }

      if(!birth) {
          alert("생년월일 8자리를 입력해주세요.");
          return false;
      }

      if(!phone) {
          alert("휴대폰 번호를 입력해주세요.");
          return false;
      }

      if(!email) {
          alert("이메일을 입력해주세요.");
          return false;
      }

      if(!phoneVal.test(phone)) {
          alert("휴대폰 번호 형식이 맞지 않습니다. 다시 입력해주세요.");
          return false;
      }

      if(!emailVal.test(email)) {
          alert("이메일 형식이 맞지 않습니다. 다시 입력해주세요.");
          return false;
      }

      if(!zipCode) {
          alert("우편번호를 입력해주세요.");
          return false;
      }

      if(!address) {
          alert("주소를 입력해주세요.");
          return false;
      }

      if(!detailAddress) {
          alert("상세 주소를 입력해주세요.");
          return false;
      }

     let form = document.signUpForm;
     form.submit();
   }
</script>
</body>
</html>
