package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.web;

import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Message;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.Product;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model.User;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.MessageRepo;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.ProductRepo;
import net.guides.springboot.registrationloginspringbootsecuritythymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminActionController {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/admin")
    public String addProduct(Model model){
        return "admin";
    }

    @PostMapping("/admin")
    public String productAdd(@RequestParam String mark, @RequestParam String name ,
                             @RequestParam String markType , @RequestParam  String memory,
                             @RequestParam String ssd, @RequestParam String videocard ,
                             @RequestParam String type , @RequestParam String os, @RequestParam String price, Model model){
        Product product = new Product(mark , name , markType , memory , ssd , videocard , type , os, price);
        productRepo.save(product);
        return "redirect:/admin?success";
    }

    @GetMapping("/contact/show")
    public String contactPage(Model model){
        List<Message> messages = messageRepo.findAll();
        System.out.println(messages.size());
        model.addAttribute("messages" , messages);
        return "contact-show";
    }

    @GetMapping("/show")
    public String showAddedProducts(Model model){
        List<Product> products = productRepo.findAll();
        System.out.println(products.size());
        model.addAttribute("products" , products);
        return "show";
    }

    @GetMapping("/delete")
    public String deletePage(){
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String markType, Model model){
        Product product = productRepo.findByMarkType(markType);
        productRepo.delete(product);
        return "redirect:/show";
    }

    @GetMapping("/usersList/show")
    public String listUsers(Model model){
        List<User> users = userRepository.findAll();
        System.out.println(users.size());
        model.addAttribute("users" , users);
        return "usersList";
    }
}