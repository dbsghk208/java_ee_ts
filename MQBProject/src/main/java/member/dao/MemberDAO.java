package member.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import member.bean.MemberDTO;
import member.bean.ZipcodeDTO;

public class MemberDAO {
//모든 데이터 관련괸건 마이바티스로 넘겼으니까 커녁션 풀 지우기. 그리고 마이바티스랑 연결될 세션팩토리 생성하기

	private static MemberDAO instance = null;  //dao 1번 생성
	
	private SqlSessionFactory sqlSessionFactory;
	
	public static MemberDAO getInstance() { //싱글톤
		if(instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();//생성
			}
		}
		
		return instance;
	}

	public MemberDAO() {
		try {
			Reader reader=Resources.getResourceAsReader("mybatis-config.xml"); //mybatis-config 를 읽어와라
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader); //sqlSessionFactory 도 인터페이스라 빌더에 의해서 생성이 됨 그리고 내가 원하는 메소드 리더를 빌드라는 메소드를 통해 환결설정을 읽은 리더를 통해서 접근하며 생성.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	//F3번키! 누르면 여기의 메소드로 바로 옴
	public MemberDTO login(String id, String pwd) { //LoginService.java 의 디비작업을 하는데 아이디와 비번의 값을 디비와 비교하기위한 작업시작.
		Map<String,String> map =new HashMap<String,String>(); //마이바티스는 데이터를 두개 못보냄. 그래서 디에이오에서 맵으로 묶는다..맵퍼로 보내주려면 묶어야 한다.
		map.put("id",id);
		map.put("pwd",pwd);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();  //생성
															//여기서 맵퍼로 갈때 하나밖에 데이터가 안가니깐 맵으로 묶어준것
		MemberDTO memberDTO=sqlSession.selectOne("memberSQL.login",map);  //memberMapper.xml 로 갈껀데 그때 네임스페이스 정해준곳에 셀렉트로 갈꺼고 셀렉트에 해당하는 아이디로 갈거니까 그 명칭을 "memberSQL.login" 이라고 칭한것. 
			//memberMapper.xml에 있는 네임스페이스 memberSQL인 애의 login 에 데이터는 map 을 보낼께요
		// 디벨로퍼에서 홍 그리고 111이렇게 데이터 맞는걸 조회하면 1줄이 조회가 된다. 가져올떄는 오브젝트로 온다. 
			//그런데 우리는 한사람것만 담으면 되니까 맴버디티오에 담겠다라고 설정해주면됨.그럼 맴버디티오에 담아온다.
		     // map을 통해 데이터를 보냈고 맴버디티오로 꺼내오겠다 라는 말이 된다.
		sqlSession.close();
		return memberDTO;
	}// login 

	public boolean isCheckId(String id) {
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		MemberDTO memberDTO= sqlSession.selectOne("memberSQL.isCheckId", id); //한사람 분량의 데이터기 때문에  그래고 텍스트로 가져오니깐 불린형으로 안나옴
		sqlSession.close(); //이거없으면 나중에 메모리 빵구남 
		if(memberDTO == null) 
			return false;//아이디 사용가능
		else
			return true; //아이디 사용 불가능
		
	}

	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("sido", sido);
		map.put("sigungu", sigungu);
		map.put("roadname", roadname);
		
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		List<ZipcodeDTO> list = sqlSession.selectList("memberSQL.getZipcodeList",map);
		sqlSession.close();
		
		return list;
	} 
	
	
	
	
	
	
	/*

	public List<ZipcodeDTO> getZipcodeList(String sido, String sigungu, String roadname) {
		List<ZipcodeDTO> list= new ArrayList<ZipcodeDTO>();
		
		String sql= "select * from newzipcode where sido like ? and sigungu like ? and roadname like ?";
		
		try {
			conn= ds.getConnection(); // 위에서 생성해줬으니깐 필요할때마다 꺼내서 접속 하기 
			pstmt=conn.prepareStatement(sql);//생성
			pstmt.setString(1,"%"+sido+"%");
			pstmt.setString(2,"%"+sigungu+"%");
			pstmt.setString(3,"%"+roadname+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { //레이블이 없을때까지 반복해주세요
				ZipcodeDTO zipcodeDTO =new ZipcodeDTO ();
				zipcodeDTO.setZipcode(rs.getString("zipcode")); // 디비 테이블에 생성한 컬럼들을 꺼내면 됨
				zipcodeDTO.setSido(rs.getString("sido"));
				zipcodeDTO.setSigungu(rs.getString("sigungu"));
				zipcodeDTO.setYubmyundong(rs.getString("yubmyundong"));
				zipcodeDTO.setRi(rs.getString("ri"));
				zipcodeDTO.setRoadname(rs.getString("roadname"));
				zipcodeDTO.setBuildingname(rs.getString("buildingname"));
			
				list.add(zipcodeDTO);
				
			}//while
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list=null; // 그래야 에러가 나도 널포인트이셉션을 발생시키니까.
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	//회원정보수정 업데이트
	public void modifyService(MemberDTO memberDTO) {
		
		String sql = "update member set name=?, pwd=?, gender=?, email1=?, email2=?, tel1=?, tel2=?, tel3=?, zipcode=?, addr1=?, addr2=? where id=? ";
				
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getPwd());
			pstmt.setString(3, memberDTO.getGender());
			pstmt.setString(4, memberDTO.getEmail1());
			pstmt.setString(5, memberDTO.getEmail2());
			pstmt.setString(6, memberDTO.getTel1());
			pstmt.setString(7, memberDTO.getTel2());
			pstmt.setString(8, memberDTO.getTel3());
			pstmt.setString(9, memberDTO.getZipcode());
			pstmt.setString(10, memberDTO.getAddr1());
			pstmt.setString(11, memberDTO.getAddr2());
		
			pstmt.executeUpdate();//실행
		} catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}//modifyService
	
	//회원정보 폼 불러오기
	public MemberDTO modifyFormService(String id) {
		
		MemberDTO memberDTO =null;
		String sql= "select * from member where id=? ";
		
		try {
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1,id);
//			pstmt.setString(1, memberDTO.getName());
//			pstmt.setString(2, memberDTO.getPwd());
//			pstmt.setString(3, memberDTO.getGender());
//			pstmt.setString(4, memberDTO.getEmail1());
//			pstmt.setString(5, memberDTO.getEmail2());
//			pstmt.setString(6, memberDTO.getTel1());
//			pstmt.setString(7, memberDTO.getTel2());
//			pstmt.setString(8, memberDTO.getTel3());
//			pstmt.setString(9, memberDTO.getZipcode());
//			pstmt.setString(10, memberDTO.getAddr1());
//			pstmt.setString(11, memberDTO.getAddr2());
			
			rs= pstmt.executeQuery();//실행
			
			if(rs.next()) {
				memberDTO =new MemberDTO();
				memberDTO.setName(rs.getString("name"));
				memberDTO.setPwd(rs.getString("pwd"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setEmail1(rs.getString("email1"));
				memberDTO.setEmail2(rs.getString("email2"));
				memberDTO.setTel1(rs.getString("Tel1"));
				memberDTO.setTel2(rs.getString("Tel2"));
				memberDTO.setTel3(rs.getString("Tel3"));
				memberDTO.setZipcode(rs.getString("zipcode"));
				memberDTO.setAddr1(rs.getString("addr1"));
				memberDTO.setAddr2(rs.getString("addr2"));
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return memberDTO;
	}
*/	
	
}



















