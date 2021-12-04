package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getUserById(long id);
    public boolean saveUser(User user);
    public void updateUser(long id, User user);
    public boolean deleteUserById(long id);
}
