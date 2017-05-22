package edu.hm.shareitauth.services;

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

    private static String fail = "0";


    @Override
    public String getToken(User user) {
        if(UserData.contains(user)) {
            Token token = new Token();
            AccesList.put(token, user);
            return token.getToken();
        }
        return fail;
    }

    @Override
    public String validateToken(Token token) {
        return null;
    }
}
