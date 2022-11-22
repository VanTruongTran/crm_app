package controller;

import model.RolesModel;
import service.RolesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleServlet", urlPatterns = {"/role", "/role-add", "/role-update"})
public class RoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/role":
                getList(req, resp);
                break;

            case "/role-add":
                getAdd(req, resp);
                break;

            case "/role-update":
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
        RolesService rolesService = new RolesService();
        List<RolesModel> rolesModelList = rolesService.getRolesList();
        req.setAttribute("rolesModelList", rolesModelList);

        req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
    }

    private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/role-add.html").forward(req, resp);
    }

    private void getUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        RolesService rolesService = new RolesService();
        RolesModel rolesModel = rolesService.getRoleById(id);
        req.setAttribute("rolesModel", rolesModel);

        req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
    }
}
