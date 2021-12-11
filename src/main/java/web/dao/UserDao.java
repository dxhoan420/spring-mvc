package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(long id);
    void saveUser(User user);
    void updateUser(long id, User updatedUser);
    void deleteUserById(long id);
    User findByUsername(String username);
}
