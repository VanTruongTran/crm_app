package api;

import com.google.gson.Gson;
import payload.ResponseData;
import service.RolesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RoleApi", urlPatterns = {"/api/role-add", "/api/role-delete", "/api/role-update"})
public class RoleApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/api/role-add":
                postAdd(req, resp);
                break;

            case "/api/role-delete":
                postDelete(req, resp);
                break;

            case "/api/role-update":
                postUpdate(req, resp);
                break;

            default:
                break;
        }
    }

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        RolesService rolesService = new RolesService();
        boolean success = rolesService.addRole(name, description);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

    private void postDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));

        RolesService rolesService = new RolesService();
        boolean success = rolesService.deleteRoleById(id);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

    private void postUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        RolesService rolesService = new RolesService();
        boolean success = rolesService.updateRole(id, name, description);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }
}
