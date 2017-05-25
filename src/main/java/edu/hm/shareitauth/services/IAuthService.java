package edu.hm.shareitauth.services;

import edu.hm.shareitauth.model.Token;
import edu.hm.shareitauth.model.User;

/**
 * Interface that specifies the methods of the authservice.
 */
public interface IAuthService {

    /**
     * checks if data is valid and generates a token.
     * @param user User with password and name.
     * @return JSON-Object of Token class.
     */
    //
    String getToken(User user);

    /**
     * Returns JWT in string with JSON-format.
     * @param token string representing user-data.
     * @return JWT in string representation.
     */
    String validateToken(Token token); }