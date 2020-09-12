package dao;

import model.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    public static final String url = "jdbc:MySQL://localhost:3306/my_schema_spt_2020?serverTimezone=UTC";
    public static final String login = "root";
    public static final String password = "12345678";

    @Override
    public User findByName(String name) {
        try (Connection connection = DriverManager.getConnection(url, login, password);
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select u.login, u.password from my_schema_spt_2020.users u where u.login = ?")) {

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                System.out.println("Нашли пользователя: " + login + " " + password);
                return new User(login, password);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}


//1. Url, login и password к БД
// 2. Драйвер в зависимостях
// 3. JDBC (встроен внутрь JDK)
// 4. Написать свой DAO



//HW: CRUD