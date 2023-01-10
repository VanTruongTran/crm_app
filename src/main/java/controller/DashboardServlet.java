package controller;

import model.JobsModel;
import model.TasksModel;
import service.JobsService;
import service.TasksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsService jobsService = new JobsService();
        List<JobsModel> jobsModelList = jobsService.getJobsList();
        req.setAttribute("jobsModelList", jobsModelList);

        TasksService tasksService = new TasksService();
        List<TasksModel> tasksModelList = tasksService.getTasksList();
        req.setAttribute("tasksModelList", tasksModelList);

        int numberOfAllTask = tasksService.countAllTask();//đếm tổng số task
        int numberOfNewTask = tasksService.countAllTaskWhereStatusId(1);//đếm tổng số task chưa bắt đầu
        int numberOfProgressTask = tasksService.countAllTaskWhereStatusId(2);//đếm tổng số task đang thực hiện
        int numberOfCompleledTask = tasksService.countAllTaskWhereStatusId(3);//đếm tổng số task đã hoàn thành
        req.setAttribute("numberOfAllTask", numberOfAllTask);
        req.setAttribute("numberOfNewTask", numberOfNewTask);
        req.setAttribute("numberOfProgressTask", numberOfProgressTask);
        req.setAttribute("numberOfCompleledTask", numberOfCompleledTask);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
