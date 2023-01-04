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

@WebServlet(name = "TaskApi", urlPatterns = {"/api/task-add"})
public class TaskApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/api/task-add":
                postAdd(req, resp);
                break;

            default:
                break;
        }
    }

    private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        int userId = Integer.parseInt(req.getParameter("userId"));
        int jobId = Integer.parseInt(req.getParameter("jobId"));
        int statusId = 1;

        TasksService tasksService = new TasksService();
        boolean success = tasksService.addTask(name, startDate, endDate, userId, jobId, statusId);

        ResponseData responseData = new ResponseData(success, null, null);
        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(json);
        printWriter.flush();
    }
}
