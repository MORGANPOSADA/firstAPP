package mx.edu.utez.firstapp.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.models.role.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Boolean status;
    private String lastAccess;
    private Boolean blocked;
    private String token;
    @OneToOne
    @JoinColumn(name = "person_id",
            referencedColumnName = "id",
            unique = true)
    @JsonIgnore
    private Person person;
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
}
