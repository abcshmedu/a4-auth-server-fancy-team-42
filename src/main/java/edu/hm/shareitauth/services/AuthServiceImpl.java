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
 * Created by markus on 22.05.17.
 */
public class AuthServiceImpl implements IAuthService {

    private static List<User> UserData = new LinkedList<>();
    private static Map<Token,User> AccesList = new HashMap<>();
    private static Map<Token,Double> ExpireList = new HashMap<>();
    private static Map<User,Profile> UserProfiles = new HashMap<>();

    public static String fail = "0";

    //Token is valid for 12 minutes
    private static double expireTime = 12*60*1000;

    public AuthServiceImpl(){
        UserData.add(new User("markus","123456789"));
        UserData.add(new User("peter","12345"));

        UserProfiles.put(new User("markus","123456789"),new Profile(true,"eng","no-ads, sort after date"));
        UserProfiles.put(new User("peter","12345"),new Profile(true,"deu","ads, sort after size"));
    }

    @Override
    public String getToken(User user) {
        if(UserData.contains(user)) {
            Token token = new Token();
            double time = System.currentTimeMillis();
            AccesList.put(token, user);
            ExpireList.put(token,time);
            return token.getToken();
        }
        return fail;
    }

    @Override
    public String validateToken(Token token) {
        String res = "";
        User user;
        double time = System.currentTimeMillis();
        if(AccesList.containsKey(token)){
            user = AccesList.get(token);
            if (ExpireList.containsKey(token)){
                double oldTime = ExpireList.get(token);
                if(Math.abs(time-oldTime)<expireTime){
                    Profile p = UserProfiles.get(user);
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        res = mapper.writeValueAsString(p);
                    } catch (JsonProcessingException e) {
                        res = "{\"message\":\"parse error.\"}";
                    }

                }
                else {
                    ExpireList.remove(token);
                    AccesList.remove(token);
                    res = "{\"message\":\"old token.\"}";
                }
            }
            else {
                AccesList.remove(token);
                res = "{\"message\":\"token has no time stamp.\"}";
            }
        }
        else {
            res = "{\"message\":\"token is wrong.\"}";
        }
        return res;
    }
}
