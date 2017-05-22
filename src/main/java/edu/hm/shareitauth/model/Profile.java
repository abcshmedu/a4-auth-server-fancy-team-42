package edu.hm.shareitauth.model;

/**
 * Created by markus on 22.05.17.
 */
public class Profile {

    private boolean admin;
    private String language;
    private String settings;

    public Profile(){}

    public Profile (boolean admin, String lanuage, String settings){
        this.admin = admin;
        this.language = lanuage;
        this.settings = settings;
    }

    @Override
    public int hashCode() {
        if(admin){
            return this.language.hashCode()+this.settings.hashCode()*2;
        }
        else{
            return this.language.hashCode()+this.settings.hashCode();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (admin != profile.admin) return false;
        if (language != null ? !language.equals(profile.language) : profile.language != null) return false;
        return settings != null ? settings.equals(profile.settings) : profile.settings == null;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "admin=" + admin +
                ", language='" + language + '\'' +
                ", settings='" + settings + '\'' +
                '}';
    }

    public String getLanguage() {
        return language;
    }

    public String getSettings() {
        return settings;
    }

    public boolean isAdmin() {

        return admin;
    }
}
