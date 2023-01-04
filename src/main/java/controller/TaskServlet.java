package controller;

import model.JobsModel;
import model.StatusModel;
import model.TasksModel;
import model.UsersModel;
import service.JobsService;
import service.StatusService;
import service.TasksService;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskServlet", urlPatterns = {"/task", "/task-add", "/task-update"})
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/task":
                getList(req, resp);
                break;

            case "/task-add":
                getAdd(req, resp);
                break;

            case "/task-update":
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
        TasksService tasksService = new TasksService();
        List<TasksModel> tasksModelList = tasksService.getTasksList();
        req.setAttribute("tasksModelList", tasksModelList);

        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsService jobsService = new JobsService();
        List<JobsModel> jobsModelList = jobsService.getJobsList();
        req.setAttribute("jobsModelList", jobsModelList);

        UsersService usersService = new UsersService();
        List<UsersModel> usersModelList = usersService.getUsersList();
        req.setAttribute("usersModelList", usersModelList);

        req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
    }

    private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        TasksService tasksService = new TasksService();
        TasksModel tasksModel = tasksService.getTaskById(id);
        req.setAttribute("tasksModel", tasksModel);

        JobsService jobsService = new JobsService();
        List<JobsModel> jobsModelList = jobsService.getJobsList();
        req.setAttribute("jobsModelList", jobsModelList);

        UsersService usersService = new UsersService();
        List<UsersModel> usersModelList = usersService.getUsersList();
        req.setAttribute("usersModelList", usersModelList);

        StatusService statusService = new StatusService();
        List<StatusModel> statusModelList = statusService.getStatusList();
        req.setAttribute("statusModelList", statusModelList);

        req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
    }
}
