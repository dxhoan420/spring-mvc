package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.Dao.UserDaoLocalList;
import web.model.User;

import java.util.List;

@Component
public class UserService {

    private UserDaoLocalList userDAO;

    @Autowired
    public UserService(UserDaoLocalList userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public User findUser(int id) {
        return userDAO.findUser(id);
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public void updateUser(int id, User user) {
        userDAO.updateUser(id, user);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
