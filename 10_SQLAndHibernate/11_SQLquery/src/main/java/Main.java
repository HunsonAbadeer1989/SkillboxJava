import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?" +
                "useUnicode=true&serverTimezone=Europe/Moscow&characterEncoding=UTF-8";
        String user = "root";
        String password = "testtest";
        try {

            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery("SELECT c.name AS 'Название курса'," +
                            "COUNT(sub.subscription_date)/12 AS 'Среднее количество покупок в месяц'  " +
                            "FROM Courses c " +
                            "JOIN Subscriptions sub ON c.id=sub.course_id " +
                            "GROUP BY c.name  " +
                            "ORDER BY c.name");
            System.out.println("Название курса\t\tСреднее количество покупок в месяц");
            while(resultSet.next()){
                String nameString = resultSet.getString("Название курса");
                String monthSubString = resultSet.getString("Среднее количество покупок в месяц");
                System.out.printf("%s \t-\t %s\n", nameString, monthSubString);
            }

            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
    }
}
