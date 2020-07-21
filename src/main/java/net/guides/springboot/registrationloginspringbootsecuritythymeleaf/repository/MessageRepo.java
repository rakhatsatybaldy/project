package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message , Long> {
}
