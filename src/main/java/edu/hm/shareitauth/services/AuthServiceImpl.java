package edu.hm.shareitauth.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareitauth.model.Profile;
import edu.hm.shareitauth.model.Token;
import edu.hm.shareitauth.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Auth-service that contains the core data of the service. Uses maps to save user-data and log in information.
 */
public class AuthServiceImpl implements IAuthService {

    private static List<User> userList = new LinkedList<>();
    private static Map<Token, User> accessList = new HashMap<>();
    private static Map<Token, Double> expireList = new HashMap<>();
    private static Map<User, Profile> userProfiles = new HashMap<>();

    private static String fail = "0";

    //Token is valid for 12 minutes
    private static final double EXPIRE_TIME = 12 * 60 * 1000;

    /**
     * Constructor that creates the aut-service, with a few test users.
     */
    public AuthServiceImpl() {
        userList.add(new User("markus", "123456789"));
        userList.add(new User("peter", "12345"));

        userProfiles.put(new User("markus", "123456789"), new Profile(true, "eng", "no-ads, sort after date"));
        userProfiles.put(new User("peter", "12345"), new Profile(true, "deu", "ads, sort after size"));
    }

    @Override
    public String getToken(User user) {
        if (userList.contains(user)) {
            Token token = new Token();
            double time = System.currentTimeMillis();
            accessList.put(token, user);
            expireList.put(token, time);
            return token.getToken();
        }
        return fail;
    }

    @Override
    public String validateToken(Token token) {
        String res = "";
        User user;
        double time = System.currentTimeMillis();
        if (accessList.containsKey(token)) {
            user = accessList.get(token);
            if (expireList.containsKey(token)) {
                double oldTime = expireList.get(token);
                if (Math.abs(time - oldTime) < EXPIRE_TIME) {
                    Profile p = userProfiles.get(user);
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        res = mapper.writeValueAsString(p);
                    } catch (JsonProcessingException e) {
                        res = "{\"message\":\"parse error.\"}";
                    }

                }
                else {
                    expireList.remove(token);
                    accessList.remove(token);
                    res = "{\"message\":\"old token.\"}";
                }
            }
            else {
                accessList.remove(token);
                res = "{\"message\":\"token has no time stamp.\"}";
            }
        }
        else {
            res = "{\"message\":\"token is wrong.\"}";
        }
        return res;
    }

    /**
     * Getter that returns fail-code.
     * @return fail-code
     */
    public static String getFail() {
        return fail;
    }
}
