package ru.netology.backendservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.backendservice.Security.MyUser;


import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUserName(String name);
}
