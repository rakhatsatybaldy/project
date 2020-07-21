package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.service;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.User;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}