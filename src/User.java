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
        if (!watched.contains(m)) {
            watched.add(m);
        }
    }

    public void addToSaved(Media m){
        if (!saved.contains(m)) {
            saved.add(m);
        }
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
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


    public ArrayList<Media> getSaved() {
        return saved;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
