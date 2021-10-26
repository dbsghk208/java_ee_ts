package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

//자바(.java)클래스로서 진째 수행하는 역할은 service 클래스가한다. 
//부모 클래스인 인터페이스가 요청을 받고 인테페이스에서 자바클래스는 implememts로 받아서 실제 역할을 수행한다.(추상메소드,클래스명 통일위해)
public class WriteFormService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		request.setAttribute("display", "/member/writeForm.jsp");
		return "/index.jsp"; // 이제 이걸 포워드 시켜주는거라서 서블릿 주소만 보이고 jsp 결과가 보임
	}

}
