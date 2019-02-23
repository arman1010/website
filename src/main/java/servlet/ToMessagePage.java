package servlet;

import manager.MessageManager;
import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/toMessagePage")
public class ToMessagePage extends HttpServlet {
    MessageManager messageManager = new MessageManager();
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String toId = req.getParameter("toId");
        int id = Integer.parseInt(toId);
        if (user != null) {
            messageManager.getMessages(user.getId(), id);
            req.setAttribute("allMessage", messageManager.getMessages(user.getId(), id));
            req.setAttribute("toUser", userManager.getUserById(id));
            req.getRequestDispatcher("/WEB-INF/message/message.jsp").forward(req, resp);
        }

    }
}
