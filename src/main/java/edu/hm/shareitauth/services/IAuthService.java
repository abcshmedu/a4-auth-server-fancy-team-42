package edu.hm.shareitauth.services;

import edu.hm.shareitauth.model.Token;
import edu.hm.shareitauth.model.User;

/**
 * Interface that specifies the methods of hte authservice
 */
public interface IAuthService {

    //checks if data is valid and generates a token
    String getToken (User user);

    //Returns JWT in string with JSON-format
    String validateToken (Token token);
}
