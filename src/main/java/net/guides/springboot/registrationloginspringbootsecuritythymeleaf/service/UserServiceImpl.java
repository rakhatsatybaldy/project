package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Role;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.User;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.web.dto.UserRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setName(registration.getName());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        logger.info("Creating new user by fields: " + user.getEmail() + " " +
                user.getPassword() + " " + user.getName() + " with "+ user.getRoles());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            logger.error("Invalid username " + email + " or password!");
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}