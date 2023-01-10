package filter;

import com.google.gson.Gson;
import model.UsersModel;
import payload.ResponseData;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/*"})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String servletPath = ((HttpServletRequest) servletRequest).getServletPath();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Object objUsersModel = session.getAttribute("usersModel");

        if (objUsersModel != null) {//đã đăng nhập
            int roleId = ((UsersModel) objUsersModel).getRolesModel().getId();

            if (servletPath.startsWith("/role") && !servletPath.equals("/role")) { /* SERVLET REQUEST CHECKING */
                //ALL | "/role"
                //ADMIN | "/role-add", "/role-update"
                doCheckServletRequest(servletRequest, servletResponse, filterChain, roleId, 1);

            } else if (servletPath.startsWith("/groupwork") && !servletPath.equals("/groupwork")) {
                //ALL | "/groupwork"
                //LEADER | "/groupwork-add", "/groupwork-details", "/groupwork-update"
                doCheckServletRequest(servletRequest, servletResponse, filterChain, roleId, 2);

            } else if (servletPath.startsWith("/task") && !servletPath.equals("/task")) {
                //ALL | "/task"
                //LEADER | "/task-add", "/task-update"
                doCheckServletRequest(servletRequest, servletResponse, filterChain, roleId, 2);

            } else if (servletPath.startsWith("/user") && !servletPath.equals("/user")) {
                //ALL | "/user"
                //ADMIN, LEADER | "/user-add", "/user-details", "/user-update"
                doCheckServletRequest(servletRequest, servletResponse, filterChain, roleId, 1, 2);

            } else if (servletPath.startsWith("/api/role")) { /* API REQUEST CHECKING */
                //ADMIN | "/api/role-add", "/api/role-delete", "/api/role-update"
                doCheckApiRequest(servletRequest, servletResponse, filterChain, roleId, 1);

            } else if (servletPath.startsWith("/api/groupwork")) {
                //LEADER | "/api/groupwork-add", "/api/groupwork-delete", "/api/groupwork-update"
                doCheckApiRequest(servletRequest, servletResponse, filterChain, roleId, 2);

            } else if (servletPath.startsWith("/api/task")) {
                //LEADER | "/api/task-add", "/api/task-delete", "/api/task-update"
                doCheckApiRequest(servletRequest, servletResponse, filterChain, roleId, 2);

            } else if (servletPath.startsWith("/api/user")) {
                //ADMIN, LEADER | "/api/user-add", "/api/user-delete", "/api/user-update"
                doCheckApiRequest(servletRequest, servletResponse, filterChain, roleId, 1, 2);

            } else {
                //ALL | "/login"
                //ALL | "/logout"
                //ALL | "/dashboard"
                //ALL | "/profile", "/profile-edit"
                //ALL | "/api/profile-update"
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {//chưa đăng nhập
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * phương thức kiểm tra Servlet request
     *
     * @param servletRequest      (tham số servletRequest)
     * @param servletResponse     (tham số servletResponse)
     * @param filterChain         (tham số filterChain)
     * @param roleId              (tham số roleId của user)
     * @param permittedRoleIdList (tham số danh sách roleId được phép truy cập)
     * @throws IOException      (ngoại lệ IOException)
     * @throws ServletException (ngoại lệ ServletException)
     */
    private void doCheckServletRequest(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, int roleId, int... permittedRoleIdList) throws IOException, ServletException {
        //kiểm tra roleId có nằm trong danh sách roleId được phép hay không
        if (checkRoleId(roleId, permittedRoleIdList)) {//role được phép truy cập
            filterChain.doFilter(servletRequest, servletResponse);
        } else {//role không được phép truy cập
            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('YOU ARE NOT PERMISSION');");
            printWriter.println("location='" + ((HttpServletRequest) servletRequest).getContextPath() + "/dashboard';");
            printWriter.println("</script>");
        }
    }

    /**
     * phương thức kiểm tra API request
     *
     * @param servletRequest      (tham số servletRequest)
     * @param servletResponse     (tham số servletResponse)
     * @param filterChain         (tham số filterChain)
     * @param roleId              (tham số roleId của user)
     * @param permittedRoleIdList (tham số danh sách roleId được phép truy cập)
     * @throws IOException      (ngoại lệ IOException)
     * @throws ServletException (ngoại lệ ServletException)
     */
    private void doCheckApiRequest(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, int roleId, int... permittedRoleIdList) throws IOException, ServletException {
        //kiểm tra roleId có nằm trong danh sách roleId được phép hay không
        if (checkRoleId(roleId, permittedRoleIdList)) {//role được phép truy cập
            filterChain.doFilter(servletRequest, servletResponse);
        } else {//role không được phép truy cập
            servletResponse.setContentType("application/json");
            servletResponse.setCharacterEncoding("UTF-8");

            ResponseData responseData = new ResponseData(false, null, "NOT PERMISSION");
            Gson gson = new Gson();
            String json = gson.toJson(responseData);

            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.print(json);
            printWriter.flush();
        }
    }

    /**
     * phương thức kiểm tra roleId có nằm trong danh sách được truy cập hay không
     *
     * @param roleId              (tham số roleId của user)
     * @param permittedRoleIdList (tham số danh sách roleId được phép truy cập)
     * @return (trả về true nếu roleId được phép truy cập, nếu không thì trả về false)
     */
    private boolean checkRoleId(int roleId, int[] permittedRoleIdList) {
        for (int permittedRoleId : permittedRoleIdList) {
            if (permittedRoleId == roleId) {
                return true;
            }
        }
        return false;
    }
}
