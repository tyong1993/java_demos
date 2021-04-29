package LoginOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/originlogin/login")
public class Login implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String contextPath = servletRequest.getServletContext().getContextPath();
        servletResponse.getWriter().write("<html>");
        servletResponse.getWriter().write("<head>");
        servletResponse.getWriter().write("</head>");
        servletResponse.getWriter().write("<body>");
        servletResponse.getWriter().write("<form action='"+contextPath+"/originlogin/dologin' method='post'>");
        servletResponse.getWriter().write("账号:<input name='username' />"+"<br/>");
        servletResponse.getWriter().write("密码:<input name='password' />"+"<br/>");
        servletResponse.getWriter().write("<button>登录</button>"+"<br/>");
        servletResponse.getWriter().write("<span>密码:123456</span>");
        servletResponse.getWriter().write("</form>");
        servletResponse.getWriter().write("</body>");
        servletResponse.getWriter().write("</html>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
