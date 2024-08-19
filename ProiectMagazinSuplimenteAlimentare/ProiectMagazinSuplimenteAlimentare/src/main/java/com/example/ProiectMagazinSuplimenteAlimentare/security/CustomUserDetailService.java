package com.example.ProiectMagazinSuplimenteAlimentare.security;


import com.example.ProiectMagazinSuplimenteAlimentare.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.example.ProiectMagazinSuplimenteAlimentare.model.User userAuthFromDB = userRepository.findFirstByUsername(username);
        if (userAuthFromDB == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails userDetails = User.withUsername(userAuthFromDB.getUsername()).password(userAuthFromDB.getPassword()).roles(userAuthFromDB.getClass().getSimpleName().toUpperCase()).build();
        userDetails.getAuthorities().forEach(auth -> {
            System.out.println(userDetails.getPassword() + "  " + auth.toString());
        });
        return userDetails;
    }
}