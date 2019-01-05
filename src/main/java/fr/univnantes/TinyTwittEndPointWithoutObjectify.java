package fr.univnantes;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import fr.univnantes.beans.*;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(name = "tinyTwittAPI")
public class TinyTwittEndPointWithoutObjectify {

    @ApiMethod(
            name = "createUser",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void createUser(@Named("login") String login, @Named("email") String email, @Named("password") String password, @Named("firstname") String firstname, @Named("lastname") String lastname) {

    }

    @ApiMethod(
            name = "getUser",
            httpMethod = ApiMethod.HttpMethod.GET)
    public User getUser(@Named("login") String login) {

    }

    @ApiMethod(
            name = "followUser",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void followUser(@Named("follower") String followerLogin, @Named("followed") String followedLogin) {

    }

    @ApiMethod(
            name = "getListUsers",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<User> getListUsers() {


    }

    @ApiMethod(
            name = "createTwitt",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void createTwitt(@Named("login") String login, @Named("message") String message) {

    }

    @ApiMethod(
            name = "getTimeline",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<Twitt> getTimeline(@Named("login") String login) {

        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        Filter filter = new Query.FilterPredicate("login", Query.FilterOperator.EQUAL, login);
        Query query = new Query("User").setFilter(filter);
        Entity userEntity = ds.prepare(query).asSingleEntity();

        if (userEntity == null)
            throw  new NullPointerException("User not found.");

        Long id = userEntity.getKey().getId();

        filter = new Query.FilterPredicate("idAuthor", Query.FilterOperator.EQUAL, id);
        query = new Query("Twitt").setFilter(filter);

        List<Entity> twittKeysEntity = ds.prepare(query).asList(FetchOptions.Builder.withDefaults());

        if (twittKeysEntity == null)
            throw new NullPointerException("No twitt found");

        List<Key> keys = new ArrayList<Key>();
        for(Entity e : twittKeysEntity){
            Key k = e.getParent();
            keys.add(k);
        }

        Map<Key, Entity> map = ds.get(keys);
        List<Entity> list = new ArrayList<Entity>(map.values());

        List<Twitt> result = new ArrayList<Twitt>();
        for(Entity e : list){
            result.add(new Twitt(e));
        }

        return result;

    }

}
