/**
 * ***********************************************************
 * Autorskie Prawa Majątkowe Kamil Zemczak
 *
 * Copyright 2017 Kamil Zemczak
 * ************************************************************
 * Utworzono 26-10-2017, Kamil Zemczak
 * ************************************************************
 */
package kamilzemczak.runny.dto;

import java.util.List;
import kamilzemczak.runny.model.User;

public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private Integer age;
    private String gender;
    private String password;
    private Integer weight;
    private Integer height;
    private String city;
    private String about;
    private List<User> friends;

    public UserDTO(Integer id, String name, String surname, String username, String email, Integer age, String gender, String password, Integer weight, Integer height, String city, String about, List<User> friends) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.city = city;
        this.about = about;
        this.friends = friends;
    }
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
    
    @Override
    public String toString() {
        return "UserDTO{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", email=" + email + ", age=" + age + ", gender=" + gender + ", password=" + password + ", weight=" + weight + ", height=" + height + ", city=" + city + ", about=" + about + '}';
    }
}
