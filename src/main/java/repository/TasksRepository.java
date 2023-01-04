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
}
