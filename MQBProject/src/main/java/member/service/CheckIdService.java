package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class CheckIdService implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		//데이터
		String id = request.getParameter("id");  //member.js데이터 넘겨울때 아이디는 이라고 변수명 받아온거
		System.out.println(id);
		
		//DB접속
		MemberDAO memberDAO = MemberDAO.getInstance();
		boolean exist = memberDAO.isCheckId(id);
		
		//응답
		request.setAttribute("exist", exist);
		return "/member/checkId.jsp"; 
			
		}
	}
	
