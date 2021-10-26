<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<style type="text/css">
img{
   width: 100px;
   height: 100px;
   cursor: pointer;
}
</style>

${sessionScope.memName }님 로그인
<br><br>

<input type="button" value="로그아웃" onclick="location.href='/MQBProject/member/logout.do'">
<input type="button" value="회원정보수정" onclick="location.href='/MQBProject/member/modifyForm.do'">