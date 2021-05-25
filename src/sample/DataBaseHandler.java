package sample;
import java.sql.*;

public class DataBaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://"+dbHost+":"
                +dbPort+"/"+dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO "+Const.USER_TABLE+"("+Const.USERS_LOGIN+","+Const.USERS_PASSWORD+")"+ "VALUES(?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1,user.getLogin());
        prSt.setString(2,user.getPassword());

        prSt.executeUpdate();
    }
    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT * FROM "+Const.USER_TABLE+" WHERE "+Const.USERS_LOGIN+"=? AND "+ Const.USERS_PASSWORD+"=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1,user.getLogin());
        prSt.setString(2,user.getPassword());

        resSet = prSt.executeQuery();
        return resSet;
    }
}
