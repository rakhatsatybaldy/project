package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model;

import javax.persistence.*;

@Entity

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName , lastName , town , fullText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Message(String firstName, String lastName, String town, String fullText) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.town = town;
        this.fullText = fullText;
    }

    public Message() {
    }
}
