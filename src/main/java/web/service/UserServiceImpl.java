package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import web.dao.UserDaoImpl;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao repository;

    @Autowired
    public UserServiceImpl(UserDao repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }

    @Transactional(readOnly = true)
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
