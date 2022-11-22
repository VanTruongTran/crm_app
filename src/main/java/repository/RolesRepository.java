package repository;

import connection.JDBCConnection;
import model.RolesModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesRepository {
    /**
     * phương thức thêm role vào Database
     *
     * @param name        (tham số name do người dùng nhập)
     * @param description (tham số description do người dùng nhập)
     * @return (trả về số record được thêm vào Database)
     */
    public int addRole(String name, String description) {
        final String QUERY = "INSERT INTO roles(name,description) VALUES (?,?)";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, name);
            statement.setString(2, description);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'addRole', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * phương thức lấy danh sách role từ Database
     *
     * @return (trả về danh sách đối tượng RolesModel)
     */
    public List<RolesModel> getRolesList() {
        final String QUERY = "SELECT * FROM roles";
        List<RolesModel> rolesModelList = new ArrayList<RolesModel>();

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {//nếu có record trong kết quả trả về
                RolesModel rolesModel = new RolesModel();
                rolesModel.setId(resultSet.getInt("id"));
                rolesModel.setName(resultSet.getString("name"));
                rolesModel.setDescription(resultSet.getString("description"));

                rolesModelList.add(rolesModel);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getRolesList', " + ex.getMessage());
            ex.printStackTrace();
        }
        return rolesModelList;
    }

    /**
     * phương thức xóa role trong Database dựa trên id
     *
     * @param id (tham số id của role cần xóa)
     * @return (trả về số record bị xóa khỏi Database)
     */
    public int deleteRoleById(int id) {
        final String QUERY = "DELETE FROM roles r WHERE r.id = ?";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'deleteRole', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * phương thức trả về role dựa trên id
     *
     * @param id (tham số id do người dùng nhập)
     * @return (trả về đối tượng role nếu tìm thấy, nếu không thì trả về null)
     */
    public RolesModel getRoleById(int id) {
        final String QUERY = "SELECT * FROM roles r WHERE r.id = ?";

        RolesModel rolesModel = null;
        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rolesModel = new RolesModel();
                rolesModel.setId(resultSet.getInt("id"));
                rolesModel.setName(resultSet.getString("name"));
                rolesModel.setDescription(resultSet.getString("description"));
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getRoleById', " + ex.getMessage());
            ex.printStackTrace();
        }
        return rolesModel;
    }

    /**
     * phương thức cập nhật role trong Database
     *
     * @param id          (tham số id của role cần update)
     * @param name        (tham số name của role cần update)
     * @param description (tham số description của role cần update)
     * @return (trả về 1 nếu role được update thành công, nếu không thì trả về 0)
     */
    public int updateRole(int id, String name, String description) {
        final String QUERY = "UPDATE roles r SET r.name = ?,r.description = ? WHERE r.id = ?";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, id);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'updateRole', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }
}
