package fr.univnantes;

import fr.univnantes.beans.User;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

public class TinyTwittEndPoint {
    public void createUser(String login, String email, String password, String firstname, String lastname) {

        List<User> users = ofy().load().type(User.class).filter("login", login).list();

        users.addAll(
                ofy().load().type(User.class).filter("email", email).list()
        );

        if (users.isEmpty() ) {
            User newUser = new User(login, email, password, firstname, lastname);
        }
    }
}
