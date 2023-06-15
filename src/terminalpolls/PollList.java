package terminalpolls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class PollList
{
    public static void main(String[] args)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            String url = "jdbc:mysql://127.0.0.1:3306/db_polls";
            String user = "root";
            String password = "!yojulab*";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");
            
            Statement statement = connection.createStatement();
            String query = "";
            String query2 = "";
            int number = 1;
                query = "select *\n" + //
                        "from questions as ques";
            ResultSet resultSet = statement.executeQuery(query);
            HashMap<String, String> map1 = new HashMap<>();
            HashMap<String, String> map2 = new HashMap<>();
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
                    map2.put(String.valueOf(number), choiceID);
                    number = number + 1;
                }
                System.out.println();
                System.out.print("답: ");
                ResultSet resultSet3 = statement.executeQuery(query2);
                String answer = scanner.nextLine();
                if (resultSet3.next())
                {
                    map1.put(questionID, map2.get(answer));
                }
                System.out.println();
            }
        }
            catch (Exception e)
            {

            }
    }
}