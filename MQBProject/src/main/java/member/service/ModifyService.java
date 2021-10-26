package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

//modifyForm 에서 수정한정보를 디비에 업데이트 하기위해 DAO 와 접근을 한다.
public class ModifyService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		//데이터
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2" );
		String tel3 = request.getParameter("tel3" );
		String zipcode = request.getParameter("zipcode" );
		String addr1 = request.getParameter("addr1" );
		String addr2 = request.getParameter("addr2" );
		
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setZipcode(zipcode);
		memberDTO.setAddr1(addr1);
		memberDTO.setAddr2(addr2);
		
		
		//디비
		MemberDAO memberDAO = MemberDAO.getInstance(); //싱글톤
		memberDAO.modifyService(memberDTO);//호출
		
		
		return "/member/modify.jsp";
	}

}
