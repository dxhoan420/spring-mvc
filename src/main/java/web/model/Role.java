package web.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "roles")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Role  implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
