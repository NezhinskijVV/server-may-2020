package dao;

import model.User;
import utils.Props;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    public static final String DB_URL = Props.getValue("db.url");
    public static final String DB_LOGIN = Props.getValue("db.user");
    public static final String DB_PASSWORD = Props.getValue("db.password");

    @Override
    public User findByName(String name) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
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