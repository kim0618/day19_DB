package day19_DB;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		DBClass db = new DBClass();
		
		Scanner input = new Scanner(System.in);
		int num;
		
		while(true) {
			System.out.println("1.등록 2.전체보기 3.삭제 4. 수정");
			num = input.nextInt();
			switch(num) {
			case 1:
				System.out.println("학번 입력 : ");
				String stNum = input.next();
				System.out.println("이름 입력 : ");
				String name = input.next();
				System.out.println("나이 입력 : ");
				int age = input.nextInt();
				int result = db.saveData(stNum, name, age);
				if(result == 1) {
					System.out.println("성공 저장");
				}else {
					System.out.println("동일한 아이디 존재");
				}
				break;
			case 2:
				ArrayList<StudentDTO>list = db.getUsers(); // db의 모든 내용을 list에 저장
				if(list.size()==0) {				//저장된 값이 없으면 밑에 아니면 전체내용보기
					System.out.println("저장된 데이터가 없습니다");
				}else {
					for(int i=0; i<list.size();i++) {
						System.out.println("학번 : "+list.get(i).getStNum());
						System.out.println("이름 : "+list.get(i).getName());
						System.out.println("나이 : "+list.get(i).getAge());
						System.out.println("-------------------------");
					}
				}
				break;
			case 3:
				System.out.println("삭제 학번 입력 : ");
				String userNum = input.next();
				int re = db.delete(userNum);
				if(re == 1) {
					System.out.println("삭제 완료");
				}else {
					System.out.println("해당 학번은 존재하지 않습니다.");
				}
				break;
			case 4:
				System.out.println("아이디 입력 : ");
				String stNum1= input.next();
				System.out.println("수정할 이름 입력 : ");
				String name1 = input.next();
				System.out.println("수정할 나이 입력 : ");
				int age1 = input.nextInt();
				if(db.modify(stNum1,name1,age1) == 1) {
					System.out.println("수정 완료");
				}else {
					System.out.println("해당 아이디는 존재하지 않습니다.");
				}
				break;
			}
		}
		
		
		
		
		
		
		
	}
	
}
