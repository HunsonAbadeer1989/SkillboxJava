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

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String SQLstr = "SELECT c.name AS 'Course name'," +
                    "COUNT(sub.subscription_date)/12 AS 'AVG buy course per month'  " +
                    "FROM Courses c " +
                    "JOIN Subscriptions sub ON c.id=sub.course_id " +
                    "GROUP BY c.name  " +
                    "ORDER BY c.name";

            try {
                ResultSet resultSet =
                        statement.executeQuery(SQLstr);
                System.out.println("Название курса\t\tСреднее количество покупок в месяц");

                while (resultSet.next()) {
                    String nameString = resultSet.getString("Course name");
                    String monthSubString = resultSet.getString("AVG buy course per month");
                    System.out.printf("%s \t-\t %s\n", nameString, monthSubString);
                }
            }
            catch (Exception e){
                e.getStackTrace();
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
    }
}
