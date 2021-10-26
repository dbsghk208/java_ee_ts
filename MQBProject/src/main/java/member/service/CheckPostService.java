package member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

public class CheckPostService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse reponse) throws Throwable {
		
		return "/member/checkPost.jsp";  //창만띄우겠다  디비처리 안함. 전에는 디비에서 가져온 리스트를 처리하고 했는데!!
	}

}
