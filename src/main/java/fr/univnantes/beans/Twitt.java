package fr.univnantes.beans;

import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Cache
@Index
public class Twitt {

    @Id
    private Long id;

    private Long idAuthor;

    private String nameAuthor;

    private String message;

    private Date date;

    private List<String> hashtags;

    public Twitt() {}

    public Twitt(com.google.appengine.api.datastore.Entity e) {
        this.idAuthor = (Long) e.getProperty("id");
        this.nameAuthor =  (String) e.getProperty("author");
        this.message = (String) e.getProperty("message");
        this.date = (Date) e.getProperty("date");

        buildHashtags();
    }

    public Twitt(Long idAuthor, String nameAuthor, String message) {
        this.idAuthor = idAuthor;
        this.nameAuthor = nameAuthor;
        this.message = message;
        this.date = new Date();
        this.hashtags = new ArrayList<>();

        buildHashtags();
    }

    private void buildHashtags() {
        String hashtag = "";
        boolean tag = false;
        for (int i = 0; i < this.message.length(); ++i) {
            if (tag && this.message.charAt(i) == (' ')) {
                this.hashtags.add(hashtag);
                hashtag = "";
                tag = false;
            }
            if(tag || this.message.charAt(i) == ('#')) {
                tag = true;
                hashtag = hashtag + this.message.charAt(i);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
