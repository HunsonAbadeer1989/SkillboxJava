package analyzer;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DBConnection
{
    private static Connection connection;

    private static String dbName = "task14";
    private static String dbUser = "root";
    private static String dbPass = "testtest";

    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection()
    {
        if(connection == null)
        {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                    "?user=" + dbUser + "&password=" + dbPass + "&useSSL=false&serverTimezone=UTC");
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "UNIQUE KEY name_date(name(50), birthDate))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
//        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
//                "VALUES" + insertQuery.toString() +
//                "ON DUPLICATE KEY UPDATE `count`=`count` + 1";
//        analyzer.DBConnection.getConnection().createStatement().execute(sql);

        insertQuery.insert(0, "INSERT INTO voter_count(name, birthDate, `count`) VALUES");
        insertQuery.append(" ON DUPLICATE KEY UPDATE `count`=`count`+1");
        Set<Future<?>> futures = new HashSet<>();
        ExecutorService service = Executors.newSingleThreadExecutor();
        futures.add(service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    DBConnection.getConnection().createStatement().execute(insertQuery.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }));
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        insertQuery.setLength(0);
        service.shutdown();
    }

    public static void countVoter(String name, String birthDay) throws SQLException
    {
        birthDay = birthDay.replace('.', '-');
        boolean isStart = insertQuery.length() == 0;
        insertQuery.append(isStart ? "" : ",").append("('").append(name).append("', '").append(birthDay).append("', 1)");
        if (insertQuery.length() > 2_140_000) {
            executeMultiInsert();
        }

        // Home work 14.12
//        String sql = "SELECT id FROM voter_count WHERE birthDate='" + birthDay + "' AND name='" + name + "'";
//        ResultSet rs = analyzer.DBConnection.getConnection().createStatement().executeQuery(sql);
//
//        if(!rs.next())
//        {
//            analyzer.DBConnection.getConnection().createStatement()
//                    .execute("INSERT INTO voter_count(name, birthDate, `count`) VALUES('" +
//                            name + "', '" + birthDay + "', 1)");
//        }
//        else {
//            Integer id = rs.getInt("id");
//            analyzer.DBConnection.getConnection().createStatement()
//                    .execute("UPDATE voter_count SET `count`=`count`+1 WHERE id=" + id);
//        }
//        rs.close();
    }

    public static void printVoterCounts() throws SQLException
    {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while(rs.next())
        {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }
}