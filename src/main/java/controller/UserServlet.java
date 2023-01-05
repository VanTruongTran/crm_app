package controller;

import model.RolesModel;
import model.TasksModel;
import model.UsersModel;
import service.RolesService;
import service.TasksService;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/user", "/user-add", "/user-details", "/user-update"})
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/user":
                getList(req, resp);
                break;

            case "/user-add":
                getAdd(req, resp);
                break;

            case "/user-details":
                getDetails(req, resp);
                break;

            case "/user-update":
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
        UsersService usersService = new UsersService();
        List<UsersModel> usersModelList = usersService.getUsersList();
        req.setAttribute("usersModelList", usersModelList);

        req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RolesService rolesService = new RolesService();
        List<RolesModel> rolesModelList = rolesService.getRolesList();
        req.setAttribute("rolesModelList", rolesModelList);

        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    private void getDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));

        UsersService usersService = new UsersService();
        UsersModel usersModel = usersService.getUserById(userId);
        req.setAttribute("usersModel", usersModel);

        TasksService tasksService = new TasksService();
        List<TasksModel> tasksModelList = tasksService.getTasksListWhereUserId(userId);
        req.setAttribute("tasksModelList", tasksModelList);

        int numberOfAllTask = tasksService.countAllTask(tasksModelList);//đếm tổng số task
        int numberOfNewTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 1);//đếm tổng số task chưa bắt đầu
        int numberOfProgressTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 2);//đếm tổng số task đang thực hiện
        int numberOfCompleledTask = tasksService.countAllTaskWhereStatusId(tasksModelList, 3);//đếm tổng số task đã hoàn thành
        req.setAttribute("numberOfAllTask", numberOfAllTask);
        req.setAttribute("numberOfNewTask", numberOfNewTask);
        req.setAttribute("numberOfProgressTask", numberOfProgressTask);
        req.setAttribute("numberOfCompleledTask", numberOfCompleledTask);

        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }

    private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        UsersService usersService = new UsersService();
        UsersModel usersModel = usersService.getUserById(id);
        req.setAttribute("usersModel", usersModel);

        RolesService rolesService = new RolesService();
        List<RolesModel> rolesModelList = rolesService.getRolesList();
        req.setAttribute("rolesModelList", rolesModelList);

        req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
    }
}
