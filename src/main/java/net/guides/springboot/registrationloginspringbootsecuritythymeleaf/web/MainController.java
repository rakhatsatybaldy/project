package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.web;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Message;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Product;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.User;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.MessageRepo;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.ProductRepo;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.web.dto.UserRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/home";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/order")
    public String orderPage()
    {
        logger.warn("This URL '/order' is for only users");
        return "order";
    }

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private ProductRepo productRepo;


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/contact")
    public String contactAdd(Model model){
        logger.warn("This URL '/contact' is for only users");
        return "contact";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/contact")
    public String contactMessageAdd(@RequestParam String firstName, @RequestParam String lastName ,
                                    @RequestParam String town , @RequestParam String fullText, Model model){
        logger.info("Sending message to admin: " + fullText + " from user: " + firstName + " " + lastName);
        Message message = new Message(firstName , lastName , town, fullText);
        messageRepo.save(message);
        return "redirect:/contact?success";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/yourorders/{markType}")
    public String getOrderingProduct(@PathVariable(value = "markType") String markType, Model model){
        Optional<Product> product = Optional.ofNullable(productRepo.findByMarkType(markType));
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product" , res);
        return "yourorders";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/order")
    public String queryOrderingProduct(@RequestParam String markType, Model model){
        Product existing = productRepo.findByMarkType(markType);

        if (existing==null){    
            return "redirect:/order?error";
        }

        return "redirect:/order?success";
    }







}