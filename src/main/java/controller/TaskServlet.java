package controller;

import model.TasksModel;
import service.TasksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TaskServlet", urlPatterns = {"/task", "/task-add"})
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
        req.getRequestDispatcher("/task-add.html").forward(req, resp);
    }
}
