<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 body{
      margin: 0;
      padding: 0;
      height: 100%;
      width: auto;
   }
   
   header{
      width: 1700px;
      height: 10%;
      text-align: center;
   }
   
   #container{
      margin: auto;
      width: 1700px;
      height: 500px;
   }
   
   #container:after{
      content: '';
      display: block;
      clear: both;
      float: none;
   }

   
   nav{
      margin-left: 10px;
      width: 25%;
      height: 100%;
      float: left;
   }
   
   section{
      width: 70%;
      height: 100%;
      float: left;
   }
   
   footer{
      width: 1700px;
      height: 10%;
   }

</style>
</head>
<body> <!-- 파일이 인덱스에서 시작하고 네비를 부른다. 그담 네비 파일을 가면 또 네비에서는 각각의 로그인폼 로그인 오케이 풀러옴  소스가 또 끼어오고 (네비에) 그담 인덱스로 간다. -->
<!-- 시멘틱테그./ div id="header" 둘중 아무거나 써도 됨 -->
<!-- <div id="header"> </div>-->
<header>  
	<h1>
		<img alt="냥" src="/MQBProject/image/4.jpg" width="100" height="130"
		onclick="location.href='/MQBProject/index.jsp'" style="cursor:pointer;">MVC 기반의 미니 프로젝트
	</h1>

	<jsp:include page="main/menu.jsp"/> <!--인클루드로 가서 메뉴에 가서 끌고와라. 스타일시트까지 끌고오니까 스타일시트 충돌나는건 없어져버림  -->
</header>

<!-- menu.jsp를 여기에 끌고 와라(포함해라) include -->
<div id="container">  <!--  -->
	<nav>   <!-- // 나에게 main/nav.jsp 가지고 인덱스로 와라 --> 
		<jsp:include page="main/nav.jsp"/>  <!--로그인폼 소스가 네비에 의해 끌려들어왔다.  -->
	</nav>
	
	<section>
		<c:if test="${empty display}">
			<jsp:include page="main/body.jsp"/>
		</c:if>
		<c:if test="${not empty display}">
			<jsp:include page="${display }"/>  <!--section 부분도 에이작스로 회원가입 띄워야되니깐 고정적이지 않은 화면이 되니까  -->
		</c:if>
	</section><!--어떨때는 바디화면 어떨때는 회원가입폼 그래서 ${display } 별명을 붙임. 그럼 디스플레이 값이 매번 바뀐다. -->
		          <!-- body.jsp  -->
</div>
<footer>
	<p>비트캠프</p>
</footer>

</body>
</html>