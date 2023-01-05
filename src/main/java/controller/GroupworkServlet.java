package controller;

import model.JobsModel;
import model.TasksModel;
import model.UsersModel;
import service.JobsService;
import service.TasksService;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GroupworkServlet", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-details", "/groupwork-update"})
public class GroupworkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/groupwork":
                getList(req, resp);
                break;

            case "/groupwork-add":
                getAdd(req, resp);
                break;

            case "/groupwork-details":
                getDetails(req, resp);
                break;

            case "/groupwork-update":
                getUpdate(req, resp);
                break;

            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsService jobsService = new JobsService();
        List<JobsModel> jobsModelList = jobsService.getJobsList();
        req.setAttribute("jobsModelList", jobsModelList);

        req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/groupwork-add.html").forward(req, resp);
    }

    private void getDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        JobsService jobsService = new JobsService();
        JobsModel jobsModel = jobsService.getJobById(id);
        req.setAttribute("jobsModel", jobsModel);

        UsersService usersService = new UsersService();
        List<UsersModel> usersModelList = usersService.getUsersListWhereJobId(id);
        req.setAttribute("usersModelList", usersModelList);

        TasksService tasksService = new TasksService();
        List<TasksModel> tasksModelList = tasksService.getTasksListWhereJobId(id);
        req.setAttribute("tasksModelList", tasksModelList);

        int numberOfAllTask = tasksService.countAllTask(tasksModelList);//đếm tổng số task
        int numberOfNewTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 1);//đếm tổng số task chưa bắt đầu
        int numberOfProgressTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 2);//đếm tổng số task đang thực hiện
        int numberOfCompleledTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 3);//đếm tổng số task đã hoàn thành
        req.setAttribute("numberOfAllTask", numberOfAllTask);
        req.setAttribute("numberOfNewTask", numberOfNewTask);
        req.setAttribute("numberOfProgressTask", numberOfProgressTask);
        req.setAttribute("numberOfCompleledTask", numberOfCompleledTask);

        req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
    }

    private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        JobsService jobsService = new JobsService();
        JobsModel jobsModel = jobsService.getJobById(id);
        req.setAttribute("jobsModel", jobsModel);

        req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);
    }
}
