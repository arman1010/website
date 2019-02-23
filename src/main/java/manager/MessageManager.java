package manager;

import db.DBConnectionProvider;
import model.Message;
import util.DateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private Connection connection;

    public MessageManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addMessage(Message message) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO message(`from_id`,`to_id`,`message`,`date`) VALUES (" + message.getFromId().getId() + "," + message.getToId().getId() + ",'" + message.getText() + "','" + DateUtil.convertDateToString(message.getDate()) + "');";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                message.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Message> getMessages(int fromId, int toId) {
        UserManager userManager = new UserManager();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM message WHERE `from_id` = " + fromId + " AND `to_id`=" + toId;
            ResultSet resultSet = statement.executeQuery(query);
            List<Message> messages = new ArrayList<>();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFromId(userManager.getUserById(resultSet.getInt(2)));
                message.setToId(userManager.getUserById(resultSet.getInt(3)));
                message.setText(resultSet.getString(4));
                message.setDate(DateUtil.convertStringToDate(resultSet.getString(5)));
                messages.add(message);
            }
            List<Message> messagesSecond = getMessagesSecond(toId, fromId);
            if (messagesSecond!=null) {
                messages.addAll(messagesSecond);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }public List<Message> getMessagesSecond(int toId, int fromId) {
        UserManager userManager = new UserManager();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM message WHERE `to_id` = " + fromId + " AND `from_id`=" + toId;
            ResultSet resultSet = statement.executeQuery(query);
            List<Message> messages = new ArrayList<>();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setFromId(userManager.getUserById(resultSet.getInt(2)));
                message.setToId(userManager.getUserById(resultSet.getInt(3)));
                message.setText(resultSet.getString(4));
                message.setDate(DateUtil.convertStringToDate(resultSet.getString(5)));
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
