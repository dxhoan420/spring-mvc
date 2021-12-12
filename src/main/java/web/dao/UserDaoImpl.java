package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements  UserDao {
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
        old.setUsername(updatedUser.getUsername());
        old.setEmail(updatedUser.getEmail());
        old.setPhone(updatedUser.getPhone());
        old.setRoles(updatedUser.getRoles());
    }

    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }

    public User findByUsername(String username) {
        List<User> resultList = entityManager.createQuery("from User where username = :username", User.class)
                .setParameter("username", username).getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else if (resultList.size() == 1) {
            return  resultList.get(0);
        } else {
            throw new NonUniqueResultException();
        }
    }
}
