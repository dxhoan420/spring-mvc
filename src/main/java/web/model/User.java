package web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class User {

    @EqualsAndHashCode.Exclude
    private Long id;

//    @NotEmpty(message = "Имя должно быть заполнено")
    @Size(min = 2, max = 30, message = "Длина имени от 2 до 30 символов")
    private String name;

    @Email (message = "Используйте почту в формате ваш_логин@почтовый_сервер.доменная_зона")
    @NotEmpty(message = "Почта должна быть заполнена")
    private String email;
    private String phone;
    private LocalDate birthdate;
}
