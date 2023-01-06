package controller;

import model.StatusModel;
import model.TasksModel;
import model.UsersModel;
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

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile", "/profile-edit"})
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/profile":
                getList(req, resp);
                break;

            case "/profile-edit":
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
        int id = Integer.parseInt(req.getParameter("id"));

        UsersService usersService = new UsersService();
        UsersModel usersModel = usersService.getUserById(id);
        req.setAttribute("usersModel", usersModel);

        TasksService tasksService = new TasksService();
        List<TasksModel> tasksModelList = tasksService.getTasksListWhereUserId(id);
        req.setAttribute("tasksModelList", tasksModelList);

        int numberOfAllTask = tasksService.countAllTask(tasksModelList);//đếm tổng số task
        int numberOfNewTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 1);//đếm tổng số task chưa bắt đầu
        int numberOfProgressTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 2);//đếm tổng số task đang thực hiện
        int numberOfCompleledTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 3);//đếm tổng số task đã hoàn thành
        req.setAttribute("numberOfAllTask", numberOfAllTask);
        req.setAttribute("numberOfNewTask", numberOfNewTask);
        req.setAttribute("numberOfProgressTask", numberOfProgressTask);
        req.setAttribute("numberOfCompleledTask", numberOfCompleledTask);

        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }

    private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        TasksService tasksService = new TasksService();
        TasksModel tasksModel = tasksService.getTaskById(id);
        req.setAttribute("tasksModel", tasksModel);

        StatusService statusService = new StatusService();
        List<StatusModel> statusModelList = statusService.getStatusList();
        req.setAttribute("statusModelList", statusModelList);

        req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
    }
}
