package web.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity @Table
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 30, message = "Длина имени от 2 до 30 символов")
    private String name;

    @Email (message = "Используйте почту в формате ваш_логин@почтовый_сервер.доменная_зона")
    @NotEmpty(message = "Почта должна быть заполнена")
    private String email;

    @NotEmpty(message = "Номер должна быть заполнен")
    private String phone;

//    private LocalDate birthdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && email.equals(user.email) && phone.equals(user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone);
    }
}
