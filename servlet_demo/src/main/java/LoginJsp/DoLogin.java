package LoginJsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/jsplogin/dologin")
public class DoLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && !"".equals(username) && "123456".equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("name",username);
            req.getRequestDispatcher("/jsp_login/index.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/jsp_login/login_fail.jsp").forward(req,resp);
        }
    }
}
