package api;

import com.google.gson.Gson;
import payload.ResponseData;
import service.TasksService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProfileApi", urlPatterns = {"/api/profile-update"})
public class ProfileApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/api/profile-update":
                postUpdate(req, resp);
                break;

            default:
                break;
        }
    }

    private void postUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        int statusId = Integer.parseInt(req.getParameter("statusId"));

        TasksService tasksService = new TasksService();
        boolean success = tasksService.updateTaskStatus(id, statusId);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(json);
        printWriter.flush();
    }
}
