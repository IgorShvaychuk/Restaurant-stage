package com.restaurant.app.management.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "employee")
public class Employee extends AbstractEntity {

    private String username;
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //no-argument constructor, for Hibernate

    public Employee() {}

    public Employee(String username, String password) {

        super();

        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username");
        }

        this.username = username;
        this.pwHash = hashPassword(password);

    }

    @NotNull
    @Column(name = "pwhash")
    public String getPwHash() {
        return pwHash;
    }

    @SuppressWarnings("unused")
    private void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    @NotNull
    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    @SuppressWarnings("unused")
    private void setUsername(String username) {
        this.username = username;
    }

    //checks that the given p.word is correct for the user
    //user.isMatchingPassword(...)
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    //checks that p.word meets min. standards
    public static boolean isValidPassword(String password) {
        Pattern validUsernamePattern = Pattern.compile("(\\S){6,20}");
        Matcher matcher = validUsernamePattern.matcher(password);
        return matcher.matches();
    }

    //checks that username meets min standards
    public static boolean isValidUsername(String username) {
        Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{4,11}");
        Matcher matcher = validUsernamePattern.matcher(username);
        return matcher.matches();
    }
}
