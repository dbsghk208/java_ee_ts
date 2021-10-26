//JavaScript
/*function checkWrite(){
	document.getElementById("nameDiv").innerText = "";
	document.getElementById("idDiv").innerText = "";
	document.getElementById("pwdDiv").innerText = "";
	document.getElementById("repwdDiv").innerText= "";
	
	if(document.writeForm.name.value == "") 
		document.getElementById("nameDiv").innerText = "이름을 입력해주세요";
	else if(document.writeForm.id.value == "")
		document.getElementById("idDiv").innerText = "아이디를 입력해주세요";
	else if(document.writeForm.pwd.value == "")
		document.getElementById("pwdDiv").innerText = "비밀번호를 입력해주세요";
	else if(document.writeForm.pwd.value != document.writeForm.repwd.value)
		document.getElementById("repwdDiv").innerText = "비밀번호가 맞지 않습니다";
	else
		document.writeForm.submit();
}*/

//jQuery
$(function(){
	//회원가입
	$('#writeBtn').click(function(){
		$('#nameDiv').empty();
		$('#writeForm #idDiv').empty();
      	$('#writeForm #pwdDiv').empty();
      	$('#repwdDiv').empty();

		//id 속성
		/*if($('#name').val() == '') $('#nameDiv').html('이름 입력'); */
		
		//name 속성
		if($('input[name="name"]').val() == '') {
			$('#nameDiv').html('이름 입력');
			$('#name').focus();
		}else if($('#writeForm #id').val()=='')
			$('#writeForm #idDiv').html('아이디 입력');
		else if($('#writeForm #pwd').val()=='')
			$('#writeForm #pwdDiv').html('비밀번호 입력');
		
		else if($('#writeForm #pwd').val() != $('#repwd').val())
			$('#repwdDiv').html('비밀번호 틀림');
			
		else if($('#writeForm #id').val() != $('#check').val())
			$('#writeForm #idDiv').html('중복체크 하세요');
			
		else 
			$('form[name="writeForm"]').submit(); /*액션찾으러 감*/
	});
	
	//로그인
	$('#loginBtn').click(function(){
		$('#idDiv').empty();
      	$('#pwdDiv').empty();

		if($('input[name="id"]').val()=='')
			$('#idDiv').html('아이디 입력');
		else if($('input[name="pwd"]').val()=='')
			$('#pwdDiv').html('비밀번호 입력');
		else{
			//$('form[name="loginForm"]').submit();  - 페이지 이동
			//에이작스는 데이터를 보낸다음에 무조건 다시 돌아옴(빈손이든 데이터가 있든.)
			$.ajax({			//여기서 로그인두가 서블릿으로 가라는 말인데 여기서 서블릿으로 보냈자만 success: function(data) 에서 data는 서블릿에서 받는게 아니고 포워딩이 되어 ok를 jsp 파일에서 받는다,
				url:'/MQBProject/member/login.do',  /*액션이 사라지고 여기에 주소 만듬. 서블릿으로 가서 맵에있는내용 꺼내서 자바파일로 가고 자바에서 겟파하메터 두개 데이터 읽고*/
				type: 'post' ,                                              
				data: 'id='+$('#id').val()+'&pwd='+$('#pwd').val(),
				  /* data: 변수명=값;  형식
					데이터를 보낼건데 포스트 방식이니까  ? 보여지게끔 보내는건가..
					"id=" 은 우리가 붙여주는 별명 그리고 제이쿼리 방식으로 
					<input type="text" id="id">에서 우리가 입력했던 
					아이디의 값이 value 인데 제이쿼리에서는 val() 로 축약해서 
					쓰니까 input 태그에서 입력했던 걸 #id 로 가져와서 
					거기의 val()의 값을 넘겨준다.
					위의 문장이
					그래서 주소?id=hong&pwd=111
					이렇게 완성된다.
					제이쿼리 문장으로 우리가 스크립트를 써주니까 당연히 
					여기 문장도 제이쿼리식으로 완성해주어야 하는데 위 문장이 제이쿼리형 문장이다.
					
					>>공식 data: "파라미터 문자열 key=value&key=value",
				 */
				dataType: 'text',      //로그인jsp를 처리하고 그걸 마지막으로 가지고 옴 
				success: function(data){  /*loginService.java(클래스) 파일에서 받아온 return의 값 fail,ok를 받아온다.*/  
					alert(data);        // 내가 쳤던 문자열이 success 데이터 안에 감.
					data = data.trim();  //공백제거
					
					if(data == 'ok') {
						location.href='index.jsp';  //location.href= 페이지 이동하는겸 새로고침도 해준다. 그래서 같은 주소값치면 새로고침이 되어버림.
						
					}else if(data == 'fail') { // 로그인 실패가 되는것
						$('#loginResult').text('로그인실패');
						$('#loginResult').css('color','red');
						$('#loginResult').css('font-size','15pt');
						$('#loginResult').css('font-weight','bold');
					}
				},
				error: function(err){ /*오타라던지 주소설정을 잘못했다던지 하면 에러인데 그때 */
					console.log(err);    /*웹의 F12 콘솔창에 무슨 에러인지를 표기해준다. err 이 무슨에러인지 표기해주는 변수명이다. 정해진건 아니고 내가 설정한것.*/
				}
				
			});  /*페이지 안 넘어가고 그자리에ㅐ서 로그인 할라고*/
		} 
			
	});
});

//아이디 중복 체크
//$('#writeForm #id').change(function(){}); 이건 변하게 하겠다 라고  이 함수 써도 됨
$('#writeForm #id').focusout(function(){  /*포커스가 나오면 이벤트결과를 발생하겠다.*/
	//alert('sdfasda');					//포커스아웃이라는 함수전체가 내가 표시한 //focusout() 까지 감싸고 있다.	
	 $('#writeForm #idDiv').empty();
	
	if($('#writeForm #id').val() == '') {
		$('#writeForm #idDiv').html("먼저 아이디를 입력하세요");
		$('#writeForm #idDiv').css('color','magenta');
		
	}else{
		$.ajax({
			url:'/MQBProject/member/checkId.do',  
			//로 보내서 아이디가 있으면 "exist", 아이디가 없으면 "non_exist"를 가지고 오시오
         	type: 'post',
			data: 'id='+$('#writeForm #id').val(),  //{'id':$('writeForm #id').val()} 제이슨표기법
			dataType: 'text', 
			
			success: function(data){  //checkId.jsp 의 결과 문자열이 데이터의 값으로 들어간다,
		       // alert(data); // 이창으로 exist  non_exist 인지 결과문자열이 뜨는지 확인하고 밑에 쓰기
				data = data.trim();

				if(data == 'exist'){
		            $('#writeForm #idDiv').html('사용 불가능');
					$('#writeForm #idDiv').css('color','red');
		            
		         }else if(data == 'non_exist') {
		            $('#writeForm #idDiv').html('사용 가능');  //글자들의 스타일시트는 writeForm.jsp 에서 스타일시트 해준것
					$('#writeForm #idDiv').css('color','blue');
		         
					$('#check').val($('#writeForm #id').val()); //체크를 했을때 아이디 중복체크 칸에 도 똑같은 값이 있어서 넣어줘야 하니깐 이걸 넣어준것

				}
			}, 
			
			error: function(err){ 
				console.log(err);
			}	
		});
	}	
});  //focusout()


$('#checkIdBtn').click(function(){
	//var id = document.getElementById("id").value;
	var id = $('#writeForm #id').val();
	if(id == "")
		alert("먼저 아이디를 입력하세요");
	else
		window.open("/mvcmember/member/checkId.do?id="+id, "checkId", "width=400 height=200 top=200 left=700");	
});



//우편번호 체크
$('#zipcodeBtn').click(function(){
	window.open("/MQBProject/member/checkPost.do", "checkPost", "width=500 height=500 top=200 left=700");
});											 // 아무이름이나 써도 되는데 이걸 타겟명이라고 함, 이걸 정해주면 창이 여러개 안드고 하나만 뜬다.


//밑에 클래스 '.addressA'로 바꾸기전!
/*$('#addressA').click(function (){
	alert($('#zipcode').val()+", "+$('address'));
});*/

// 검색버튼을 누르면 이제 에이작스로 하겠다.(방법이 두가지가 있음) 전에는 널이면 아니면으로 했었음 
$('#checkPostSearchBtn').click(function(){
	$.ajax({
		url:'/MQBProject/member/checkPostSearch.do',
		type:'post',
		//data: 'sido='+$('#sido').val()+"&sigungu="+,
		data: $('#checkPostForm').serialize(), // 시리얼라이즈는 네임속성만 가져감 아이디  속성은 안가져감
		//제이슨
		//data: {
			//'sido' : $('#sido').val(),
			//'sigungu' : $('#sigungu').val(),
			//}
		dataType :'json',  // xml text 둘다 아니니까 우편주소의 한줄한줄을 제이슨으로밖에 담을수밖에 없다.,	
			//근데 우리ㄴ,ㄴ 자바파일에서 리스트로 결과를 내오고 이씅니가 리스트를 제이슨으로 변환하는 작업을 해야한다.
		success: function(data){
			alert(JSON.stringify(data)); //제이슨으로 잘 오는지 확인하는 명령어 JSON.stringify
		},
		error: function(err){ 
			console.log(err);
			}	
	});
});




$('.addressA').click(function(){
	//alert($(this).val());  /*눌렀을때 디스가 가지고 있는 값을 어럴트 창에 띄워라. 에이 태그에 있는 벨류에 값이 없으니깐 벨류가 아니라*/
	//alert($(this).text()); - 주소
	//alert($(this).parent().prop('tagName'));  /*내가 클릭한것에 이벤트  .prop -- 함수. prop('tagName') 태그네임을 바꿔라  */
	//alert($(this).parent().prev().text()); - 우편번호/  돔. 디스는 에이 태그 아늬 부모는 티디 프리비어스 내 앞의 형제  값을꺼내라(텍스트)*/
	
	$('#zipcode', opener.document).val($(this).parent().prev().text());
	$('#addr1', opener.document).val($(this).text());
	window.close();
	$('#addr2', opener.document).focus();
});








































