import java.util.ArrayList;

public class User {

    private String userName;
    private String password;
    private ArrayList<Media>watched;
    private ArrayList<Media>saved;

    public User(String userName, String password, ArrayList<Media> watched, ArrayList<Media> saved) {
        this.userName = userName;
        this.password = password;
        this.watched = watched;
        this.saved = saved;
    }

    public boolean checkLogin(String password){
        return true;
    }

    public void addToWatched(Media m){

    }

    public void addToSaved(Media m){

    }

    public void removeFromSaved(Media m){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
