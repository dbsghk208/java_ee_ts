package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private Map<String, Object> map = new HashMap<String, Object>();  //커맨드프로퍼티즈의 내용을 한번밖에 못 읽으니까(서블릿의 init 특성때문에) 프로퍼티지를 맵에 담아준다.
	   //Map<Key,Value> 이렇게 잡는데 여기서는 = 을 기준으로 나눠갖는다 Key-> /member/login.do    Value->  member.service.LoginService
	   public void init(ServletConfig config) {   //properties 의 경로를 읽어달라  딱한번만 수행하는데  프로퍼티 컨피그의 파일을 읽어오시오 
	      String propertyConfig = config.getInitParameter("propertyConfig");
	      System.out.println("propertyConfig = "+propertyConfig+"\n");
	      
	      FileInputStream fin = null;
	      Properties properties = new Properties();
	      
	      try {
	         fin = new FileInputStream(propertyConfig);
	         properties.load(fin);
	         System.out.println("properties = "+properties);
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }finally{
	         try {
	            fin.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println();
	      
	      Iterator it = properties.keySet().iterator();
	      while(it.hasNext()) {
	         String key = (String)it.next();
	         System.out.println("key = "+key);
	         
	         String className = properties.getProperty(key);
	         System.out.println("className = "+className);
	         
	         try {
	            Class<?> classType = Class.forName(className);
	            Object ob = classType.newInstance();
	            
	            System.out.println("ob = "+ob);
	            
	            map.put(key, ob);  //요청이 들어오면 맵어 실는다.
	            
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (InstantiationException e) {
	            e.printStackTrace();
	         } catch (IllegalAccessException e) {
	            e.printStackTrace();
	         } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	         } 
	         
	         System.out.println();
	      }//while
	      
	   }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		execute(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		execute(request,response);
	}
     //doGet,doPost 방식이든 execute요청이 간다.
	protected void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println();
		
		//한글처리
		if(request.getMethod().equals("POST")) {  //method q방식으로 포스트 방식 으로 얻어오면 한글화하 한다.
			request.setCharacterEncoding("UTF-8");
		}
		
		//http://localhost:8080/mvcmember/member/writeForm.do 요청
		//요청이 들어왔을때 /member/writeForm.do 만큼만 짤라오세요 라는 메소드 -> getServletPath()
		String category = request.getServletPath();  // /member/writeForm.do
	      System.out.println("category = "+category);
	      												//그럼 아까 프로퍼를 맵에 담았으니깐 맵에서 자바파일을 꺼내오라는 얘기
	      CommandProcess commandProcess = (CommandProcess)map.get(category);  //꺼내오세요
	      System.out.println("commandProcess = "+commandProcess);
	      
	      String view = null;
	      try {						//
	         view = commandProcess.requestPro(request, response); //메소드 요청 .그럼 여기서 requestPro 라고 요청을 해준다. 이 메소드는 자바파일과 연결되어서 자바파일에 requestPro 메소드한테 간다. getParameter("") 
	      } catch (Throwable e) {
	         e.printStackTrace();
	      }
	      
	      //forward   
	      RequestDispatcher dispatcher = request.getRequestDispatcher(view);//상대번지  가지고 이제 로그인서비스의 리턴ok 값이 전달된다.
	      dispatcher.forward(request, response);//제어권 넘기기
		
	}
}
