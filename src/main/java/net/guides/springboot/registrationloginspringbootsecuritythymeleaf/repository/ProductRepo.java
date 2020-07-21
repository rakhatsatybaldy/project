package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product , Long> {
        Product findByMarkType(String markType);

}
