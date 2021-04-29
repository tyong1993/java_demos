package LoginOrigin;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(value = "/originlogin/dologin")
public class DoLogin implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        String contextPath = servletRequest.getServletContext().getContextPath();
        servletResponse.getWriter().write("<html>");
        servletResponse.getWriter().write("<head>");
        servletResponse.getWriter().write("</head>");
        servletResponse.getWriter().write("<body>");
        if(username != null && !"".equals(username) && "123456".equals(password)){
            servletResponse.getWriter().write("<h3>登录成功,欢迎管理员:"+username+"</h3>");
        }else{
            servletResponse.getWriter().write("<a href='"+contextPath+"/originlogin/login'>登录失败,请重新登录</a>");
        }
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
