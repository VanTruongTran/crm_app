package repository;

import connection.JDBCConnection;
import model.JobsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JobsRepository {
    /**
     * phương thức lấy danh sách job từ Database
     *
     * @return (trả về danh sách chứa các job)
     */
    public List<JobsModel> getJobsList() {
        final String QUERY = "SELECT * FROM jobs";
        List<JobsModel> jobsModelList = new ArrayList<JobsModel>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JobsModel jobsModel = new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));

                if (resultSet.getDate("start_date") != null) {
                    jobsModel.setStartDate(sdf.format(resultSet.getDate("start_date")));
                }

                if (resultSet.getDate("end_date") != null) {
                    jobsModel.setEndDate(sdf.format(resultSet.getDate("end_date")));
                }

                jobsModelList.add(jobsModel);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getJobsList', " + ex.getMessage());
            ex.printStackTrace();
        }
        return jobsModelList;
    }

    /**
     * phương thức thêm job vào Database
     *
     * @param name      (tham số name của job)
     * @param startDate (tham số startDate của job)
     * @param endDate   (tham số endDate của job)
     * @return (trả về số record được thêm vào Database)
     */
    public int addJob(String name, String startDate, String endDate) {
        final String QUERY = "INSERT INTO jobs(name,start_date,end_date) VALUES (?,?,?)";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'addJob', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * phương thức xóa job theo id
     *
     * @param id (tham số id của job cần xóa)
     * @return (trả về số record bị xóa khỏi Database)
     */
    public int deleteJobById(int id) {
        final String QUERY = "DELETE FROM jobs j WHERE j.id = ?";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'deleteJobById', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * phương thức trả về job theo id
     *
     * @param id (tham số id của job cần tìm)
     * @return (trả về đối tượng job nếu tìm thấy, nếu không thì trả về null)
     */
    public JobsModel getJobById(int id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        final String QUERY = "SELECT * FROM jobs j WHERE j.id = ?";

        JobsModel jobsModel = null;
        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                jobsModel = new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));

                if (resultSet.getDate("start_date") != null) {
                    jobsModel.setStartDate(sdf.format(resultSet.getDate("start_date")));
                }

                if (resultSet.getDate("end_date") != null) {
                    jobsModel.setEndDate(sdf.format(resultSet.getDate("end_date")));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getJobById', " + ex.getMessage());
            ex.printStackTrace();
        }
        return jobsModel;
    }

    /**
     * phương thức cập nhật job trong Database theo id
     *
     * @param id        (tham số id của job cần cập nhật)
     * @param name      (tham số nam của job cần cập nhật)
     * @param startDate (tham số startDate của job cần cập nhật)
     * @param endDate   (tham số endDate của job cần cập nhật)
     * @return (trả về 1 nếu cập nhật thành công, nếu không thì trả về 0)
     */
    public int updateJobById(int id, String name, String startDate, String endDate) {
        final String QUERY = "UPDATE jobs j SET j.name = ?,j.start_date= ?,j.end_date= ? WHERE j.id= ?";

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, id);

            int result = statement.executeUpdate();

            connection.close();
            return result;
        } catch (SQLException ex) {
            System.out.println("Found error in 'updateJob', " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }
}
