package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

//회원정보수정폼 자바
//원래 등록되어있던 회원정보를 꺼내야되서 DAO 접근. 폼에 입력될 값들은 자동적으로 입력되게 해야하기 때문에
public class ModifyFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		//데이터
		 HttpSession session =request.getSession();//세션생성
		 String id =(String)session.getAttribute("memId");
		
		 //디비 접속
		 MemberDAO memberDAO= MemberDAO.getInstance();//싱글톤
		 MemberDTO memberDTO = memberDAO.modifyFormService(id);
		

		 session.setAttribute("memName",memberDTO.getName());
		 session.setAttribute("memName",memberDTO.getId());
		 session.setAttribute("memPwd", memberDTO.getPwd());
		 session.setAttribute("memGender", memberDTO.getGender());
		 session.setAttribute("memEmail1", memberDTO.getEmail1());
		 session.setAttribute("memEmail2", memberDTO.getEmail2());
		 session.setAttribute("memTel1", memberDTO.getTel1());
		 session.setAttribute("memTel2", memberDTO.getTel2());
		 session.setAttribute("memTel3", memberDTO.getTel3());
		 session.setAttribute("memZipcode", memberDTO.getZipcode());
		 session.setAttribute("memAddr1", memberDTO.getAddr1());
		 session.setAttribute("memAddr2", memberDTO.getAddr2());
		 
		 return "/member/modifyForm.jsp";
	}

}
