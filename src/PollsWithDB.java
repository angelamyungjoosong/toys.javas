import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class PollsWithDB {
    public static HashMap<String, String> map2 = new HashMap<>();
    public static void main(String[] args) {
        try {
            
            String url = "jdbc:mysql://127.0.0.1:3306/db_polls";
            String user = "root";
            String password = "!yojulab*";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");

            Scanner scanner = new Scanner(System.in);
            Statement statement = connection.createStatement();
            String query = "";
            String query2 = "";
            String query3 = "";
            int num = 1;
            int number = 1;
            HashMap<String, String> map1 = new HashMap<>();

            String workKey = "A";
            while (!workKey.equals("E")) {
                System.out.print("-- 선택입력 : ");
                workKey = scanner.nextLine();

                if (workKey.equals("P")) {
                    System.out.println("- 설문자 가능 명단(가입 완료)");
                    query = "SELECT respondents\n" + //
                            "FROM RESPONDENTS";
                    ResultSet resultSet0 = statement.executeQuery(query);
                 
                    HashMap<String, String> resNumberMap = new HashMap<>();
                    while (resultSet0.next()) {
                        System.out.print(number + ". " + resultSet0.getString("RESPONDENTS") + ", ");
                        resNumberMap.put(String.valueOf(number), query);
                        number = number + 1;
                    }

                    int cnt = 0;
                    while (cnt == 0) {
                        System.out.println();
                        System.out.print("- 설문자 번호 입력 : ");
                        int ans = scanner.nextInt();

                        if (ans > 4) {
                            System.out.print("-Error- 확인 후 입력 필요");
                            cnt = 0;
                        } else {
                            System.out.println("--설문시작");
                            cnt = 1;
                            String query4 = "SELECT *\n" + //
                         "FROM questions as ques;";
                            ResultSet resultSet = statement.executeQuery(query4);
                while (resultSet.next())
            {
                System.out.println(resultSet.getString("questions"));
                String questionID = resultSet.getString("questions_id");
                query2 = "SELECT *\n" + //
                         "FROM choice as cho;";
                statement = connection.createStatement();
                ResultSet resultSet2 = statement.executeQuery(query2);
                while (resultSet2.next())
                {
                    System.out.print(resultSet2.getString("choice") + " ");
                    String choiceID = resultSet2.getString("choice_id");
                    map2.put(String.valueOf(num), choiceID);
                    num = num + 1;
                }
                System.out.println();
                System.out.print("답: ");
                ResultSet resultSet3 = statement.executeQuery(query2);
               int answer = scanner.nextInt();
                if (resultSet3.next())
                {
                    map1.put(questionID, map2.get(String.valueOf(answer)));
                }
                System.out.println();
                            }
                        }
                    }

                } else if (workKey.equals("S")) {
                    


                } else if (workKey.equals("E")){
                    System.out.println("----- 설문 종료 ------");
                }


            }
        }
         catch (Exception e) {

            // TODO: handle exception
        
    }
}
}
