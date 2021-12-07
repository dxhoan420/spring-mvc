package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public void saveUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(long id, User updatedUser) {
        User old = getUserById(id);
        old.setId(updatedUser.getId());
        old.setName(updatedUser.getName());
        old.setEmail(updatedUser.getEmail());
        old.setPhone(updatedUser.getPhone());
    }

    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }
}
