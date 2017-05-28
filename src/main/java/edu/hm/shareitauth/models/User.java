package edu.hm.shareitauth.models;

/**
 * Class that represents the user. Every user has a name and a password, every user must have a different name.
 */
public class User {

    private String name;
    private String password;

    @Override
    public boolean equals(Object obj) {
        try {
            User other = (User) obj;
            return other.getName().equals(this.getName()) && other.getPassword().equals(this.getPassword());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        String tmp = this.getName() + this.getPassword();
        return tmp.hashCode();
    }

    /**
     * Constructor that creates a new user.
     * @param name name of the user
     * @param pw password of the user
     */
    public User(String name, String pw) {
        this.name = name;
        this.password = pw;
    }

    /**
     * Empty constructor necessary for Jackson-Framework.
     */
    public User() { }

    @Override
    public String toString() {
        return "User: " + this.getName() + "||Password: " + this.getPassword();
    }

    /**
     * Getter for the user password.
     * @return password as string.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter for the user-name.
     * @return name as string.
     */
    public String getName() {
        return this.name;
    }


}
