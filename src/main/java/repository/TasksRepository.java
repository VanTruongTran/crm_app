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
}
