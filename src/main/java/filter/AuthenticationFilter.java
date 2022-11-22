package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String servletPath = req.getServletPath();
        HttpSession session = req.getSession();
        Object objUsersModel = session.getAttribute("usersModel");

        if (objUsersModel != null) {//đã đăng nhập
            if (servletPath.equals("/login")) {//gửi request đến trang login
                resp.sendRedirect(req.getContextPath() + "/dashboard");
            } else {//không gửi request đến trang login
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {//chưa đăng nhập
            if (servletPath.equals("/login")) {//gửi request đến trang login
                filterChain.doFilter(servletRequest, servletResponse);
            } else {//không gửi request đến trang login
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }
}
