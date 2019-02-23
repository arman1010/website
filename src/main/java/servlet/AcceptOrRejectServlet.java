package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/acceptOrReject")
public class AcceptOrRejectServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String userId = req.getParameter("userId");
        String action = req.getParameter("action");
        int toId = Integer.parseInt(userId);
        if (user != null && action.equals("accept")) {
            userManager.addFriend(user.getId(), toId);
            userManager.removeRequest(user.getId(),toId);
            resp.sendRedirect("/user/home");
        } else if (user != null && action.equals("reject")) {
            userManager.removeRequest(user.getId(),toId);
            resp.sendRedirect("/user/home");
        }else{
            resp.sendRedirect("/index.jsp");
        }
    }
}
