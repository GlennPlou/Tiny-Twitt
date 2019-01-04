package fr.univnantes.beans;

import com.googlecode.objectify.annotation.*;

import java.util.Date;
import java.util.List;

@Entity
@Cache
@Index
public class Twitt {

    @Id
    private Long id;

    private String author;

    private String message;

    private Date date;

    public Twitt() {}

    public Twitt(String author, String message) {
        this.author = author;
        this.message = message;
        this.date = new Date();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
