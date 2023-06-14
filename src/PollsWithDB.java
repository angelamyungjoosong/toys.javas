import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class PollsWithDB
{
    public static void main(String[] args)
    {
        try
        {
            String url = "jdbc:mysql://127.0.0.1:3306/db_polls";
            String user = "root";
            String password = "!yojulab";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");

            Statement statement = connection.createStatement();
            String query = "";

            // 작업 키 입력
            Scanner scanner = new Scanner(System.in);
            String workKey = "A";
            while (!workKey.equals("E") || !workKey.equals("Exit")) {
                System.out.print("선택입력 : ");
                workKey = scanner.nextLine();
                if(workKey.equals("P") || workKey.equals("Poll")) {
                    System.out.println("- 설문자 가능 명단(가입 완료)");
                    query = "SELECT T_RESP.RESPONDENTS\n" + //
                            "FROM respondents AS T_RESP\n" + //
                            ";";
                    ResultSet resultSet = statement.executeQuery(query);
                    int number = 1;
                    HashMap<String, String> respondents = new HashMap();
                    while (resultSet.next()) {
                        System.out.print(number + ". " +
                            resultSet.getString("RESPONDENTS") +" ");
                            // respondents.put(String.valueOf(number), resultSet.getString("RESPONDENTS"));
                            number = number +1;
                    }
                    System.out.println();
                    System.out.println("-설문자 번호 입력 : ");
                    int RespNumber = scanner.nextInt();
                    query = "SELECT COUNT(*) AS CNT\n" + //
                            "FROM respondents AS T_RESP";
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                    while (RespNumber > resultSet.getInt("CNT")) {
                        System.out.println("-Error- 확인 후 입력 필요");
                        RespNumber = scanner.nextInt();
                    } System.out.println("-- 설문 시작");

                    // Poll contents example
                    Statement statement2 = connection.createStatement();
                    String query2 = "";
                    
                    }
                }
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
