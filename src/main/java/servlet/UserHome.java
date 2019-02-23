package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/home")
public class UserHome extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.setAttribute("allUsers", userManager.getAllUsers());
            req.setAttribute("allFriend", userManager.getAllFriends(user.getId()));
            req.setAttribute("allRequest", userManager.getAllRequests(user.getId()));
            req.getRequestDispatcher("/WEB-INF/user/home.jsp").forward(req, resp);
        }
    }
}
