package com.example.Schedule.services;

import com.example.Schedule.models.User;
import com.example.Schedule.models.enums.Role;
import com.example.Schedule.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepository.findByUsername(user.getUsername()) != null) return false;
        user.setSocial_network(false);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public List<User> list(){
        return userRepository.findAll();
    }


    public User getUserByID(Long id){
        return userRepository.findById(id).orElse(null);
    }




}
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        User user = userRepository.findByName(name);
//        if(user == null) {throw new UsernameNotFoundException("No user found with name");}
////        List<String> roles = Array.asList(user.getRole());
//        UserDetails userDetails =
//                org.springframework.security.core.userdetails.User.builder()
//                        .username(user.getName())
//                        .password(user.getPassword())
////                        .roles(user.getRoles(Role_User))
//                        .build();
//        return userDetails;
//
//    }