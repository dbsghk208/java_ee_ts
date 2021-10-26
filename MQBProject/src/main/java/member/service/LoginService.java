package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

   @Override     // requestPro( 메소드를 부른애는 서블릿임 . 서블릿에서 요청했기때문에 여기로 넘어옴 그리고 다시 리턴값은(페일과 오케이는) 서블릿으로 돌아감
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      //데이터
      String id = request.getParameter("id");  //data: id='+$('#id').val()+'&pwd='+$('#pwd').val() 이게 리퀘스트에 담아져있다.
      String pwd = request.getParameter("pwd");
      //DB 접속
      MemberDAO memberDAO = MemberDAO.getInstance(); //싱글톤 - 1번 생성해서 계속 사용한다
      MemberDTO memberDTO= memberDAO.login(id, pwd); // MemberDAO 클래스에서 이메일 값때문에 자료형 자체를 변환시켜줌
                                    // 마이바티스는 데이터를 두개 못보냄 그래서 디에이오에서 맵으로 묶는다.여기서 묶어도 되긴한데 다른프로젝트 참고하고 여기선 DAO 에서 Map으로 묶을것이다.
      //자바는 리턴값이 2개가 안되고 1개만 됨
      //배열이나 맴버 디티오로 받아와도 되고 map으로 받아와도 됨 게시판때 맵을 사용할것
      
      //응답
      if(memberDTO != null) {  //아이디와 비밀번호가 맞는사람이 있었으면 디티오가 널값이 아니고 값이 있는거니까 이때는 어떻게 할꺼냐 
      
         //세션     세션에 담는다. 아이디 비번 맞는사람이 있었을때만 세션에 값을 담는것.
         HttpSession session =request.getSession();//세션생성
         session.setAttribute("memName", memberDTO.getName());  //여기가 MemberDAO 클래스에서 MemberDTO login(String id, String pwd) 에서 만들었던 맴버디티오값들을 불어와서 세션에 넣어줌 
		 session.setAttribute("memId", id); /* 리퀘스트를 통해서 아이디값을 얻어오니까 직접적으로 쓸수 있음 */
         session.setAttribute("memEmail", memberDTO.getEmail1()+"@"+memberDTO.getEmail2());
         
      
         request.setAttribute("memDTO", memberDTO);
       
         //return "/member/loginOk.jsp?name="+name; //주소에 실어 보내기
      }
      return "/member/login.jsp";  // 리턴을 여기서 ok 시키면 안됨 jsp 파일에서 오케이가 가서 그 오케이가 member.js의 success로 간다
      
   }

}
/*
login.jsp 는 주석을 못달아서 그냥 여기다 메모!
세션의 값을 여기 loginService에서 만들어줬으니깐 그리고 넘길데이터가 세션밖에 없으니까 login.jspdp rkaus
sesionScope.memId 로 조건문을 넣어주는거고.
ok,fail은 member.js 에서 dataType 을 text 로 받아온다고 했으니깐 그 조건에 해당하는 텍스트를 기재해준것이다.
 
 
 
 * */
