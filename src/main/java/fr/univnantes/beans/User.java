package fr.univnantes.beans;

import com.googlecode.objectify.annotation.*;

import java.util.List;

@Entity
@Cache
@Index
public class User {
    @Id
    @Unindex
    private Long id;

    private String login;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private List<Long> followers;

    public User() {}

    public User(String login, String email, String password, String firstname, String lastname) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

}
