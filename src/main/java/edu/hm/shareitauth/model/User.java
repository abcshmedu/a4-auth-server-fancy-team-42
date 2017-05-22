package edu.hm.shareitauth.model;

/**
 * Created by markus on 15.05.17.
 */
public class User {

    private String name;
    private String password;

    @Override
    public boolean equals(Object obj) {
        try{
            User other = (User) obj;
            return other.getName().equals(this.getName()) && other.getPassword().equals(this.getPassword());
        }
        catch (ClassCastException ex){
            return false;
        }
    }

    @Override
    public int hashCode() {
        String tmp = this.getName() + "|" + this.getPassword();
        return tmp.hashCode();
    }

    public User(String name, String pw){
        this.name = name;
        this.password = pw;
    }

    public User(){}

    @Override
    public String toString() {
        return "User: "+this.getName() + "||Password: " + this.getPassword();
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }


}