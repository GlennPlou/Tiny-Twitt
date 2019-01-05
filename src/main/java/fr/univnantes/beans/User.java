package fr.univnantes.beans;

import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
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

    private List<Long> followers;//Liste des gens qui me suivent

    private List<Long> following;//Liste des gens que je suis

    public User() {}

    public User(String login, String email, String password, String firstname, String lastname) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean addFollower(Long idFollower) {
        return this.followers.add(idFollower);
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> getFollowing() {
        return following;
    }

    public void setFollowing(List<Long> following) {
        this.following = following;
    }

    public boolean addFollowing(Long idFollowing) {
        return this.following.add(idFollowing);
    }

    public boolean containsFollower(Long idFollower) {
        return followers.contains(idFollower);
    }

    public boolean containsFollowing(Long idFollowing) {
        return following.contains(idFollowing);
    }

}
