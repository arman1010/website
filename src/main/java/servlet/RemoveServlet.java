package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/remove")
public class RemoveServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String userId = req.getParameter("userId");
        int id = Integer.parseInt(userId);
        if (user != null) {
            userManager.removeFriend(user.getId(), id);
            resp.sendRedirect("/user/home");
        }
    }
}
