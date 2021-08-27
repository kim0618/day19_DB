package day19_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClass {
	private String url;
	private String id;
	private String pwd;
	private Connection con;
	
	public DBClass() {
		try {
			// java에서 oracle에 연결할 수 있게끔 도와주는 라이브러리를 등록하는 것 밑에
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url = "jdbc:oracle:thin:@localhost:1521:xe";
			id = "jinsung";
			pwd = "1234";
			con = DriverManager.getConnection(url,id,pwd);	// 연결된 객체(2번)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*
	 1. 드라이브 로드(오라클 기능 사용)
	 2. 연결된 객체를 얻어온다
	 3. 연결된 객체를 이용해서 명령어(쿼리문)을 전송할 수 있는 전송 객체를 얻어온다.
	 4. 전송 객체를 이용해서 데이터베이스에 전송후 결과를 얻어온다.
	 5. 얻어온 결과는 int 또는 ResultSet으로 받는다.
	 */
	public ArrayList<StudentDTO> getUsers(){	// 저장된 데이터 불러오기 위한 메소드
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		String sql = "select * from newstr";
		try {// 연결된 객체를 명령어를 전송할수있는 것(3번) 밑에
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();	// ResultSet은 select에 쓴다
			while(rs.next()) {	// rs.next()는 다음값이 있으면 true 라는 뜻
				StudentDTO dto = new StudentDTO();
				dto.setStNum(rs.getString("id"));	// dto에 setStNum에 오라클의 id값을 넣어준다 
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);	// list에 dto 저장	// 다음값이 없을때까지 반복해서 모든값을 보여줌
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	//1번째 방법
	public int saveData(String stNum, String name, int age) {	// 작성한걸 오라클에 저장하기
		// insert into newstr values('111','홍길동',20);
		String sql = "insert into newstr values('"+stNum+"','"+name+"',"+age+")";
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
		//	ResultSet rs = ps.executeQuery();	//이렇게 써도 상관 없음
			result = ps.executeUpdate();	// 저장 성공시 1을 반환, 실패시 catch이동이나 0반환
		} catch (Exception e) {
			e.printStackTrace();	// 주석하면 오류 안뜸
		}
		return result;
	}
	
	// 2번째 방법
/*	public int saveData02(String stNum, String name, int age) {	// 작성한걸 오라클에 저장하기
		// insert into newstr values('111','홍길동',20);
		String sql = "insert into newstr values(?, ?, ?)";		// ------- ?를 넣어주고
		int result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, stNum);				//-------- 첫번째 ?를 1번이라고 한다. 1번에 저장
			ps.setString(2, name);				//--------
			ps.setInt(3, age);					//--------
			result = ps.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return result;
	}*/
	
	public int delete(String userNum) {
		int result = 0;
	//  delete newstr where id = 'userNum';
		String sql = "delete newstr where id = '"+userNum+"'";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int modify(String stNum, String name, int age) {
		int result = 0;
		// update newstr set name ='홍길동', age=20 where id ='test';
		String sql = "update newstr set name =?, age=? where id =?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, stNum);
			result = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	
	
	
}
