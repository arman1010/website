package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/sendRequest")
public class SendRequestServlet extends HttpServlet {
    UserManager userManager=new UserManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       User user = (User) req.getSession().getAttribute("user");
        String getToId = req.getParameter("toId");
        int toId = Integer.parseInt(getToId);
        if(user!=null) {
            userManager.sendRequest(user.getId(),toId);
            resp.sendRedirect("/user/home");
        }
    }
}
