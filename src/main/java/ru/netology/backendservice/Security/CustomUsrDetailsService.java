package ru.netology.backendservice.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.backendservice.Repository.UserRepo;

@Service
public class CustomUsrDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUsrDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return userRepo
                .findByUserName(userName)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + userName));

    }
}
