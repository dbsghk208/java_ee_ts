<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
   td {
      /* width: 100px; */
      font-size: 8pt;
   }
</style>
</head>
<body>  <!-- 에이작스로 할거라서 이제 액션없애버림 --> 
   <form id="checkPostForm">   <!-- /member/checkPost.do 는 자바로 갈거고 자바는 다시 여기로 리턴해라고 하고 있음  -->
      <table border="1" cellspacing="0" cellpadding="5">
         <tr>
            <td align="center" width="100">시도</td>
            <td align="center">
               <select name="sido" id="sido">
                  <option>시도선택</option>
                  <option value="서울">서울</option>  <!--벨류를 해야 선택된 값이 들어감  -->
                  <option value="인천">인천</option>
                  <option value="대전">대전</option>
                  <option value="대구">대구</option>
                  <option value="울산">울산</option>
                  <option value="세종">세종</option>
                  <option value="광주">광주</option>
                  <option value="경기">경기</option>
                  <option value="강원">강원</option>
                  <option value="전남">전남</option>
                  <option value="전북">전북</option>
                  <option value="경남">경남</option>
                  <option value="경북">경북</option>
                  <option value="충남">충남</option>
                  <option value="충북">충북</option>
                  <option value="부산">부산</option>
                  <option value="제주">제주</option>
               </select>
            </td>
            <td align="center"  width="100">시.군.구</td>
            <td align="center"><input type="text" name="sigungu" id="sigungu"></td>
         </tr>
            
         <tr>
            <td align="center"  width="100">도로명</td>
            <td colspan="3">
               <input type="text" name="roadname"  id="roadname" style="width:250px"/>
               <input type="button" id="checkPostSearchBtn" value="검색"/>
            </td>  <!--페이지 이동안하니까 . 그즉시 그자리에 뿌릴거니따 서브밋을 버튼으로 바꿈  -->
         </tr>
         
         <tr>
            <td align="center">우편번호</td>
            <td align="center" colspan="3">주소</td>
         </tr>
       
		<c:if test="${list != null }">
			<c:forEach var="zipcodeDTO" items="${requestScope.list }"> <!-- 리퀘스트 스코프 생각가능/var="자료형이 없다 그래서 dto 이름만 해줘도 됨 " -->
				<c:set var="address">
					${zipcodeDTO.sido 
					} ${zipcodeDTO.sigungu 
					} ${zipcodeDTO.yubmyundong 
					} ${zipcodeDTO.ri 
					} ${zipcodeDTO.roadname 
					} ${zipcodeDTO.buildingname }
				</c:set>
				
				<%-- 원래는 이렇게 쳐줬으나 위에 처럼 }를 밑으로 빼도 서 연결시켜도 됨.
				<c:set var="address">  <!-- 여기 아래부분 엔터치면 안됨.  -->
          			 ${zipcodeDTO.sido } ${zipcodeDTO.sigungu } ${zipcodeDTO.yubmyundong } ${zipcodeDTO.ri } ${zipcodeDTO.roadname } ${zipcodeDTO.buildingname }
           		</c:set> --%>
				
				<tr>
					<td align="center">${zipcodeDTO.zipcode }</td> <!-- prev()내 앞 형제 , \내 뒤 형제 next() -->
					
					<td colspan="3">
						<%-- <a href="#" onclick="checkPostClose('${zipcodeDTO.zipcode }','${address }')">${address }</a> --%>
						<!-- 동작을 하고 싶은거지 이동을 하고 싶은게 아니니까 a href="#" -->
						<a href="#" class="addressA">${address }</a>  <!--원래는 아이디속성 아니고 여러개니까 클래스 속성 하는게 맞음 -->
					
					<%--이게 내가 위에 주석걸린곳에서 내가 친 코드인데 1도 모르겠다. <a href="#" id="addressA" onclick="checkPostClose('${zipcodeDTO.zipcode }','${address}')">${address}</a> 
					<!-- <c:set var="address"> 안에 데이터를 다 담아버림/ 내가 하고자 하는건 우편번호를 꺼내서 폼에 넣을거고 주소도 폼에 넣을것.  -->
            		
            	
            	<input type="hidden" id="zipcode" value="${zipcodeDTO.zipcode }">  <!-- js 에 넘기기위해 여기 만듬 -->
            	<input type="hidden" id="address" value="${address }"> --%>
					</td>
				</tr>
			</c:forEach>
		</c:if>

	</table>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- get을 자름 함수명인데 변수명 처럼 쓰기위해 -->
<script type="text/javascript" src="../js/member.js"></script>
</body>
</html>
       
       
       
        <%--  <c:if test="${list != null }"> <!--  param -->
            <c:forEach var="zipcodeDTO" items="${list }">  <!-- 리퀘스트 스코프 생각가능/var="자료형이 없다 그래서 dto 이름만 해줘도 됨 " -->
          		 <c:set var="address">  <!-- 여기 아래부분 엔터치면 안됨.  -->
          			 ${zipcodeDTO.sido } ${zipcodeDTO.sigungu } ${zipcodeDTO.yubmyundong } ${zipcodeDTO.ri } ${zipcodeDTO.roadname } ${zipcodeDTO.buildingname }
           		</c:set>
           
          <tr>
			<td align="center">${zipcodeDTO.zipcode }</td>  <!-- prev()내 앞 형제 , \내 뒤 형제 next() -->
			<td colspan="3">  <!-- 여기 디디의 형제는 위에 티디  -->
				<a href="#" class="addressA" 
				onclick="checkPostClose('${zipcodeDTO.zipcode }','${address }')">${address }</a>
			
			<a href="#" class="addressA">${address }</a>  <!--원래는 아이디속성 아니고 여러개니까 클래스 속성 하는게 맞음ㅁ -->
			</td>
	    </tr>
           
           
            <tr>
            	<td align="center">${zipcodeDTO.zipcode }</td>  <!-- get을 자름 함수명인데 변수명 처럼 쓰기위해 -->
            	<td colspan="3">
            		<a href="#" id="addressA" onclick="checkPostClose('${zipcodeDTO.zipcode }','${address}')">${address}</a> <!-- <c:set var="address"> 안에 데이터를 다 담아버림/ 내가 하고자 하는건 우편번호를 꺼내서 폼에 넣을거고 주소도 폼에 넣을것.  -->
            <!-- 동작을 하고 싶은거지 이동을 하고 싶은게 아니니까 #,  -->
            	
            	<input type="hidden" id="zipcode" value="${zipcodeDTO.zipcode }">  <!-- js 에 넘기기위해 여기 만듬 -->
            	<input type="hidden" id="address" value="${address }">
            	</td> 
            </tr>
            </c:forEach>
         </c:if>
         
         
      </table>
   </form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
function checkPostClose(zipcode, address) {
	opener.writeForm.zipcode.value = zipcode;
	opener.writeForm.addr1.value = address;
	window.close();
	opener.writeForm.addr2.focus();
}
</script>
   
</body>
</html> --%>




<!-- </head>
<body>
<form name="postForm" method="post" action="/mvcmember/member/checkPost.do">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="80" align="center">시도</td>
			<td>
				<select name="sido" style="width: 70px;">
					<option value="서울" selected>서울</option>
					<option value="" >011</option>
					<option value="019" >019</option>
				</select>
			<td width="150" align="center">시.군.구</td>
			<td><input type="text" name="sigungu" size="6" maxlength="4"></td>
		</tr>
		
		<tr>
			<td width="80" align="center">도로명</td>
			<td colspan="3" align="center"> 
				<input type="text" name="roadname" id="roadname" size="30" >
				<input type="button" id="writeBtn" value="회원가입">
				<div id= "rnDiv"></div>
			</td>	
		</tr>
</table>
</form>
우편번호
</body>
</html> -->