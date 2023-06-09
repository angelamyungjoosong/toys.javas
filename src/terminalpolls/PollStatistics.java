package terminalpolls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.management.Query;

// -------- 참조 : poll Statistic example -------------
// -- 총 설문자 : 3명
// -- 문항 내에서 최대 갯수 번호
// 1. 교수는 수업 전 강의 목표를 명확히 제시하였습니까?	--> 4
// 2. 강의의 내용은 체계적이고 성의있게 구성되었는가?	--> 2
// 3. 교수는 강의 내용에 대해 전문적 지식이 있었는가?	--> 3
// 4. 강의 진행 속도는 적절하였는가?			--> 4

// -- 답항 중심
// (1)전혀 아니다.	--> 0 
// (2)아니다. 	--> 2
// (3)그렇다. 	--> 1
// (4)매우그렇다.	--> 0

public class PollStatistics {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/db_polls";
            String user = "root";
            String password = "!yojulab*";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");

            Statement statement = connection.createStatement();
            String query = "";

            Scanner scanner = new Scanner(System.in);

    // 여기부터 시작
    System.out.println("- 통계 시작 -");
    
    
    // -- 총 설문자 : 4명 을 출력
        query = "SELECT COUNT(*) AS CNT_RES" +
                "FROM respondents AS T_RES" +
	                "INNER JOIN statistics AS T_STA" +
                    "ON T_RES.RESPONDENTS_ID = T_STA.RESPONDENTS_ID";
    ResultSet resultSet = statement.executeQuery(query) ;
    int res = resultSet.getInt("CNT_RES");
    System.out.println("설문에 참여한 사람 수: " + res );


    // -- 문항 내에서 최대 갯수 번호
    // 1. 교수는 수업 전 강의 목표를 명확히 제시하였습니까?	--> 4
    // 2. 강의의 내용은 체계적이고 성의있게 구성되었는가?	--> 2
    // 3. 교수는 강의 내용에 대해 전문적 지식이 있었는가?	--> 3
    // 4. 강의 진행 속도는 적절하였는가?			--> 4
    // 출력
        
        
        query = "SELECT T_QUE.QUESTIONS, MAX(T_CHO.CHOICE) AS MAX_CHO" +
                "FROM respondents AS T_RES" +
	            "INNER JOIN statistics AS T_STA " +
                "ON T_RES.RESPONDENTS_ID =  T_STA.RESPONDENTS_ID" +
		            "INNER JOIN questions AS T_QUE" +
                    "ON T_QUE.QUESTIONS_ID =  T_STA.QUESTIONS_ID" +
			            "INNER JOIN choice T_CHO" +
                        "ON  T_CHO.CHOICE_ID = T_STA.CHOICE_ID" +
                "GROUP BY T_QUE.QUESTIONS";
        Statement statement1 = connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery(query) ;
        while (resultSet1.next()) {
            System.out.println(
                resultSet1.getString("T_QUE.QUESTIONS") + ", " +
                resultSet1.getInt("MAX_CHO"));

        }
    // -- 답항 중심
    // (1)전혀 아니다.	--> 0 
    // (2)아니다. 	--> 2
    // (3)그렇다. 	--> 1
    // (4)매우그렇다.	--> 0
    // 출력
        
        
        query = "SELECT T_CHO.CHOICE, COUNT(*) AS CNT" +
                "FROM choice AS T_CHO" +
		            "INNER JOIN statistics T_STA" +
		            "ON T_CHO.CHOICE_ID = T_STA.CHOICE_ID" +
                "GROUP BY T_CHO.CHOICE_ID" ;
        Statement statement2 = connection.createStatement();
        ResultSet resultSet2 = statement.executeQuery(query) ;
        while (resultSet2.next()) {
            System.out.println(
                resultSet2.getString("T_CHO.CHOICE") + ", " +
                resultSet2.getInt("CNT"));
        }
    
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        System.out.println();
    
    }
    
}


    // 문제점 resultSet1,resultSet2 의 오류
    // 쿼리문은 저의 pc DB기준으로 작성해서 그쪽에서는 작동이 될지 모르겠어요
    // 일단 MY sql 에서는 쿼리문은 문제없이 작동되는거 확인했고, 다만 자바에서 오류를 잡지못해서 작동을 확인을 해볼수가 없었습니다.
    // 제 코드 간단하게 요약하자면 3파트로 나눴고 각각 파트는 주석처리해서 알아보실수 있으시고요
    // 첫번째 코드는 총인원만 출력하면 되기 때문에 테이블 합칠필요없이 카운트만적용했어요
    // 두번째 코드는 가장 많이 선택된 보기를 출력해야 하기 때문에 4개의 테이블을 합치고 MAX로 보기 최대값을 설정했어요
    // 세번째 코드는 보기를 몇개씩 선택했는지를 출력하는것이라 3개의 테이블을 합치고 COUNT를 걸어서 갯수 설정했어요
    
