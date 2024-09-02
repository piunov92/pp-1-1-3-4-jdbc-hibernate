package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `pp-1-1-3-4`.`users` (
                      `id` INT NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NULL,
                      `lastName` VARCHAR(45) NOT NULL,
                      `age` TINYINT NOT NULL,
                      PRIMARY KEY (`id`));""");
        } catch (SQLException e) {
            System.err.println("error creating users table" + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `pp-1-1-3-4`.`users`");
        } catch (SQLException e) {
            System.err.println("error dropping table: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";
        String message = "";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            message = String.format("User с именем — %s добавлен в базу данных", name);
        } catch (SQLException e) {
            System.err.println("error saving user: " + e.getMessage());
        }
        System.out.println(message);
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error removing user: " + e.getMessage());;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("error getting all users: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE `pp-1-1-3-4`.`users`";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error cleaning table: " + e.getMessage());
        }
    }
}
