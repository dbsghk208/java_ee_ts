<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- jstl 자르파일이 없으면 여기 위에 core 쓸수 없음 -->


<c:if test="${sessionScope.memId == null }">  <!-- "세션이 없을 때" -->
	<jsp:include page="../member/loginForm.jsp" />

</c:if>    
<c:if test="${sessionScope.memId != null }"><!--  세션이 있을 때 -->
	<jsp:include page="../member/loginOK.jsp" />  
</c:if> 

    <!-- 
    memId 를 잡을 것이다
    만약 memId 의 값이 존재하면 로그인했다 loginOK.jsp => 00님 로그인
    
    만약  memId 값이 존재하면 로그인 안했다 loginForm.jsp
    로그아웃을 시키면 alert (로그아웃) 띄우고 다시 index.jsp 로 이동한다. 그리고 그안에 로그인폼이 떠야한다.
    
    인터페이스 서블릿 등등 다 가져오기 js 디비도 다 퍼오기
    mvcmember 프로젝트 참고. 로그인에 관한거는 이제 한 페이지 안에서 다 해결될것.
     -->