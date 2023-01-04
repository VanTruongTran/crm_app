package repository;

import connection.JDBCConnection;
import model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    /**
     * phương thức lấy danh sách status từ Database
     *
     * @return (trả về danh sách status)
     */
    public List<StatusModel> getStatusList() {
        final String QUERY = "SELECT * FROM status";
        List<StatusModel> statusModelList = new ArrayList<StatusModel>();

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                StatusModel statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));

                statusModelList.add(statusModel);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getStatusList', " + ex.getMessage());
            ex.printStackTrace();
        }
        return statusModelList;
    }

    /**
     * phương thức trả về status dựa trên id
     *
     * @param id (tham số id của status)
     * @return (trả về đối tượng StatusModel nếu tìm thấy, nếu không thì trả về null)
     */
    public StatusModel getStatusById(int id) {
        final String QUERY = "SELECT * FROM status s WHERE s.id = ?";
        StatusModel statusModel = null;

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getStatusById', " + ex.getMessage());
            ex.printStackTrace();
        }
        return statusModel;
    }
}
