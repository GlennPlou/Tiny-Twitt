package fr.univnantes;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import fr.univnantes.beans.User;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

@Api(name = "TinyTwittAPI")
public class TinyTwittEndPoint {

    @ApiMethod(
            name = "createUser",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void createUser(@Named("login") String login, @Named("email") String email, @Named("password") String password, @Named("firstname") String firstname, @Named("lastname") String lastname) {

        List<User> users = ofy().load().type(User.class).filter("login", login).list();

        users.addAll(
                ofy().load().type(User.class).filter("email", email).list()
        );

        if (users.isEmpty() ) {
            User newUser = new User(login, email, password, firstname, lastname);
            ofy().save().entity(newUser).now();
        } else {
            throw new IllegalStateException();
        }
    }

    @ApiMethod(
            name = "getUser",
            httpMethod = ApiMethod.HttpMethod.GET)
    public User getUser(@Named("login") String login) {
        User user = ofy().load().type(User.class).filter("login", login).first().now();

        if(user == null) {
            throw new NullPointerException("User not found.");
        }
        return user;
    }

    @ApiMethod(
            name = "followUser",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void followUser(@Named("follower") String followerLogin, @Named("followed") String followedLogin) {
        User followerUser = ofy().load().type(User.class).filter("login", followerLogin).first().now();
        User followedUser = ofy().load().type(User.class).filter("login", followedLogin).first().now();

        if (followerUser == null)
            throw  new NullPointerException("Follower user not found.");
        if (followedUser == null)
            throw  new NullPointerException("Followed user not found.");

        Long idFollower = followerUser.getId();
        Long idFollowed = followedUser.getId();

        if (followerUser.containsFollowing(idFollowed)) {
            throw new IllegalStateException("You already follow this user.");
        } else {
            followedUser.addFollower(idFollower);
            followerUser.addFollowing(idFollowed);
        }
    }
}
