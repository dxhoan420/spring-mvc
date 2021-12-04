package web.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return repository.getUserById(id);
    }

    @Override
    public void updateUser(long id, User user) {
        repository.updateUser(id, user);
    }

    @Override
    public boolean saveUser(User user) {
        try {
            repository.saveUser(user);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Override
    public boolean deleteUserById(long id) {
        try {
            repository.deleteUserById(id);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}
