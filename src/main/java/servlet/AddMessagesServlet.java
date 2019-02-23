package servlet;

import lombok.Data;
import manager.MessageManager;
import manager.UserManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/message")
public class AddMessagesServlet extends HttpServlet {
    MessageManager messageManager = new MessageManager();
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String toId = req.getParameter("toId");
        String message = req.getParameter("message");
        int id = Integer.parseInt(toId);
        if (user != null) {
            Message message1 = new Message();
            message1.setFromId(user);
            message1.setToId(userManager.getUserById(id));
            message1.setText(message);
            message1.setDate(new Date());
            messageManager.addMessage(message1);
            req.setAttribute("toId",message1.getToId().getId());
            req.getRequestDispatcher("/toMessagePage").forward(req,resp);
        }
    }
}
