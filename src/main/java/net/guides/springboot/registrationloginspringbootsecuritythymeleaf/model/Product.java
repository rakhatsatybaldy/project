package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mark;
    private String name;
    private String markType;
    private String memory;
    private String ssd;
    private String videocard;
    private String type;
    private String os;
    private String price;

    private String master;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Product() {
    }

    public Product(String mark, String name, String markType, String memory, String ssd, String videocard, String type, String os, String price) {
        this.mark = mark;
        this.name = name;
        this.markType = markType;
        this.memory = memory;
        this.ssd = ssd;
        this.videocard = videocard;
        this.type = type;
        this.os = os;
        this.price = price;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarkType() {
        return markType;
    }

    public void setMarkType(String markType) {
        this.markType = markType;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
