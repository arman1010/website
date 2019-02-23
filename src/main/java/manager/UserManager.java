package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Connection connection;

    public UserManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) {
        try {
            String query = "INSERT INTO user(`name`,`surname`,`email`,`password`,`pic_url`)" +
                    "VALUES(?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPicUrl());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        String query = "SELECT * FROM user WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setPicUrl(resultSet.getString(6));
                return user;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeUserById(int id) {
        String query = "DELETE  FROM user WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE `email` = '" + email + "' AND `password` = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setPicUrl(resultSet.getString(6));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUserExist(String email) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user WHERE `email`=" + email;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getAllUsers() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user";
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setPicUrl(resultSet.getString(6));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllFriends(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT user_id FROM user_friend WHERE `friend_id` = " + id;
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                users.add(getUserById(resultSet.getInt(1)));
            }
            if (getUserId(id) != null) {
                users.addAll(getUserId(id));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserId(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT friend_id FROM user_friend WHERE `user_id` = " + id;
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                users.add(getUserById(resultSet.getInt(1)));

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllRequests(int id) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT from_id FROM friend_request WHERE `to_id` = " + id;
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                users.add(getUserById(resultSet.getInt(1)));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendRequest(int fromId, int toId) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO friend_request(`from_id`,`to_id`) VALUES(" + fromId + "," + toId + ")";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriend(int userId, int fromId) {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO user_friend(`user_id`,`friend_id`) values (" + userId + "," + fromId + ")";
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeRequest(int toId, int fromId) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM friend_request WHERE `from_id`=" + fromId + " AND to_id = " + toId;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeFriend(int sessionUserId, int userId) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM user_friend WHERE `user_id` = " + sessionUserId + " AND `friend_id` = " + userId;
            statement.executeUpdate(query);
        } catch (SQLException e) {


        }
    }

    public boolean isRequestSend(int fromId, int toId) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM friend_request WHERE `from_id` = " + fromId + "AND `to_id` = " + toId;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
            return isReqSend(fromId, toId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isReqSend(int fromId, int toId) {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM friend_request WHERE `to_id` = " + fromId + "AND `from_id` = " + toId;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
