import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PollsWithDB
{
    public static void main(String[] args)
    {
        try
        {
            String url = "jdbc:mysql://127.0.0.1:3306/db_cars";
            String user = "root";
            String password = "!yojulab*";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("DB연결 성공\n");
            
            Statement statement = connection.createStatement();
            String query = "";
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
