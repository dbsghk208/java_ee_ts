<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
div#idDiv, div#pwdDiv {
	color: red;
	font-size: 8pt;
	font-weight: bold;
}
</style>
</head>  <!-- 여기서 폼 서브밋을 하면 아예 넘어가버림 우리는 네비부분만 바뀌게 하고싶으니까 에이작스를 쓴것 -->
<body> <!-- 액션이 없으면 백퍼JS 에서 뭔가 하고 있다는것 -->								<!-- action="http://localhost:8080/mvcmember/member/login.do" -->
<form name="loginForm" >  <!--js 에 에이작스에 주소랑 포스트 방식이라고 쓰니깐 여기가 간략해짐  -->
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">아이디</td>
			<td>
				<input type="text" name="id" id="id" placeholder="아이디 입력">
				<div id= "idDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td width="100" align="center">비밀번호</td>
			<td>
				<input type="password" name="pwd" id="pwd" size="30" placeholder="비밀번호 입력">
				<div id= "pwdDiv"></div>
			</td>	
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="loginBtn" value="로그인">
				<input type="button" value="회원가입" 
				onclick="location.href='/MQBProject/member/writeForm.do'">
			</td>
		</tr>
	</table>
	<br><br>
	<div id="loginResult"></div>  <!-- 여기에 뜰것 로그인 결과가! 여기는 member.js 에서 결과를 받아올것임, -->
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/member.js">

</script>

