package terminalpolls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            String query3 = "";
            String query2 = "";
                query3 = "select questions\n" + //
                                    "from questions as ques";
                            ResultSet resultSet = statement.executeQuery(query3);
                            while (resultSet.next()) {
                                System.out.println(resultSet.getString("questions"));
                                query2 = "SELECT choice\n" + //
                                        "FROM choice;";
                                statement = connection.createStatement();
                                ResultSet resultSet2 = statement.executeQuery(query2);
                                while (resultSet2.next()) {
                                    System.out.print(resultSet2.getString("choice") + " ");
                                }
                                System.out.println();
                                System.out.print("답: ");
                                int answer = scanner.nextInt();
                }
            }
            catch (Exception e)
            {

            }
    }
}