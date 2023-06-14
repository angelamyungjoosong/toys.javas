import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class PollsWithDB {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/db_polls";
            String user = "root";
            String password = "!yojulab*";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");

            Statement statement = connection.createStatement();
            String query = "";

            Scanner scanner = new Scanner(System.in);
            String workKey = "A";
            while (!workKey.equals("E")) {
                System.out.print("-- 선택입력 : ");
                workKey = scanner.nextLine();

                if (workKey.equals("P")) {
                    System.out.println("- 설문자 가능 명단(가입 완료)");
                    query = "SELECT respondents\n" + //
                            "FROM RESPONDENTS";
                    ResultSet resultSet = statement.executeQuery(query);
                    int number = 1;
                    HashMap<String, String> resNumberMap = new HashMap<>();
                    while (resultSet.next()) {
                        System.out.print(number + ". " + resultSet.getString("RESPONDENTS") + ", ");
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
                        System.out.print("--설문시작"); 
                        cnt = 1;
                    }}
                }
                
                 else if (workKey.equals("S")) {
                     System.out.print("-- 총 설문자");
                 }
                 else {
                 }

            }
        } catch (Exception e) {

            // TODO: handle exception
        }
    }
}
