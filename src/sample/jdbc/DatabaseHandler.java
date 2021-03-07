package sample.jdbc;

import sample.User;

import java.sql.*;

public class DatabaseHandler extends Configs {

    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connect = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connect, dbUser, dbPass);

        return connection;
    }

    public void signUpUser(User user) {
        String insert = "insert into " + Const.USER_TABLE + "(" + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD
                + "," + Const.USERS_EMAIL + "," + Const.USERS_PHONE + ","
                + Const.USERS_SEX + ")" + "values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getSex());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;

        String select = "select * from " + Const.USER_TABLE + " where " + Const.USERS_LOGIN + "= ? and "
                + Const.USERS_PASSWORD + "= ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }
}
