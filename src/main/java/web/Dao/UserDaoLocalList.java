package web.Dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoLocalList {
    private List<User> users;
    private Long id = 1L;

    {
        users = new ArrayList<>();
        users.add(new User(id++, "Kirill", "kiril@lirik.ru","88005553535", LocalDate.now()));
        users.add(new User(id++, "Roman", "amor@gmail.com","+77777777777", LocalDate.now().minusYears(3L)));
        users.add(new User(id++, "Alexey", "alex12334098134089174@yahoeu.uk","nope", LocalDate.now().minusDays(3)));
        users.add(new User(id++, "Vitek", "vitya1986@mails.ru","8841255555", LocalDate.now().minusWeeks(3)));
    }

    public List<User> getUsers() {
        return  users;
    }

    public User findUser(int id) {
        return users.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public void createUser(User user) {
        user.setId(id++);
        users.add(user);
    }

    public void updateUser(int id, User updatedUser) {
        User old = findUser(id);
        old.setName(updatedUser.getName());
        old.setEmail(updatedUser.getEmail());
        old.setPhone(updatedUser.getPhone());
//        old.setBirthdate(updatedUser.getBirthdate());
    }

    public void deleteUser(int id) {
        users.removeIf(x -> x.getId() == id);
    }
}
