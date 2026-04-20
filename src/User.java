import java.util.ArrayList;

public class User {

    private String id;
    private String username;
    private String password;
    private ArrayList<Media>watched;
    private ArrayList<Media>saved;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.watched = new ArrayList<>();
        this.saved = new ArrayList<>();
    }

    public boolean checkLogin(String password){
        return this.password.equals(password);
    }

    public void addToWatched(Media m){

    }

    public void addToSaved(Media m){

    }

    public void removeFromSaved(Media m){

    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Media> getWatched() {
        return watched;
    }

    public void setWatched(ArrayList<Media> watched) {
        this.watched = watched;
    }

    public ArrayList<Media> getSaved() {
        return saved;
    }

    public void setSaved(ArrayList<Media> saved) {
        this.saved = saved;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
