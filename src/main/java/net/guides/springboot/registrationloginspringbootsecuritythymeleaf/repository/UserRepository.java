package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Product;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <User, Long > {
    User findByEmail(String email);
}