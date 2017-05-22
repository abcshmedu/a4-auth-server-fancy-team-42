package edu.hm.shareitauth.model;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Class that represents the token.
 */
public class Token {
    private String token;
    private final static int randomBits = 130;
    private final static int stringBits = 32;


    public Token () {
        SecureRandom random = new SecureRandom();
        this.token = new BigInteger(randomBits,random).toString(stringBits);
    }

    public String getToken(){
        return this.token;
    }

    @Override
    public boolean equals(Object obj) {

        Token token = null;
        try{
            token = (Token) obj;
        }
        catch (ClassCastException ex){
            return false;
        }
        return token.getToken().equals(this.getToken());
    }

    @Override
    public String toString() {
        return this.getToken();
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}
