package repository;

import connection.JDBCConnection;
import model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    /**
     * phương thức tìm user theo email và password
     *
     * @param email    (tham số email do người dùng nhập)
     * @param password (tham số password do người dùng nhập)
     * @return (trả về đối tượng UsersModel nếu tìm thấy, nếu không thì trả về null)
     */
    public UsersModel getUserByEmailAndPassword(String email, String password) {
        final String QUERY = "SELECT * FROM users u WHERE u.email = ? AND u.password = ?";

        UsersModel usersModel = null;
        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {//nếu tìm thấy user
                usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(null);
                usersModel.setFullname(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setRoleId(resultSet.getInt("role_id"));
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getUserByEmailAndPassword', " + ex.getMessage());
            ex.printStackTrace();
        }
        return usersModel;
    }

    /**
     * phương thức thêm user vào Database
     *
     * @param email    (tham số email của user muốn thêm)
     * @param password (tham số password của user muốn thêm)
     * @param fullname (tham số fullname của user muốn thêm)
     * @param avatar   (tham số avatar của user muốn thêm)
     * @param roleId   (tham số roleId của user muốn thêm)
     * @return (trả về 1 nếu thêm thành công, nếu không thì trả về 0)
     */
    public int addUser(String email, String password, String fullname, String avatar, int roleId) {
        final String QUERY = "INSERT INTO users(email,password,fullname,avatar,role_id) VALUES (?,?,?,?,?)";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setString(4, avatar);
            statement.setInt(5, roleId);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'addUser', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }
}
