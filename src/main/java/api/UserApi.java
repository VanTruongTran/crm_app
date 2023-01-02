package api;

import com.google.gson.Gson;
import payload.ResponseData;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserApi", urlPatterns = {"/api/user-add", "/api/user-delete"})
public class UserApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/api/user-add":
                postAdd(req, resp);
                break;

            case "/api/user-delete":
                postDetele(req, resp);
                break;

            default:
                break;
        }
    }

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String avatar = req.getParameter("avatar");
        int roleId = Integer.parseInt(req.getParameter("roleId"));

        UsersService usersService = new UsersService();
        boolean success = usersService.addUser(email, password, fullname, avatar, roleId);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }

    private void postDetele(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int userId = Integer.parseInt(req.getParameter("id"));

        UsersService usersService = new UsersService();
        boolean success = usersService.deleteUserById(userId);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(json);
        printWriter.flush();
    }
}
