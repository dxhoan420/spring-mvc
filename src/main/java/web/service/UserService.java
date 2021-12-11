package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(long id);
    boolean saveUser(User user);
    void updateUser(long id, User user);
    boolean deleteUserById(long id);
    User findByUsername(String username);
}
