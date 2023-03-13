package ru.netology.backendservice.Security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
@Builder
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String role;

    public MyUser(Long id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public MyUser() {

    }
}

