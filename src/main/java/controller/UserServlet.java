package controller;

import model.RolesModel;
import model.UsersModel;
import service.RolesService;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/user", "/user-add", "/user-details"})
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
        req.getRequestDispatcher("/user-details.html").forward(req, resp);
    }
}
