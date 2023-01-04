package repository;

import connection.JDBCConnection;
import model.JobsModel;
import model.StatusModel;
import model.TasksModel;
import model.UsersModel;
import service.JobsService;
import service.StatusService;
import service.UsersService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TasksRepository {
    /**
     * phương thức lấy danh sách task từ Database
     *
     * @return (trả về danh sách task từ Database)
     */
    public List<TasksModel> getTasksList() {
        final String QUERY = "SELECT * FROM tasks";
        List<TasksModel> tasksModelList = new ArrayList<TasksModel>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                TasksModel tasksModel = new TasksModel();
                tasksModel.setId(resultSet.getInt("id"));
                tasksModel.setName(resultSet.getString("name"));

                if (resultSet.getDate("start_date") != null) {
                    tasksModel.setStartDate(sdf.format(resultSet.getDate("start_date")));
                }

                if (resultSet.getDate("end_date") != null) {
                    tasksModel.setEndDate(sdf.format(resultSet.getDate("end_date")));
                }

                UsersService usersService = new UsersService();
                UsersModel usersModel = usersService.getUserById(resultSet.getInt("user_id"));
                tasksModel.setUsersModel(usersModel);

                JobsService jobsService = new JobsService();
                JobsModel jobsModel = jobsService.getJobById(resultSet.getInt("job_id"));
                tasksModel.setJobsModel(jobsModel);

                StatusService statusService = new StatusService();
                StatusModel statusModel = statusService.getStatusById(resultSet.getInt("status_id"));
                tasksModel.setStatusModel(statusModel);

                tasksModelList.add(tasksModel);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getTasksList', " + ex.getMessage());
            ex.printStackTrace();
        }
        return tasksModelList;
    }

    /**
     * phương thức thêm task vào Database
     *
     * @param name      (tham số name của task muốn thêm)
     * @param startDate (tham số startDate của task muốn thêm)
     * @param endDate   (tham số endDate của task muốn thêm)
     * @param userId    (tham số userId của task muốn thêm)
     * @param jobId     (tham số jobId của task muốn thêm)
     * @param statusId  (tham số statusId của task muốn thêm)
     * @return (trả về 1 nếu thêm task thành công, nếu không thì trả về 0)
     */
    public int addTask(String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        final String QUERY = "INSERT INTO tasks(name,start_date,end_date,user_id,job_id,status_id) VALUES (?,?,?,?,?,?)";
        int result = 0;

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, userId);
            statement.setInt(5, jobId);
            statement.setInt(6, statusId);

            result = statement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'addTask', " + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * phương thức xóa task dựa trên id
     *
     * @param id (tham số id của task cần xóa)
     * @return (trả về 1 nếu xóa thành công, nếu không thì trả về 0)
     */
    public int deleteTask(int id) {
        final String QUERY = "DELETE FROM tasks t WHERE t.id = ?";
        int result = 0;

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);
            result = statement.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'deleteTask', " + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * phương thức trả về task dựa trên id
     *
     * @param id (tham số id của task cần tìm)
     * @return (trả về task nếu tìm thấy, nếu không thì trả về null)
     */
    public TasksModel getTaskById(int id) {
        final String QUERY = "SELECT * FROM tasks t WHERE t.id = ?";
        TasksModel tasksModel = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tasksModel = new TasksModel();
                tasksModel.setId(resultSet.getInt("id"));
                tasksModel.setName(resultSet.getString("name"));

                if (resultSet.getDate("start_date") != null) {
                    tasksModel.setStartDate(sdf.format(resultSet.getDate("start_date")));
                }

                if (resultSet.getDate("end_date") != null) {
                    tasksModel.setEndDate(sdf.format(resultSet.getDate("end_date")));
                }

                UsersService usersService = new UsersService();
                UsersModel usersModel = usersService.getUserById(resultSet.getInt("user_id"));
                tasksModel.setUsersModel(usersModel);

                JobsService jobsService = new JobsService();
                JobsModel jobsModel = jobsService.getJobById(resultSet.getInt("job_id"));
                tasksModel.setJobsModel(jobsModel);

                StatusService statusService = new StatusService();
                StatusModel statusModel = statusService.getStatusById(resultSet.getInt("status_id"));
                tasksModel.setStatusModel(statusModel);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'getTaskBy', " + ex.getMessage());
            ex.printStackTrace();
        }
        return tasksModel;
    }

    /**
     * phương thức cập nhật task trong Database
     *
     * @param id        (tham số id của task cần cập nhật)
     * @param name      (tham số name của task cần cập nhật)
     * @param startDate (tham số startDate của task cần cập nhật)
     * @param endDate   (tham số endDate của task cần cập nhật)
     * @param userId    (tham số userId của task cần cập nhật)
     * @param jobId     (tham số jobId của task cần cập nhật)
     * @param statusId  (tham số statusId của task cần cập nhật)
     * @return (trả về 1 nếu cập nhật task thành công, nếu không thì trả về 0)
     */
    public int updateTask(int id, String name, String startDate, String endDate, int userId, int jobId, int statusId) {
        final String QUERY = "UPDATE tasks t SET t.name = ?, t.start_date = ?, t.end_date = ?," +
                " t.user_id = ?, t.job_id = ?, t.status_id = ? WHERE t.id = ?";
        int result = 0;

        try {
            Connection connection = JDBCConnection.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);

            statement.setString(1, name);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.setInt(4, userId);
            statement.setInt(5, jobId);
            statement.setInt(6, statusId);
            statement.setInt(7, id);

            result = statement.executeUpdate();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Found error in 'updateTask', " + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }
}
