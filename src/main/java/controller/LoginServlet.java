package controller;

import model.UsersModel;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UsersService usersService = new UsersService();
        UsersModel usersModel = usersService.getUserByEmailAndPassword(email, password);

        if (usersModel != null) {//nếu tìm thấy user
            //tạo thuộc tính lưu thông tin user trong session
            HttpSession session = req.getSession();
            session.setAttribute("usersModel", usersModel);
            session.setMaxInactiveInterval(600);//thiết lập thời gian đăng nhập

            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else {//nếu không tìm thấy user
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('LOG IN UNSUCCESSFUL');");
            printWriter.println("location='" + req.getContextPath() + "/login';");
            printWriter.println("</script>");
        }
    }
}
