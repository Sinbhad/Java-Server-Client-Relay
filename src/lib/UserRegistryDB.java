package lib;

import java.sql.*;

public class UserRegistryDB {
    //Holds the location of the database
    private static final String URL = "jdbc:sqlite:user_registry.db";

    /**
     * Create a database if one is not found at the URL
     */
    public void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS UserRegistry (ip TEXT PRIMARY KEY, UserName TEXT);";

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
                statement.execute(createTableSQL);

        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    /**
     * Saves the user's ip address and Username to the database so long as it exists
     * @param ipAddr stores a connected users ip in the database
     * @param userName stores a user's selected name in the database
     */
    public void saveUser(String ipAddr, String userName) {
        String insertSQL = "INSERT INTO UserRegistry(ip, UserName) VALUES(?, ?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, ipAddr);
            preparedStatement.setString(2, userName);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving run: " + e.getMessage());
        }
    }

    /**
     * Display three highest score so long as a database is holding any
     */
    public void printUserRegistry(){
        String selectSQL = "SELECT * FROM UserRegistry ORDER BY UserName DESC";

        try(Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL)){

            System.out.println("Registered Users");
            int i = 1;
            while(resultSet.next()){
                String userName = resultSet.getString("UserName");
                int ipAddr = resultSet.getInt("ip");
                System.out.println(i + ": " + userName + " - " + ipAddr);
                i++;
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving scores: " + e.getMessage());
        }
    }
}