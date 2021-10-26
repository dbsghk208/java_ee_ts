<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="modifyForm" method="post" action="/mvcmember/member/modify.do">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">이름</td>
			<td>
				<input type="text" name="name" id="name" value="${sessionScope.memName }" >
				<div id= "nameDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">아이디</td>
			<td>
				<input type="text" name="id" id="id"  value="${sessionScope.memId }" readonly>  <!-- onclick="location.href='/mvcmember/member/checkId.do'" -->
				<div id= "idDiv"></div>  <!-- 중복체크 버튼 누르면 id의 값을 넣을것  -->
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">비밀번호</td>
			<td>
				<input type="password" name="pwd" id="pwd"  value="${sessionScope.memPwd }" size="30" placeholder="비밀번호 입력">
				<div id= "pwdDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">재확인</td>
			<td>
				<input type="password" name="repwd" id="repwd" size="30" placeholder="비밀번호 입력">
				<div id= "repwdDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">성별</td>
			<td>
				<input type="radio" name="gender" value="0" checked>남
				<input type="radio" name="gender" value="1">여
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">이메일</td>
			<td>
				<input type="text" name="email1" value="${sessionScope.memEmail1 }">
				@
				<input type="text" name="email2" list="email2" value="${sessionScope.memEmail2 }" placeholder="직접입력">
				<datalist id="email2">
					<option value="naver.com">naver.com
					<option value="daum.net">daum.net
					<option value="gmail.com">gmail.com
				</datalist>
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">핸드폰</td>
			<td>
				<select name="tel1" style="width: 70px;">
					<option value="010" selected>010</option>
					<option value="011" >011</option>
					<option value="019" >019</option>
				</select>
				-
				<input type="text" name="tel2" value="${sessionScope.memTel2 }"  size="6" maxlength="4">
				-
				<input type="text" name="tel3" value="${sessionScope.memTel3 }"  size="6" maxlength="4">
			</td>
		</tr>
		
		<tr>
			<td width="100" align="center">주소</td>
			<td>
				<input type="text" name="zipcode" id="zipcode"   value="${sessionScope.memZipcode }" readonly>
				<input type="button" value="우편번호 검색" id="zipcodeBtn"><br>
				<input type="text" name="addr1" id="addr1" size="60"  value="${sessionScope.Addr1 }" readonly><br>
				<input type="text" name="addr2" id="addr2" size="60"  value="${sessionScope.Addr2 }">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="modifyBtn" value="회원정보수정"> <!-- 버튼이 역할 해주니깐 온클릭 필요 없음  -->
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">  /* 여기에 써야 el태그 쓸수 있음 */

//회원정보수정
$('modifyBtn').click(function(){
	$('#nameDiv').empty();
	
    $('#pwdDiv').empty();
	$('#repwdDiv').empty();

	if($('input[name="name"]').val() == ''){
		$('#nameDiv').html('이름 입력');
		$('#name').focus();
	} else if($('input[name="pwd"]').val()=='')
		$('#pwdDiv').html('비밀번호 입력');
	else if($('input[name="pwd"]').val() != $('input[name="repwd"]').val())
		$('#repwdDiv').html('비밀번호 틀림');

});
</script>

</body>
</html>